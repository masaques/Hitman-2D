package com.mygdx.game.model.character.behaviour;

import com.mygdx.game.model.character.Context;
import com.mygdx.game.model.character.NPC;

public class FollowBehaviour implements Behaviour<NPC> {

	@Override
	public void behave(NPC npc, Context context) {
		npc.moveTo(context.getPlayerPosition(), true);
		npc.refreshVisualInbox();
		npc.refreshNoiseInbox();
	}

}
