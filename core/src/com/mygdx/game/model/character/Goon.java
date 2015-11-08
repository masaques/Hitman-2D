/*
 *@author Tomas Raies
 *@date   13 de oct. de 2015
 */

package com.mygdx.game.model.character;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.LevelMap;
import com.mygdx.game.model.character.behaviour.Behaviour;
import com.mygdx.game.model.character.behaviour.FollowBehaviour;
import com.mygdx.game.model.character.behaviour.InspectBehaviour;
import com.mygdx.game.model.character.behaviour.PatrolBehaviour;
import com.mygdx.game.model.character.behaviour.SearchBehaviour;
import com.mygdx.game.model.character.behaviour.ShootBehaviour;
import com.mygdx.game.model.message.Bullet;
import com.mygdx.game.model.message.BulletManager;
import com.mygdx.game.model.message.Noise;
import com.mygdx.game.model.message.NoiseManager;
import com.mygdx.game.model.message.NoiseType;
import com.mygdx.game.model.message.VisionManager;
import com.mygdx.game.model.util.RandList;

import serialization.NPCInformation;

/**
 * Los Goon son {@link NPC} con un comportamiento agresivo. 
 */
public class Goon extends NPC implements Aggressive{
	/**
	 * Delay entre los disparos del arma.
	 */
	private static final float SHOOTING_DELAY = 3f;
	private Behaviour<NPC> patrolBehaviour;
	private Behaviour<NPC> followBehaviour;
	/**
	 * TODO Deprecated?
	 */
	private Behaviour<NPC> shootBehaviour;
	private Behaviour<NPC> searchBehaviour;
	private Behaviour<NPC> inspectBehaviour;
	private float shootTimer = 0f;
	private boolean isShooting;
	
	public Goon(Rectangle hitBox, LevelMap map, List<Vector2> searchPositions){
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
		if (context.playerIsVisible()){
			if (shootTimer > SHOOTING_DELAY) {
				resetShootTimer();
				shoot();
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
		
	}
	@Override
	public void update(){
		if (getState() != NPCState.SUSPICIOUS) {
			searchBehaviour = null;
		}
		shootTimer += Gdx.graphics.getDeltaTime();
		isShooting = false;
		super.update();
	}
	public void resetShootTimer() {
		shootTimer = 0f;
	}
	
	@Override
	public void shoot() {
		isShooting = true;
		Bullet bullet = new Bullet(getTeam(),getCenter(),getLookDirection());
		BulletManager.getInstance().dispatchMessage(bullet);
		NoiseManager.getInstance().dispatchMessage(new Noise(this.getPosition(),100,NoiseType.SHOOT));
	}
	
	@Override
	public boolean isShooting() {
		return isShooting;
	}
}
