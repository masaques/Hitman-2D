package com.mygdx.game.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
	public Stage stage;
	
	public HitmanGame(){
		super();
	}
	
	@Override
	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		HitmanSkin hitmanSkin = new HitmanSkin();
		
		//MenuData.FONT.draw(batch, "probando", 5, 5);
		//MenuData.TITLE_FONT.draw(batch, "H I T M A N", 0, 0);
		MenuUI menu = new MenuUI(hitmanSkin);
		
		for(TextButton each : menu.getMenuButtons() ){
			stage.addActor(each);
		}
		
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
