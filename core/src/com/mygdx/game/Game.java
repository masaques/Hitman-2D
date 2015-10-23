package com.mygdx.game;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
/**
 * La clase Game ha sido reducida para que solo se ocupe
 * de lo visual.
 * Contiene, ademas, al GameHandler, al cual llama para que 
 * este actualice todo lo necesario.
 * @author traies
 * @author masaques
 */
public class Game extends ApplicationAdapter {
	Texture img;
	TiledMap tiled_map;
	OrthographicCamera camera;
	OrthogonalTiledMapRenderer renderer;
	FPSLogger fps_logger;
	GameManager game_manager ;
	
	@Override
	public void create () {
		int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width,height );
		camera.update();
		tiled_map = new TmxMapLoader().load("assets/test5.tmx");
		renderer = new OrthogonalTiledMapRenderer(tiled_map);
		fps_logger = new FPSLogger();
		game_manager = new GameManager(tiled_map,width,height,32,20) ;
		
	}

	@Override
	public void render () {
		
		fps_logger.log();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        renderer.setView(camera);
        renderer.render();
        game_manager.updateModel();
        game_manager.updateView();

        
	}
}
