package com.mygdx.game.model.character;

import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.LevelMap;
import com.mygdx.game.model.util.RandList;

import serialization.NPCInformation;

public class Target extends Goon {

	public Target(NPCInformation info, LevelMap map, RandList<Vector2> searchPositions) {
		super(info, map, searchPositions);
	}
	public Target(Rectangle hitBox, LevelMap map, List<Vector2> searchPositions){
		super(hitBox, map, searchPositions);		
	}

}
