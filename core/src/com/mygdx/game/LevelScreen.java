package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LevelScreen implements Screen{

	Texture img;
	TiledMap tiled_map;
	OrthographicCamera camera;
	OrthogonalTiledMapRenderer renderer;
	Viewport viewport;
	int width;
	int height;
	
	
	public LevelScreen(int width, int height,TiledMap map) {
		this.width = width;
        this.height = height;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width,height);
		camera.update();
		tiled_map = map;
		renderer = new OrthogonalTiledMapRenderer(tiled_map);
		viewport = new FitViewport(width, height);
		
	}

	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float arg0) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		/**
		 * ACA DEBERIA IR TODO LO QUE SE REALIZA CON EL BATCH
		 * game.batch.setPojectionMatrix(camera.combined);
		 * game.batch.begin();
		 * game.batch.end();
		 * 
		 */
        camera.update();
        renderer.setView(camera);
        renderer.render();
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

}
