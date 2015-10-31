package com.mygdx.game;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

public class SearchBehaviour implements Behaviour<NPC> {
	private List<Vector2> searchPositions;
	
	public SearchBehaviour(Vector2 npcPosition, LevelMap map) {
		/*
		 * Busca posiciones validas en un radio de 2 tiles.
		 */
		searchPositions = map.findRandomValidPositions(npcPosition, 2);
	}
	
	@Override
	public void behave(NPC npc, Context context) {
		if (searchPositions.size() <= 0) {
			return;
		}
		npc.moveTo(searchPositions.get(0), false);
	}

}
