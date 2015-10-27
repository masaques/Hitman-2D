package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;


public class PatrolState implements State<NPC> {
	private RandList<Vector2> searchPositions;
	
	public PatrolState(RandList<Vector2> searchPositions) {
		this.searchPositions = searchPositions;
	}

	/*
	 * Va a circular por las posiciones aleatoriamente. Nunca devuelve done() == true porque es 
	 * la rutina por defecto del npc.
	 */
	
	@Override
	public ActionRequest<NPC> updateState(StateMachine<NPC> owner, Context context) {
		ActionRequest<NPC> actionRequest;
		
		if (!context.isMoving()) {
			actionRequest = new MoveRequest(searchPositions.get(0), false);
		}
		else {
			actionRequest = new NullRequest<NPC>();
		}
		return actionRequest;
	}

}
