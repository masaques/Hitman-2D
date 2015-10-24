package com.mygdx.game;


import com.badlogic.gdx.Game;
//import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.maps.tiled.TiledMap;
//import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;

import screens.LevelScreen;

public class HitmanGame extends Game{

	public SpriteBatch batch;
	//private Texture img;
	//private TiledMap map;
	//private OrthoCachedTiledMapRenderer renderer;
	
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		
		setScreen(new LevelScreen(this));
		// TODO Auto-generated method stub
		
	}
	
	public void render () {
		super.render();
	}


		
}
