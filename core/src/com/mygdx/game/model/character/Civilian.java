package com.mygdx.game.model.character;

import java.util.List;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.LevelMap;
import com.mygdx.game.model.character.behaviour.Behaviour;
import com.mygdx.game.model.character.behaviour.FleeBehaviour;
import com.mygdx.game.model.character.behaviour.InspectBehaviour;
import com.mygdx.game.model.character.behaviour.PatrolBehaviour;
import com.mygdx.game.model.character.behaviour.SearchBehaviour;

public class Civilian extends NPC {
	
	private Behaviour<NPC> fleeBehaviour;
	/**
	 * TODO Deprecated?
	 */
	private Behaviour<NPC> patrolBehaviour;
	private Behaviour<NPC> inspectBehaviour;
	
	public Civilian(Rectangle hitBox, LevelMap map, List<Vector2> patrolPositions, List<Vector2> safePositions) {
		super(hitBox, map);
		patrolBehaviour  = new PatrolBehaviour(patrolPositions);
		System.out.println(patrolBehaviour);
		fleeBehaviour    = new FleeBehaviour(safePositions);
		inspectBehaviour = new InspectBehaviour();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void alarm(Context context) {
		// TODO Auto-generated method stub
		if (context.playerIsVisible()) {
			fleeBehaviour.behave(this, context);
		}

	}

	@Override
	public void suspicious(Context context) {
		// TODO Auto-generated method stub
		if (context.canHear()) {
			inspectBehaviour.behave(this, context);
		}

	}

	@Override
	public void calm(Context context) {
		// TODO Auto-generated method stub
		patrolBehaviour.behave(this, context);

	}

	@Override
	public boolean isShooting() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void update(){
		super.update();
	}

}
