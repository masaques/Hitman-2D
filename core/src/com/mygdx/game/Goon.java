/*
 *@author Tomas Raies
 *@date   13 de oct. de 2015
 */

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import serialization.NPCInformation;

/**
 * Los Goon son {@link NPC} con un comportamiento agresivo. 
 */
public class Goon extends NPC {
	/**
	 * Delay entre los disparos del arma.
	 */
	private static final float SHOOTING_DELAY = 3f;
	private Behaviour<NPC> patrolBehaviour;
	private Behaviour<NPC> followBehaviour;
	private Behaviour<NPC> shootBehaviour;
	private Behaviour<NPC> searchBehaviour;
	private Behaviour<NPC> inspectBehaviour;
	private float shootTimer = 0f;
	
	public Goon(Rectangle hitBox, LevelMap map, RandList<Vector2> searchPositions){
		super(hitBox, map);
		patrolBehaviour  = new PatrolBehaviour(searchPositions);
		followBehaviour  = new FollowBehaviour();
		shootBehaviour   = new ShootBehaviour();
		inspectBehaviour = new InspectBehaviour();
	}
	public Goon(NPCInformation info,LevelMap map,RandList<Vector2> searchPositions) {
		super(info, map) ;
		patrolBehaviour  = new PatrolBehaviour(searchPositions);
		followBehaviour  = new FollowBehaviour();
		shootBehaviour   = new ShootBehaviour();
		inspectBehaviour = new InspectBehaviour();
	}
	@Override
	public void alarm(Context context) {
		searchBehaviour = null;
		if (context.playerIsVisible()){
			if (shootTimer > SHOOTING_DELAY) {
				System.out.println("BANG!!!");
				resetShootTimer();
//				shootBehaviour.behave(this, context);
			}
			else {
				followBehaviour.behave(this, context);
			}
		}
	}
	@Override
	public void suspicious(Context context) {
		if (searchBehaviour == null) {
			searchBehaviour = new SearchBehaviour (getPosition(), getMap());
		}
		if (context.canHear()) {
			inspectBehaviour.behave(this, context);
			searchBehaviour = null;
		}
		else if (!isMoving()) {
			searchBehaviour.behave(this, context);
		}
	}
	@Override
	public void calm(Context context) {
		patrolBehaviour.behave(this, context);
		searchBehaviour = null;
	}
	@Override
	public void update(){
		shootTimer += Gdx.graphics.getDeltaTime();
		super.update();
	}
	public void resetShootTimer() {
		shootTimer = 0f;
	}
}
