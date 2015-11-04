
package com.mygdx.game.model.message;

import com.badlogic.gdx.scenes.scene2d.ui.List;

/**
 * Clase singleton para el manejo de los sonidos
 * @author masaques
 * @author traies
 *
 */


public class NoiseManager extends MessageManager<NoiseListener, Noise>{
	private static final NoiseManager INSTANCE = new NoiseManager();
	
	private NoiseManager(){
		super();
	}
	
	public static NoiseManager getInstance() {
		return INSTANCE;
	}
}
