package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/*
 * TODO existe una implementacion mas nueva en el otro repositorio.
 */


public class LinearPathFinder implements PathFinder {
	private LevelMap map;
	
	public LinearPathFinder(LevelMap map) {
		this.map = map;
	}
	
	public Path findPath(Movable movable, Vector2 startPos, Vector2 finalPos){

		if (!map.isValid(startPos, finalPos)){
			return null;
		}
		
		Path path = new Path(); 
		path.prependStep(new Vector2(finalPos));
		
		return path;
	}
}