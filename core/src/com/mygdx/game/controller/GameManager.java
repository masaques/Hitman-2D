package com.mygdx.game.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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
import com.mygdx.game.model.character.AStarPathFinder;
import com.mygdx.game.model.character.Civilian;
import com.mygdx.game.model.character.Goon;
import com.mygdx.game.model.character.LinearPathFinder;
import com.mygdx.game.model.character.Player;
import com.mygdx.game.model.message.BulletManager;
import com.mygdx.game.model.message.VisionManager;
import com.mygdx.game.model.util.RandList;
import com.mygdx.game.serialization.Level;
import com.mygdx.game.serialization.Position;
import com.mygdx.game.view.assets.BulletView;
import com.mygdx.game.view.assets.CivilianView;
import com.mygdx.game.view.assets.GoonView;
import com.mygdx.game.view.assets.LogicAssets;
import com.mygdx.game.view.assets.PlayerView;

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

	public GameManager(int width, int height, int tile_width, Viewport viewport, Level level, SpriteBatch batch)
			throws IllegalPositionException {
		this.path = level.getPath();
		control = new ControlProcessor(viewport);
		Gdx.input.setInputProcessor(control);
		tiled_map = new TmxMapLoader().load(path);
		LevelMap map = new LevelMap(width, height, tile_width, tiled_map);
		AStarPathFinder aStarPathFinder = new AStarPathFinder(map, MAX_SEARCH);
		LinearPathFinder linearPathFinder = new LinearPathFinder(map);
		List<Vector2> randArray = new RandList<Vector2>();
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
		linearPathFinder = new LinearPathFinder(map);
		BulletManager.getInstance().setMap(map);
		for (Vector2 v : level.civilPositions()) {
			if (!map.isValid(new Rectangle(v.x, v.y, 18, 13))) {
				throw new IllegalPositionException();
			}
			CivilianView civilianView = new CivilianView(batch, LogicAssets.civilianWalkAnimation,
					LogicAssets.civilainHurtWalkAnimation, LogicAssets.civilianDeadTextureRegion,
					LogicAssets.exclamationTextureRegion, LogicAssets.interrogationTextureRegion);
			Civilian civilian = new Civilian(new Rectangle(v.x, v.y, 18, 13), map, randArray, randArray);
			civilian.setAStarPathFinder(aStarPathFinder);
			civilian.setLinearPathFinder(linearPathFinder);
			CivilianController civController = new CivilianController(civilian, civilianView);
			characterControllerList.add(civController);
		}

		BulletManager bulletManager = BulletManager.getInstance();
		BulletView bulletView = new BulletView(viewport.getCamera());
		bulletController = new BulletController(bulletManager, bulletView);
		this.state = GameState.PLAY ;
	}

	public TiledMap getTiledMap() {
		return tiled_map;
	}

	public void update() {
		VisionManager.getInstance().update();
		noiseController.manage();
		bulletController.manage();
		Collections.sort(characterControllerList);

		for (CharacterController<?, ?> c : characterControllerList) {
			c.updateModel();
			c.updateView();
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
		for (CharacterController<?, ?> c : characterControllerList) {
			if (c instanceof GoonController) {
				goonPositions.add(c.position());
			} else if (c instanceof CivilianController) {
				civilPositions.add(c.position());
			}
		}
		Level l = new Level(path, Position.vectorToPosition(goonPositions), Position.vectorToPosition(civilPositions),
				playerPosition);
		JAXBContext context = JAXBContext.newInstance(Level.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.marshal(l, new File(to));
	}
	
	public GameState getState() {
		return state ;
	}
}
