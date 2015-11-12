package com.mygdx.game.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import com.mygdx.game.serialization.Level;
import com.mygdx.game.serialization.Position;
import com.mygdx.game.view.assets.BulletView;
import com.mygdx.game.view.assets.CivilianView;
import com.mygdx.game.view.assets.GoonView;
import com.mygdx.game.view.assets.LogicAssets;
import com.mygdx.game.view.assets.PlayerView;
import com.mygdx.game.view.assets.TargetView;

/**
 * Esta clase es una modularizacion del contenido previo de Game Es quien
 * contiene el mapa y todos los personajes, como asi a los diferentes handlers
 * para administrarlos Se ocupa de actualizar el modelo y, luego, de actualizar
 * la vista, tal como dice MVC
 */
public class GameManager  {

	protected static final int MAX_SEARCH = 100;
	private String path;
	private TiledMap tiled_map;
	private ControlProcessor control;
	private BulletController bulletController;
	private PlayerController playerController;
	private List<CharacterController<?, ?>> characterControllerList = new ArrayList<CharacterController<?, ?>>();
	private NoiseController noiseController = new NoiseController();
	private GameState  state ;
	
	/**
	 * Dos valores para comparar contra los valores de quienes
	 * hayan muerto en la iteracion actual
	 */
	private Set<Integer> civilianIDs = new HashSet<Integer>() ;
	private Integer targetID ;
	private Integer playerID ;
	private TargetController targetController;
	private List<Vector2> goonRoutine = new RandList<Vector2>();
	private List<Vector2> targetRoutine = new RandList<Vector2>();
	private List<Vector2> civilianRoutine = new RandList<Vector2>();

	public GameManager(int width, int height, int tile_width, Viewport viewport, Level level, SpriteBatch batch)
			throws IllegalPositionException, IOException {
		NoiseManager.getInstance().clearAllListeners();
		BulletManager.getInstance().clearAllListeners();
		VisionManager.getInstance().clearAllListeners();
		this.path = level.getPath();
		control = new ControlProcessor(viewport);
		Gdx.input.setInputProcessor(control);
		
		

		File f = new File(path);
		if(!f.exists() || f.isDirectory()) 
			throw new IOException("Map not Found");

		tiled_map = new TmxMapLoader().load(path);
		
		LevelMap map = new LevelMap(width, height, tile_width, tiled_map);
		AStarPathFinder aStarPathFinder = new AStarPathFinder(map, MAX_SEARCH);
		LinearPathFinder linearPathFinder = new LinearPathFinder(map);
		goonRoutine = new RandList<Vector2>();
		goonRoutine.addAll(level.goonRoutine());
		targetRoutine= new RandList<Vector2>();
		targetRoutine.addAll(level.targetRoutine());
		civilianRoutine = new RandList<Vector2>();
		civilianRoutine.addAll(level.civilRoutine());
		
		for (Vector2 v : level.goonPositions()) {
			if (!map.isValid(new Rectangle(v.x, v.y, 18, 13))) {
				throw new IllegalPositionException();
			}

			GoonView goon_view = new GoonView(batch, LogicAssets.walkAnimation, LogicAssets.hurtWalkAnimation,
					LogicAssets.shootAnimation, LogicAssets.hurtShootAnimation, LogicAssets.deadTextureRegion,
					LogicAssets.exclamationTextureRegion, LogicAssets.interrogationTextureRegion);

			Goon goon = new Goon(new Rectangle(v.x, v.y, 18, 13), map, goonRoutine);
			goon.setAStarPathFinder(aStarPathFinder);
			goon.setLinearPathFinder(linearPathFinder);
			GoonController controller = new GoonController(goon, goon_view);
			characterControllerList.add(controller);
		}
		if (!map.isValid(new Rectangle(level.getPlayer().x, level.getPlayer().y, 18, 13))) {
			throw new IllegalPositionException();
		}
		PlayerView player_view = new PlayerView(batch, LogicAssets.playerWalkAnimation,
				LogicAssets.playerHurtWalkAnimation, LogicAssets.playerShootAnimation,
				LogicAssets.playerHurtShootAnimation, LogicAssets.playerDeadTextureRegion);
		Player player = new Player(new Rectangle(level.getPlayer().x, level.getPlayer().y, 18, 13), map);
		playerController = new PlayerController(player, control, player_view);
		characterControllerList.add(playerController);
		playerID = player.getId() ;
		linearPathFinder = new LinearPathFinder(map);
		BulletManager.getInstance().setMap(map);
		
		for (Vector2 v : level.civilPositions()) {
			if (!map.isValid(new Rectangle(v.x, v.y, 18, 13))) {
				throw new IllegalPositionException();
			}
			CivilianView civilianView = new CivilianView(batch, LogicAssets.civilianWalkAnimation,
					LogicAssets.civilainHurtWalkAnimation, LogicAssets.civilianDeadTextureRegion,
					LogicAssets.exclamationTextureRegion, LogicAssets.interrogationTextureRegion);
			Civilian civilian = new Civilian(new Rectangle(v.x, v.y, 18, 13), map, civilianRoutine, level.safePositions());
			civilian.setAStarPathFinder(aStarPathFinder);
			civilian.setLinearPathFinder(linearPathFinder);
			CivilianController civController = new CivilianController(civilian, civilianView);
			characterControllerList.add(civController);
			civilianIDs.add(civilian.getId()) ;
		}
		Vector2 tp = level.getTarget();
		Target target = new Target(new Rectangle(tp.x,tp.y, 18,13),map,targetRoutine);
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
		targetID = target.getId() ;
		BulletManager bulletManager = BulletManager.getInstance();
		BulletView bulletView = new BulletView(viewport.getCamera());
		bulletController = new BulletController(bulletManager, bulletView);
		this.state = GameState.PLAY ;
	}

	public TiledMap getTiledMap() {
		return tiled_map;
	}

	public void update() {
		if (control.paused()) {
			state = GameState.PAUSE ;
			return ;
		}
		VisionManager.getInstance().update();
		noiseController.updateModel();
		noiseController.updateView();
		bulletController.manage();
		Collections.sort(characterControllerList);
		for (CharacterController<?, ?> c : characterControllerList) {
			c.updateModel();
			c.updateView();
			c.draw();
			if (c.isDead()) {
				if (civilianIDs.contains(c.getModel().getId())) {
					state= GameState.DEFEAT ;
				} else if (targetID == c.getModel().getId()) {
					state= GameState.WIN ;
				} else if (playerID ==c.getModel().getId()) {
					state = GameState.DEFEAT ;
				}
			}
		}
	}
	public GameState getState() {
		return state ;
	}
	private void setState(GameState state) {
		this.state = state ;
	}
	public void unpause() {
		this.setState(GameState.PLAY); ;
		Gdx.input.setInputProcessor(control);
	}
}
