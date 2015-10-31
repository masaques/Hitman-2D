package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;


public class PatrolBehaviour implements Behaviour<NPC> {
	private RandList<Vector2> patrolPositions;
	
	public PatrolBehaviour(RandList<Vector2> patrolPositions) {
		this.patrolPositions = patrolPositions;
	}

	/*
	 * Va a circular por las posiciones aleatoriamente. Nunca devuelve done() == true porque es 
	 * la rutina por defecto del npc.
	 */
	
	@Override
	public void behave(NPC npc, Context context) {
		if (!npc.isMoving()) {
			npc.moveTo(patrolPositions.get(0), false);
		}
	}

}
