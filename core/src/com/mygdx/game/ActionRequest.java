package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class ActionRequest {
	public static final int REQUEST_NOTHING = 0;
	public static final int REQUEST_SHOOT = 1;
	public static final int REQUEST_MOVETO = 2;
	private boolean running;
	private int request;
	private Vector2 position;
	
	public void setPosition(Vector2 position){
		this.position = position;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
	public void setRequest(int request) {
		this.request = request;
	}
	public Vector2 getPosition(){
		return position;
	}
	public boolean getRunning() {
		return running;
	}
	public int getRequest() {
		return request;
	}
	
}
