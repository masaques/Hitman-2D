package com.mygdx.game.model.message;

/**
 * Clase singleton para el manejo de la vision
 * @author masaques
 * 
 * @see MessageManager
 * @see VisionListener
 * @see Vision
 *
 */


public class VisionManager extends MessageManager<VisionListener, Vision> {	
	private static final VisionManager INSTANCE = new VisionManager();
	
	private VisionManager(){
		super();
	}
	
	public static VisionManager getInstance() {
		return INSTANCE;
	}

}
