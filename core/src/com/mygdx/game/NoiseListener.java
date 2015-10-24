package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public interface NoiseListener extends Listener {
	
	public void addNoise(Noise message);
	public Vector2 getPosition();

}
