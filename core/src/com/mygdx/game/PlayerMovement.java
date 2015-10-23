package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
/**
 * Pequeña clase que devuelve ControlProcessor en getPlayerMovement()
 * para informarle a PlayerHandler lo que tiene que hacer
 * @author masaques
 *
 */
public class PlayerMovement {
	/**
	 * TODO Deberian haber mas estados, tales como ver si se emitio un sonido
	 * o un disparo, etc
	 */
	private boolean running ;
	private Vector2 direction ;
	public PlayerMovement (Vector2 direction,boolean running) {
		this.running = running ;
		this.direction = direction ;
	}
	public boolean isRunning(){
		return this.running;
	}
	public Vector2 getDirection(){
		return this.direction;
	}
}
