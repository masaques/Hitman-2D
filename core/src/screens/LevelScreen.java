package screens;

import java.io.IOException;

/**
 *  Screen del nivel principal donde se llevara a cabo el juego. Posee la información completa
 *  del juego y se encarga del renderizado.
 *  
 *  @author jcaracciolo
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ControlProcessor;
import com.mygdx.game.GameManager;
import com.mygdx.game.HitmanGame;

import serialization.GameSerializer;

public class LevelScreen implements Screen{

	private HitmanGame game;
	
	
	private OrthographicCamera camera = new OrthographicCamera();
	private Viewport gameport;

	
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	
	ControlProcessor input;
	
	FPSLogger fps_logger =new FPSLogger();
	
	GameManager gameManager;
	 
	public LevelScreen(HitmanGame game){
		this.game = game;
		gameport = new FitViewport(864, 864,camera);
		gameManager = new GameManager(864,864,32,20);
//		try {
//			gameManager = GameSerializer.create("prueba") ;
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		renderer = new OrthogonalTiledMapRenderer(gameManager.getTiledMap());
		camera.position.set(gameport.getWorldWidth()/2,gameport.getWorldHeight()/2,0);
	}
	
	public void update(float dt){
		
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		fps_logger.log();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gameManager.updateModel();
		game.batch.setProjectionMatrix(camera.combined);
	
		camera.update();
        renderer.setView(camera);
        renderer.render();
        gameManager.updateModel();
        gameManager.updateView();
		
		
	}

	@Override
	public void resize(int width, int height) {
		gameport.update(width, height);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}