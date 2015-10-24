
package com.mygdx.game;

/**
 * WIP
 * Control system for emitted noises
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
