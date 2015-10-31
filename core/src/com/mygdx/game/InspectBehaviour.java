package com.mygdx.game;

public class InspectBehaviour implements Behaviour<NPC> {

	@Override
	public void behave(NPC npc, Context context) {
		npc.moveTo(context.getNoise().getPosition(), false);
		npc.refreshNoiseInbox();
		npc.refreshVisualInbox();
	}

}
