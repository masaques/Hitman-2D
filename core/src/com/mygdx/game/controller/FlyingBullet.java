package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class FlyingBullet {
	private Vector2 origin;
	private Vector2 end;
	private float timer;
	
	public FlyingBullet(Vector2 origin, Vector2 end) {
		this.origin = new Vector2(origin);
		this.end = new Vector2(end);
	}
	
	public void update() {
		timer += Gdx.graphics.getDeltaTime();
	}
	
	public float getTimer() {
		return timer;
	}
	
	public Vector2 getOrigin() {
		return new Vector2(origin);
	}
	
	public Vector2 getEnd() {
		return new Vector2(end);
	}
}
