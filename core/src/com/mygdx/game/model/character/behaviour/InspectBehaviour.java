package com.mygdx.game.model.character.behaviour;

import com.mygdx.game.model.character.Context;
import com.mygdx.game.model.character.NPC;

public class InspectBehaviour implements Behaviour<NPC> {

	@Override
	public void behave(NPC npc, Context context) {
		npc.moveTo(context.getNoise().getPosition(), false);
		npc.refreshNoiseInbox();
		npc.refreshVisualInbox();
	}

}
