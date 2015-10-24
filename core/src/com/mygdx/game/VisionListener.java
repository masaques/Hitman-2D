package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public interface VisionListener extends Listener {
	public Vector2 getPosition() ;
	public Vector2 getDirection() ;
	public float visualRange() ;
	public float visualAngle() ;
	public void addPlayer(Vector2 position) ;
}
