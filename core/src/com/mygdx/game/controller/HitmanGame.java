
package com.mygdx.game.controller;

import com.mygdx.game.view.screens.menu.view.MainMenu;
import com.badlogic.gdx.Game;
//import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Screen;
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

	public Stage stage;

	@Override
	public void create() {

		setScreen(new MainMenu(this));

	}

	public void render() {
		super.render();

	}
}
