package com.mygdx.game.model;


import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.view.screens.menu.view.HitmanButton;
import com.mygdx.game.view.screens.menu.view.HitmanSkin;
import com.mygdx.game.view.screens.menu.view.MenuUI;

/**
 * <p> Clase del juego, {@link Game} es un {link ApplicationListener} (clase que se encarga del renderizado
 * y ejecucion de libgdx)  que delega el trabajo a una {@link Screen} mediante el metodo <i>setScreen</i>.
 * </p>
 * 
 * @author jcaracciolo
 * @author kcortesrodrigue
 * 
 * @see com.badlogic.gdx.Game
 * @see com.badlogic.gdx.ApplicationListener
 * @see com.badlogic.gdx.Screen
 *
 */

public class HitmanGame extends Game{

	public SpriteBatch batch;
	public MenuUI menuui;
	public Stage stage;
	
	public HitmanGame(){
		super();
	}
	
	@Override
	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		HitmanSkin hitmanSkin = new HitmanSkin();
		//Lo que esta a partir de aca tendria que estar en otro lado
		HitmanButton btnPlay = new HitmanButton("Play",hitmanSkin);
		btnPlay.setOnClickListener(new ClickListener(){
			
			public void clicked(InputEvent event, float x, float y){
				Gdx.graphics.setDisplayMode(800,600,false);
			}
			
		});
			
		
	}
	
	public void render () {
		super.render();
		//batch.begin();
		//Texture background = new Texture(Gdx.files.internal("assets/background.png"));
		//TextureRegion backgroundRegion = new TextureRegion(background, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//batch.draw(backgroundRegion, 0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		stage.draw();
		
		//batch.end();
	}


		
}
