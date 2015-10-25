package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;


public class PatrolState implements State {
	private RandArray<Vector2> searchPositions;
	
	public PatrolState(RandArray<Vector2> searchPositions) {
		this.searchPositions = searchPositions;
	}

	/*
	 * Va a circular por las posiciones aleatoriamente. Nunca devuelve done() == true porque es 
	 * la rutina por defecto del npc.
	 */
	
	@Override
	public ActionRequest updateState(StateMachine owner, Context context) {
		ActionRequest actionRequest = new ActionRequest();
		
		if (!context.isMoving()) {
			System.out.println("algo");
			actionRequest.setRequest(ActionRequest.REQUEST_MOVETO);
			actionRequest.setRunning(false);
			actionRequest.setPosition(searchPositions.get());
			actionRequest.setLinear(false);
		}
		return actionRequest;
	}

}
