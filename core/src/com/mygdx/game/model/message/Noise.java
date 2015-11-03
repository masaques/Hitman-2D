

package com.mygdx.game.model.message;

import java.io.Serializable;
import com.mygdx.game.model.message.NoiseListener;

import com.badlogic.gdx.math.Vector2;
/**
 * WIP
 * This class represents all noises emitted by the player and NPCs
 * Every time a player shoots or starts running must emit a noise
 * TODO : Must add proper behavior in Character-related classes
 * TODO : Shoots must have a distinctive property, causing greater disturbance in NPCs
 * @author masaques
 * @author traies
 *
 */

public class Noise implements Message<NoiseListener>, Comparable<Noise>, Serializable{
	private Vector2 source ;
	private double effectiveRange ;
	private boolean isShot ;
	
	public Noise(Vector2 source,double effectiveRange, boolean isShot) {
		this.source=source ;
		this.effectiveRange = effectiveRange ;
		this.isShot= isShot ;
	}
	
	public Vector2 getPosition(){
		return new Vector2(this.source);
	}
	
	public double getRange(){
		return this.effectiveRange;
	}
	
	public boolean isShot() {
		return isShot ;
	}
	
	@Override
	public void notify(NoiseListener l) {
		if (l.getPosition().dst(source) <= getRange()) {
			l.addNoise(this);
		}
	}
	@Override
	public int compareTo(Noise other){
		
		if (this.getRange() > other.getRange()) {
			return 1;
		}
		else if ( this.getRange() == other.getRange()) {
			return 0;
		}
		else {
			return -1;
		}
		
	}
}
