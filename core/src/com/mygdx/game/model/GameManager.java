package com.mygdx.game.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.controller.BulletController;
import com.mygdx.game.controller.ControlProcessor;
import com.mygdx.game.controller.GoonController;
import com.mygdx.game.controller.NPCController;
import com.mygdx.game.controller.NoiseController;
import com.mygdx.game.controller.PlayerController;
import com.mygdx.game.model.character.AStarPathFinder;
import com.mygdx.game.model.character.Goon;
import com.mygdx.game.model.character.LinearPathFinder;
import com.mygdx.game.model.character.NPC;
import com.mygdx.game.model.character.Player;
import com.mygdx.game.model.message.BulletManager;
import com.mygdx.game.model.message.NoiseManager;
import com.mygdx.game.model.message.VisionManager;
import com.mygdx.game.model.util.RandList;
import com.mygdx.game.view.assets.CharacterView;
import com.mygdx.game.view.assets.LogicAssets;
import com.mygdx.game.view.assets.NPCView;
import com.mygdx.game.view.assets.PlayerView;

import serialization.Dumpeable;
import serialization.GameInformation;
import serialization.Information;
import serialization.Level;
import serialization.NPCInformation;
import serialization.Position;
import serialization.CharacterInformation;
import serialization.GameSerializer;

/**
 * Esta clase es una modularizacion del contenido previo de Game
 * Es quien contiene el mapa y todos los personajes, como asi a los diferentes 
 * handlers para administrarlos
 * Se ocupa de actualizar el modelo y, luego, de actualizar la vista, tal como
 * dice MVC
 * @author masaques
 * @author traies
 */
public class GameManager implements Dumpeable {
	
	protected static final int MAX_SEARCH = 100 ;
	/**
	 * Duracion de un frame en la animacion (en segundos).
	 */
	private LevelMap map ;
	private TiledMap tiled_map ;
	private ControlProcessor control ;
	private BulletController bulletController = new BulletController();
	private PlayerController playerController ;
	private List<NPCController> npcController = new ArrayList<NPCController>();
	private NoiseController noiseController = new NoiseController() ;
	
	public GameManager(int width,int height,int tile_width, String path) throws JAXBException{
		control = new ControlProcessor() ;
		Gdx.input.setInputProcessor(control);
		tiled_map= new TmxMapLoader().load(path);
		LevelMap map = new LevelMap(width,height, tile_width,tiled_map);
		AStarPathFinder  aStarPathFinder  = new AStarPathFinder(map, MAX_SEARCH);
		LinearPathFinder linearPathFinder = new LinearPathFinder(map);
		RandList<Vector2> randArray = new RandList<Vector2>();
		randArray.add(new Vector2(200, 150));
		randArray.add(new Vector2(700,700));
		randArray.add(new Vector2(73,792));
		randArray.add(new Vector2(817,48));
		SpriteBatch batch = new SpriteBatch();
		
		for(int i=0; i< 3; i++){
			NPCView goon_view = new NPCView(
					batch,
					LogicAssets.walkAnimation,
					LogicAssets.hurtWalkAnimation,
					LogicAssets.shootAnimation,
					LogicAssets.hurtShootAnimation,
					LogicAssets.deadTextureRegion,
					LogicAssets.exclamationTextureRegion,
					LogicAssets.interrogationTextureRegion
					);
			
			Goon goon = new Goon(new Rectangle(40,40, 18,13),map, randArray);
			goon.setAStarPathFinder(aStarPathFinder);
			goon.setLinearPathFinder(linearPathFinder);
			NPCController controller = new GoonController(goon, goon_view);
			npcController.add(controller);
		}
		
		PlayerView player_view = new PlayerView(
				batch,
				LogicAssets.playerWalkAnimation,
				LogicAssets.playerHurtWalkAnimation,
				LogicAssets.playerShootAnimation,
				LogicAssets.playerHurtShootAnimation,
				LogicAssets.playerDeadTextureRegion
				);
		Player player = new Player(new Rectangle(50,50,18,13),map);
		playerController = new PlayerController(player,control, player_view) ;
		
		linearPathFinder = new LinearPathFinder(map);
		BulletManager.getInstance().setMap(map);

		
		/**
		 * Todo este bloque comentado sirve para probar el 
		 * marshalling de los datos creados arriba a un XML que representa al
		 * nivel al inicializarse
		 */
		
		
//		List<Vector2> goonPositions = new ArrayList<Vector2>() ;
//		Position playerPosition = new Position(playerController.position());
//		
//		for(NPCController c : npcController) {
//			goonPositions.add(c.position()) ;
//		}
//		
//		Level l = new Level (mapPath,Position.vectorToPosition(goonPositions),playerPosition) ;
//		JAXBContext context = JAXBContext.newInstance(Level.class) ;
//		Marshaller marshaller = context.createMarshaller();
//		marshaller.marshal(l, new File("test.xml"));
	}
	
	public GameManager (int width, int height, int tileWidth, Level level) {
		
	}
	
	
	public TiledMap getTiledMap() {
		return tiled_map;
	}
	
	public void update(){
		if(control.requestSave()) {
			try {
				GameSerializer.save(this,"prueba");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		VisionManager.getInstance().update();
		noiseController.manage();
		bulletController.manage();
		for (NPCController g : npcController){
			g.updateModel();
			g.updateView();
		}
		playerController.control();
	}
	
	@Override
	public GameInformation dump() {
		return null;
	}
}
