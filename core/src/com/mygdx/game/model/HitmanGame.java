package com.mygdx.game.model;



import com.mygdx.game.view.screens.menu.view.MenuUI;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * <p> Clase del juego, {@link Game} es un {link ApplicationListener} (clase que se encarga del renderizado
 * y ejecucion de libgdx)  que delega el trabajo a una {@link Screen} mediante el metodo <i>setScreen</i>.
 * </p>
 * 
 * 
 * @see com.badlogic.gdx.Game
 * @see com.badlogic.gdx.ApplicationListener
 * @see com.badlogic.gdx.Screen
 *
 */

public class HitmanGame extends Game{

	private SpriteBatch batch;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new MenuUI(this));
		
	
	}
	
	public SpriteBatch getBatch(){
		return batch;
	}

	public void render () {
		super.render();
		
	}


		
}
