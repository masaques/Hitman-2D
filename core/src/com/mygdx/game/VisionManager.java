package com.mygdx.game;

/**
 * Clase singleton para el manejo de la vision
 * @author masaques
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
