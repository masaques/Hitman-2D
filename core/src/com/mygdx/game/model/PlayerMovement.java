package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
/**
 * Pequeña clase que devuelve ControlProcessor en {@link com.mygdx.game.controller.ControlProcessor#getPlayerMovement() getPlayerMovement}
 * para informarle a PlayerHandler lo que tiene que hacer
 * 
 * @see Player
 * @see ControlPocessor
 */
public class PlayerMovement {
	private boolean running ;
	private Vector2 direction ;
	private Vector2 lookDirection;
	private boolean shoot;
	
	public PlayerMovement (Vector2 direction,boolean running, Vector2 lookDirection, boolean shoot) {
		this.running = running ;
		this.direction = direction ;
		this.lookDirection = lookDirection;
		this.shoot = shoot;
	}
	public boolean isRunning(){
		return this.running;
	}
	public Vector2 getDirection(){
		return this.direction;
	}
	public Vector2 getLookDirection(){
		return lookDirection;
	}
	public boolean isShooting(){
		return shoot;
	}
}
