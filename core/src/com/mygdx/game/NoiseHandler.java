package com.mygdx.game;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.math.Vector2;

public class NoiseHandler implements Listener {

	private NPC owner;
	private Set<Noise> noiseSet;
	
	public NoiseHandler(NPC owner) {
		this.owner = owner;
		this.noiseSet = new HashSet<Noise>();
	}
	
	@Override
	public boolean handleMessage(Message message) {
		if (message.getClass()!=Noise.class){
			throw new WrongMessageException();
		}
		Noise noise = (Noise)message;
		noiseSet.add(noise);
		return true;
	}
	
	public Noise getInbox(){
		Noise maxNoise = null;
		double max = 0;
		for (Noise n:noiseSet){
			if (n.getRange() > max) {
				n = maxNoise;
				max = n.getRange();
			}
		}
		noiseSet.clear();
		return maxNoise;
	}
	public Vector2 getPosition(){
		return owner.getPosition();
	}

}
