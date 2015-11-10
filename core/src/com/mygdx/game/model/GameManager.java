package com.mygdx.game.model;

import java.io.File;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.model.LevelMap;
import com.mygdx.game.model.VictoryInputProcessor;
import com.mygdx.game.model.character.AStarPathFinder;
import com.mygdx.game.model.character.Civilian;
import com.mygdx.game.model.character.Goon;
import com.mygdx.game.model.character.LinearPathFinder;
import com.mygdx.game.model.character.Player;
import com.mygdx.game.model.character.Target;
import com.mygdx.game.model.message.BulletManager;
import com.mygdx.game.model.message.NoiseManager;
import com.mygdx.game.model.message.VisionManager;
import com.mygdx.game.model.util.RandList;
import com.mygdx.game.view.assets.BulletView;
import com.mygdx.game.view.assets.CivilianView;
import com.mygdx.game.view.assets.GoonView;
import com.mygdx.game.view.assets.LogicAssets;
import com.mygdx.game.view.assets.PlayerView;
import com.mygdx.game.view.assets.TargetView;

import serialization.Dumpeable;
import serialization.GameInformation;
import serialization.Level;
import serialization.Position;

/**
 * Esta clase es una modularizacion del contenido previo de Game
 * Es quien contiene el mapa y todos los personajes, como asi a los diferentes 
 * handlers para administrarlos
 * Se ocupa de actualizar el modelo y, luego, de actualizar la vista, tal como
 * dice MVC
 */
public class GameManager implements Dumpeable {
	
	protected static final int MAX_SEARCH = 100 ;
	private String path ;
	private TiledMap tiled_map ;
	private ControlProcessor gameInputProcessor ;
	private PauseInputProcessor pauseInputProcessor;
	private DefeatInputProcessor defeatInputProcessor ;
	private VictoryInputProcessor victoryInputProcessor;
	private BulletController bulletController;
	private PlayerController playerController;
	private List<CharacterController<?,?>> characterControllerList;
	private NoiseController noiseController = new NoiseController() ;
	private LevelMap map;
	private SpriteBatch batch;
	private Viewport viewport;
	private GameState currentGameState;
	private Level level;
	private TargetController targetController;
	
	private enum GameState {
		GAME() {
			public void update(GameManager gm) {
				gm.updateGame();
				gm.updateGameView();
				gm.drawGame();
			}
		},
		PAUSE() {
			public void update(GameManager gm) {
				gm.pause();
				gm.drawGame();
				
			}
		},
		VICTORY() {
			public void update(GameManager gm) {
				try {
					gm.victory();
				}
				catch(IllegalPositionException e){
					e.getStackTrace();
				}
				gm.drawGame();
			}
		},
		DEFEAT() {
			public void update(GameManager gm) {
				try {
					gm.defeat();
				}
				catch(IllegalPositionException e){
					e.getStackTrace();
				}
				gm.drawGame();
			}
		};
		
		public abstract void update(GameManager gm);
	}
	
	
	public GameManager(int width,int height,int tile_width,Viewport viewport, Level level, SpriteBatch batch) throws IllegalPositionException {
		this.path=level.getPath();
		gameInputProcessor = new ControlProcessor(viewport) ;
		Gdx.input.setInputProcessor(gameInputProcessor);
		tiled_map= new TmxMapLoader().load(path);
		map = new LevelMap(width,height, tile_width,tiled_map);
		this.batch = batch;
		this.viewport = viewport;
		this.level = level;
		setLevel(level);
		currentGameState = GameState.GAME;
		pauseInputProcessor = new PauseInputProcessor();
		defeatInputProcessor = new DefeatInputProcessor();
		victoryInputProcessor = new VictoryInputProcessor();
		
	}
	
	public TiledMap getTiledMap() {
		return tiled_map;
	}
	
	public void update(){
		
		currentGameState.update(this);
		
	}
	
	private void updateGame() {
		
		if (gameInputProcessor.requestPause()) {
			currentGameState = GameState.PAUSE;
			gameInputProcessor.reset();
			Gdx.input.setInputProcessor(pauseInputProcessor);
		}
		else if (playerController.isDead()) {
			currentGameState = GameState.DEFEAT;
			gameInputProcessor.reset();
			Gdx.input.setInputProcessor(defeatInputProcessor);
		}
		else if (targetController.isDead()) {
			currentGameState = GameState.VICTORY;
			gameInputProcessor.reset();
			Gdx.input.setInputProcessor(victoryInputProcessor);
		}
		else {
			VisionManager.getInstance().update();
			noiseController.updateModel();
			bulletController.manage();
			Collections.sort(characterControllerList);
			
			for (CharacterController<?,?> c : characterControllerList){
				c.updateModel();
			}
		}
		
	}
	
	private void updateGameView() {
		bulletController.updateView();
		noiseController.updateView();
		for (CharacterController<? ,?> c: characterControllerList) {
			c.updateView();
		}
		
	}
	private void drawGame() {
		for (CharacterController<? ,?> c: characterControllerList) {
			c.draw();
		}
	}
	
	private void pause() {
		if (pauseInputProcessor.requestUnPause()) {
			currentGameState = GameState.GAME;
			Gdx.input.setInputProcessor(gameInputProcessor);
		}
	}
	
	private void victory() throws IllegalPositionException{
		if (victoryInputProcessor.requestReset()) {
			currentGameState = GameState.GAME;
			Gdx.input.setInputProcessor(gameInputProcessor);
			setLevel(level);
		}
	}
	
