/*
 *@author Tomas Raies
 *@date   13 de oct. de 2015
 */

package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Los Goon son {@link NPC} con un comportamiento agresivo. 
 */
public class Goon extends NPC {
	
	public Goon(Rectangle hitBox, LevelMap map, RandArray<Vector2> searchPositions){
		super(hitBox, map);
		setStateMachine(new GoonStateMachine(searchPositions));
	}

}
