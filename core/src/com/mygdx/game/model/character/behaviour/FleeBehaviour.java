package com.mygdx.game.model.character.behaviour;

import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.controller.IllegalPositionException;
import com.mygdx.game.model.character.Context;
import com.mygdx.game.model.character.NPC;

public class FleeBehaviour implements Behaviour<NPC> {
	private List<Vector2> safePlaces;

	public FleeBehaviour(List<Vector2> safePlaces) {
		this.safePlaces = safePlaces;
	}

	@Override
	public void behave(NPC t, Context context) {
		float maxDistance = 0;
		Vector2 safePlace = null;
		Vector2 origin = new Vector2();
		if(context.playerIsVisible()){
			origin.set(context.getPlayerPosition());
		}else if(context.canHear()){
			origin.set(context.getNoise().getPosition());
		}
		
		for (Vector2 p : safePlaces) {
			float distance = p.dst(origin);
			if (distance > maxDistance) {
				maxDistance = distance;
				safePlace = p;
			}
		}
		if (safePlace == null) {
			throw new NoSafePlaceException();
		}
		t.moveTo(safePlace, false);
		t.refreshVisualInbox();
		t.refreshNoiseInbox();

	}

}
