package com.mygdx.game.model;


import javax.xml.bind.JAXBException;
import com.mygdx.game.view.screens.LevelScreen;
import com.mygdx.game.view.screens.menu.view.HitmanSkin;
import com.mygdx.game.view.screens.menu.view.MenuUI;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;


/**
 * <p> Clase del juego, {@link Game} es un {link ApplicationListener} (clase que se encarga del renderizado
 * y ejecucion de libgdx)  que delega el trabajo a una {@link Screen} mediante el metodo <i>setScreen</i>.
 * </p>
 * 
 * @author jcaracciolo
 * 
 * @see com.badlogic.gdx.Game
 * @see com.badlogic.gdx.ApplicationListener
 * @see com.badlogic.gdx.Screen
 *
 */

public class HitmanGame extends Game{

	public SpriteBatch batch;
	public Stage stage;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		
		
		
		setScreen(new MenuUI(this));
		
	
	}
	
	

	public void render () {
		super.render();
		
	}


		
}
