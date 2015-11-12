package com.mygdx.game.controller;

import java.io.File;
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
	private List<Vector2> randArray = new RandList<Vector2>();

	public GameManager(int width, int height, int tile_width, Viewport viewport, Level level, SpriteBatch batch)
			throws IllegalPositionException {
		NoiseManager.getInstance().clearAllListeners();
		BulletManager.getInstance().clearAllListeners();
		VisionManager.getInstance().clearAllListeners();
		this.path = level.getPath();
		control = new ControlProcessor(viewport);
		Gdx.input.setInputProcessor(control);
		tiled_map = new TmxMapLoader().load(path);
		LevelMap map = new LevelMap(width, height, tile_width, tiled_map);
		AStarPathFinder aStarPathFinder = new AStarPathFinder(map, MAX_SEARCH);
		LinearPathFinder linearPathFinder = new LinearPathFinder(map);
		randArray.add(new Vector2(200, 150));
		randArray.add(new Vector2(700, 700));
		randArray.add(new Vector2(73, 792));
		randArray.add(new Vector2(817, 48));

		for (Vector2 v : level.goonPositions()) {
			if (!map.isValid(new Rectangle(v.x, v.y, 18, 13))) {
				throw new IllegalPositionException();
			}

			GoonView goon_view = new GoonView(batch, LogicAssets.walkAnimation, LogicAssets.hurtWalkAnimation,
					LogicAssets.shootAnimation, LogicAssets.hurtShootAnimation, LogicAssets.deadTextureRegion,
					LogicAssets.exclamationTextureRegion, LogicAssets.interrogationTextureRegion);

			Goon goon = new Goon(new Rectangle(v.x, v.y, 18, 13), map, randArray);
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
			Civilian civilian = new Civilian(new Rectangle(v.x, v.y, 18, 13), map, level.safePositions(), level.safePositions());
			civilian.setAStarPathFinder(aStarPathFinder);
			civilian.setLinearPathFinder(linearPathFinder);
			CivilianController civController = new CivilianController(civilian, civilianView);
			characterControllerList.add(civController);
			civilianIDs.add(civilian.getId()) ;
		}
		Vector2 tp = level.getTarget();
		Target target = new Target(new Rectangle(tp.x,tp.y, 18,13),map,randArray);
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

	/**
	 * Metodo para probar el marshalling de la partida a un xml De usarse,
	 * guarda la posicion de todos los goons y del jugador
	 * 
	 * @throws JAXBException
	 */
	public void marshal(String to) throws JAXBException {
		List<Vector2> goonPositions = new ArrayList<Vector2>();
		List<Vector2> civilPositions = new ArrayList<Vector2>();
		Position playerPosition = new Position(playerController.position());
		Position targetPosition = new Position(targetController.position()) ;
		for (CharacterController<?, ?> c : characterControllerList) {
			if (c instanceof GoonController) {
				goonPositions.add(c.position());
			} else if (c instanceof CivilianController) {
				civilPositions.add(c.position());
			}
		}
		Level l = new Level(path, Position.vectorToPosition(goonPositions), Position.vectorToPosition(civilPositions),
				Position.vectorToPosition(randArray),playerPosition,targetPosition);
		JAXBContext context = JAXBContext.newInstance(Level.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.marshal(l, new File(to));
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
