package com.mygdx.game.model.message;

import com.badlogic.gdx.math.Vector2;

public interface VisionListener extends Listener {
	public Vector2 getCenter();

	public Vector2 getMoveDirection();

	public float visualRange();

	public float visualAngle();

	public void addPlayer(Vector2 position);
}
