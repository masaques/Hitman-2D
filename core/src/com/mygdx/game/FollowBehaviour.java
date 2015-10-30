package com.mygdx.game;


public class FollowBehaviour implements Behaviour<NPC> {
	
	@Override
	public void behave(NPC npc, Context context) {
		npc.moveTo(context.getPlayerPosition(), true);
		npc.refreshVisualInbox();
	}

}
