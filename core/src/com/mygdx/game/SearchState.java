package com.mygdx.game;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

public class SearchState implements State<NPC> {
	private List<Vector2> searchPositions;
	
	public SearchState(Vector2 npcPosition, LevelMap map) {
		/*
		 * Busca posiciones validas en un radio de 4 tiles.
		 */
		searchPositions = map.findRandomValidPositions(npcPosition, 4);
	}
	
	@Override
	public ActionRequest<NPC> updateState(StateMachine<NPC> owner, Context context) {
		if (searchPositions.size() <= 0 || context.isMoving()) {
			return new NullRequest<NPC>();
		}
		return new MoveRequest(searchPositions.get(0), false);
	}

}
