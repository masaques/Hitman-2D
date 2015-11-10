package com.mygdx.game.model.character;

import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.LevelMap;



public class Target extends Goon {

	
	public Target(Rectangle hitBox, LevelMap map, List<Vector2> searchPositions){
		super(hitBox, map, searchPositions);		
	}

}
