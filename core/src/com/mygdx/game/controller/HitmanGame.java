
package com.mygdx.game.controller;

import com.mygdx.game.view.screens.menu.view.MainMenu;

import com.badlogic.gdx.Game;
//import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * <p>
 * Clase del juego, {@link Game} es un {link ApplicationListener} (clase que se
 * encarga del renderizado y ejecucion de libgdx) que delega el trabajo a una
 * {@link Screen} mediante el metodo <i>setScreen</i>.
 * </p>
 * 
 * @author jcaracciolo
 * 
 * @see com.badlogic.gdx.Game
 * @see com.badlogic.gdx.ApplicationListener
 * @see com.badlogic.gdx.Screen
 *
 */

public class HitmanGame extends Game {


	private SpriteBatch batch;

	@Override
	public void create() {
	     batch = new SpriteBatch();
		setScreen(new MainMenu(this));

	}

	public void render() {
		super.render();

	}
	
	public SpriteBatch getBatch(){
    	return batch;
    }
}
