package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;


public class PatrolStrategy implements Strategy {
	private RandArray<Vector2> searchPositions;
	
	public PatrolStrategy(RandArray<Vector2> searchPositions) {
		this.searchPositions = searchPositions;
	}

	/*
	 * Va a circular por las posiciones aleatoriamente. Nunca devuelve done() == true porque es 
	 * la rutina por defecto del npc.
	 */
	
	@Override
	public ActionRequest behave(Context context) {
		ActionRequest actionRequest = new ActionRequest();
		if (!context.isMoving()) {
			actionRequest.setRequest(ActionRequest.REQUEST_MOVETO);
			actionRequest.setRunning(false);
			actionRequest.setPosition(searchPositions.get());
		}
		return actionRequest;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