	private void defeat() throws IllegalPositionException{
		if (defeatInputProcessor.requestReset()) {
			currentGameState = GameState.GAME;
			Gdx.input.setInputProcessor(gameInputProcessor);
			setLevel(level);
		}
	}
	
	
	/**
	 * Metodo para probar el marshalling de la partida a un xml
	 * De usarse, guarda la posicion de todos los goons y del jugador 
	 * @throws JAXBException
	 */
	public void marshal(String to) throws JAXBException {
		List<Vector2> goonPositions = new ArrayList<Vector2>() ;
		List<Vector2> civilPositions = new ArrayList<Vector2>() ;
		Position playerPosition = new Position(playerController.position());
		for(CharacterController<?,?> c : characterControllerList) {
			if (c  instanceof GoonController) {
				goonPositions.add(c.position()) ;
			} 
			else if (c instanceof CivilianController) {
				civilPositions.add(c.position()) ;
			}
		}
		Level l = new Level (path,Position.vectorToPosition(goonPositions),Position.vectorToPosition(civilPositions),
				playerPosition) ;
		JAXBContext context = JAXBContext.newInstance(Level.class) ;
		Marshaller marshaller = context.createMarshaller();
		marshaller.marshal(l, new File(to));
	}
	
	@Override
	public GameInformation dump() {
		return null;
	}
	
	public void setLevel(Level level) throws IllegalPositionException{
		AStarPathFinder  aStarPathFinder  = new AStarPathFinder(map, MAX_SEARCH);
		LinearPathFinder linearPathFinder = new LinearPathFinder(map);
		List<Vector2> randArray = new RandList<Vector2>();
		
		BulletManager.getInstance().reset();
		VisionManager.getInstance().reset();
		NoiseManager.getInstance().reset();
		randArray.add(new Vector2(200, 150));
		randArray.add(new Vector2(700,700));
		randArray.add(new Vector2(73,792));
		randArray.add(new Vector2(817,48));
		characterControllerList = new ArrayList<CharacterController<?,?>>();
		for(Vector2 v : level.goonPositions()){
			if (!map.isValid(new Rectangle(v.x,v.y,18,13))) {
				throw new IllegalPositionException() ;
			}
			
			GoonView goon_view = new GoonView(
					batch,
					LogicAssets.walkAnimation,
					LogicAssets.hurtWalkAnimation,
					LogicAssets.shootAnimation,
					LogicAssets.hurtShootAnimation,
					LogicAssets.deadTextureRegion,
					LogicAssets.exclamationTextureRegion,
					LogicAssets.interrogationTextureRegion
					);
			
			Goon goon = new Goon(new Rectangle(v.x,v.y, 18,13),map, randArray);
			goon.setAStarPathFinder(aStarPathFinder);
			goon.setLinearPathFinder(linearPathFinder);
			GoonController controller = new GoonController(goon, goon_view);
			characterControllerList.add(controller);
		}
		if (!map.isValid(new Rectangle(level.getPlayer().x,level.getPlayer().y,18,13))) {
			throw new IllegalPositionException() ;
		}
		PlayerView player_view = new PlayerView(
				batch,
				LogicAssets.playerWalkAnimation,
				LogicAssets.playerHurtWalkAnimation,
				LogicAssets.playerShootAnimation,
				LogicAssets.playerHurtShootAnimation,
				LogicAssets.playerDeadTextureRegion
				);
		Player player = new Player(new Rectangle(level.getPlayer().x,level.getPlayer().y,18,13),map);
		playerController = new PlayerController(player,gameInputProcessor, player_view) ;
		characterControllerList.add(playerController);
		linearPathFinder = new LinearPathFinder(map);
		BulletManager.getInstance().setMap(map);
		for (Vector2 v : level.civilPositions()) {
			if (!map.isValid(new Rectangle(v.x,v.y,18,13))) {
				throw new IllegalPositionException() ;
			}
			CivilianView civilianView = new CivilianView(
					batch,
					LogicAssets.civilianWalkAnimation,
					LogicAssets.civilainHurtWalkAnimation,
					LogicAssets.civilianDeadTextureRegion,
					LogicAssets.exclamationTextureRegion,
					LogicAssets.interrogationTextureRegion
				);
			Civilian civilian = new Civilian(new Rectangle(v.x,v.y, 18,13),map, randArray, randArray);
			civilian.setAStarPathFinder(aStarPathFinder);
			civilian.setLinearPathFinder(linearPathFinder);
			CivilianController civController = new CivilianController(civilian, civilianView);
			characterControllerList.add(civController);
		}
		Target target = new Target(new Rectangle(200,150, 18,13),map,randArray);
		target.setAStarPathFinder(aStarPathFinder);
		target.setLinearPathFinder(linearPathFinder);
		TargetView targetView = new TargetView(
				batch,
				LogicAssets.targetWalkAnimation,
				LogicAssets.targetHurtWalkAnimation,
				LogicAssets.targetShootAnimation,
				LogicAssets.targetHurtShootAnimation,
				LogicAssets.targetDeadTextureRegion,
				LogicAssets.exclamationTextureRegion,
				LogicAssets.interrogationTextureRegion
				);
		targetController = new TargetController(target, targetView);
		characterControllerList.add(targetController);
		BulletManager bulletManager = BulletManager.getInstance();
		BulletView bulletView = new BulletView(viewport.getCamera());
		bulletController = new BulletController(bulletManager, bulletView);
	}
}
