package com.mygdx.game.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.mygdx.game.view.assets.NPCView;
import com.mygdx.game.view.assets.PlayerView;

import serialization.Dumpeable;
import serialization.GameInformation;
import serialization.Information;
import serialization.NPCInformation;
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
	private static final float FRAME_DURATION = 0.05f;
	private LevelMap map ;
	private PlayerController player_manager ;
	
	
	
	private TiledMap tiled_map ;
	private ControlProcessor control ;
	private String mapPath = "assets/test5.tmx";
	private BulletController bulletController = new BulletController();
	private List<NPCController> npcController = new ArrayList<NPCController>();
	private NoiseController noiseController = new NoiseController() ;
	
	public GameManager(int width,int height,int tile_width,int goons){
		control = new ControlProcessor() ;
		Gdx.input.setInputProcessor(control);
		tiled_map= new TmxMapLoader().load(mapPath);
		LevelMap map = new LevelMap(width,height, tile_width,tiled_map);
		AStarPathFinder  aStarPathFinder  = new AStarPathFinder(map, MAX_SEARCH);
		LinearPathFinder linearPathFinder = new LinearPathFinder(map);
		RandList<Vector2> randArray = new RandList<Vector2>();
		randArray.add(new Vector2(200, 150));
		randArray.add(new Vector2(700,700));
		randArray.add(new Vector2(73,792));
		randArray.add(new Vector2(817,48));
		SpriteBatch batch = new SpriteBatch();
		Animation walkAnimation = makeAnimation("assets/goon_straight_walk.png", 18,13,15, FRAME_DURATION);
		Animation hurtWalkAnimation = makeAnimation("assets/goon_hit.png", 18,13,15, FRAME_DURATION);
		Animation shootAnimation = makeAnimation("assets/goon_shooting_walk.png", 18,20,15,FRAME_DURATION);
		Animation hurtShootAnimation = makeAnimation("assets/goon_shooting_hit.png",18, 20,15,FRAME_DURATION);
		Texture deadTexture = new Texture(Gdx.files.internal("assets/goon_dead.png"));
		TextureRegion deadTextureRegion = new TextureRegion(deadTexture);
		Texture interrogationTexture = new Texture(Gdx.files.internal("assets/sprite_interrogation.png"));
		TextureRegion interrogationTextureRegion = new TextureRegion(interrogationTexture);
		Texture exclamationTexture = new Texture(Gdx.files.internal("assets/sprite_exclamation.png"));
		TextureRegion exclamationTextureRegion = new TextureRegion(exclamationTexture);
		for(int i=0; i< 1; i++){
			
			
			NPCView goon_view = new NPCView(
					batch,
					walkAnimation,
					hurtWalkAnimation,
					shootAnimation,
					hurtShootAnimation,
					deadTextureRegion,
					exclamationTextureRegion,
					interrogationTextureRegion
					);
			
			Goon goon = new Goon(new Rectangle(40,40, 18,13),map, randArray);
			goon.setAStarPathFinder(aStarPathFinder);
			goon.setLinearPathFinder(linearPathFinder);
			NPCController controller = new GoonController(goon, goon_view);
			npcController.add(controller);
		}
		
		Animation playerWalkAnimation         = makeAnimation("assets/hitman_straight_walk.png", 18,13,15, FRAME_DURATION);
		Animation playerHurtWalkAnimation     = makeAnimation("assets/hitman_hit.png", 18,13,15, FRAME_DURATION);
		Animation playerShootAnimation        = makeAnimation("assets/hitman_shooting_walk.png", 18,20,15,FRAME_DURATION);
		Animation playerHurtShootAnimation    = makeAnimation("assets/hitman_shooting_hit.png",  18,20,15,FRAME_DURATION);
		Texture playerDeadTexture             = new Texture(Gdx.files.internal("assets/hitman_dead.png"));
		TextureRegion playerDeadTextureRegion = new TextureRegion(playerDeadTexture);
		
		
		PlayerView player_view = new PlayerView(
				batch,
				playerWalkAnimation,
				playerHurtWalkAnimation,
				playerShootAnimation,
				playerHurtShootAnimation,
				playerDeadTextureRegion
				);
		Player player = new Player(new Rectangle(50,50,18,13),map);
		player_manager = new PlayerController(player,control, player_view) ;
		
		linearPathFinder = new LinearPathFinder(map);
		BulletManager.getInstance().setMap(map);
	}
	
	public GameManager (GameInformation g) {
//		
//		
//		control = new ControlProcessor() ;
//		Gdx.input.setInputProcessor(control);
//		tiled_map= new TmxMapLoader().load(g.getMap());
//		LevelMap map = new LevelMap(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),32,tiled_map);
//		AStarPathFinder  aStarPathFinder  = new AStarPathFinder(map, MAX_SEARCH);
//		LinearPathFinder linearPathFinder = new LinearPathFinder(map);
//		RandList<Vector2> randArray = new RandList<Vector2>();
//		randArray.add(new Vector2(200, 150));
//		randArray.add(new Vector2(700,700));
//		randArray.add(new Vector2(73,792));
//		randArray.add(new Vector2(817,48));
//		VisionManager visionManager = VisionManager.getInstance();
//		NoiseManager  noiseManager  = NoiseManager.getInstance();
//		for (NPCInformation info : g.getGoonInfo()) {
//			NPCView goon_view = new NPCView("assets/hitman_walk.png",
//					"assets/goon_sprite_hurt.png",
//					"assets/goon_sprite_dead.png",
//					18, 13, 15);
//			goon = new Goon(info,map,randArray) ;
//			goon.setAStarPathFinder(aStarPathFinder);
//			goon.setLinearPathFinder(linearPathFinder);
//			visionManager.addListener(goon);
//			noiseManager.addListener(goon);
//		
//		}
//		PlayerView player_view = new PlayerView("assets/hitman_walk.png",
//				"assets/goon_sprite_hurt.png",
//				"assets/goon_sprite_dead.png",
//				 18, 13, 15);
//		Player player = new Player(g.getPlayerInfo(),map);
//		player_manager = new PlayerController(player,control, player_view) ;
//		
//		linearPathFinder = new LinearPathFinder(map);
//		BulletManager.getInstance().setMap(map);
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
//		NoiseManager.getInstance().update();
		noiseController.manage();
		bulletController.manage();
		for (NPCController g : npcController){
			g.updateModel();
			g.updateView();
		}
		player_manager.control();
	}
	
	@Override
	public GameInformation dump() {
		List<NPCInformation> goonInfo = new ArrayList<NPCInformation>();
		CharacterInformation playerInfo ;
//		for (NPC g: goon_set) {
//			goonInfo.add(g.dump());
//		}
		/**
		Player player = player_manager.getModel();
		playerInfo= player.dump();
		*/
		//return new GameInformation(goonInfo,playerInfo,mapPath) ;
		return null;
	}
	
	/**
	 * Crea una animacion a partir de un path, las dimensiones del sprite y la cantidad de frames.
	 * @param path
	 * @param spriteWidth
	 * @param spriteHeight
	 * @return
	 */
	public Animation makeAnimation(String path, int spriteWidth, int spriteHeight, int length, float frameDuration) {
		
		Texture normalSpriteTexture = new Texture(Gdx.files.internal(path));
		TextureRegion[][] tmp = TextureRegion.split(normalSpriteTexture, spriteWidth, spriteHeight);
		TextureRegion[] normalWalkFrames = new TextureRegion[length];
		for(int i=0; i<length;i++){
			normalWalkFrames[i] = tmp[0][i];
		}
		return new Animation(frameDuration,normalWalkFrames);
	}
	
	

}
