package com.mygdx.game;

public class InspectState implements State<NPC> {

	@Override
	public ActionRequest<NPC> updateState(StateMachine<NPC> owner, Context context) {
		ActionRequest<NPC> actionRequest = new NullRequest<NPC>();
		if (!context.isMoving()) {
			owner.changeState(new SearchState(context.getNpcPosition(), context.getMap()));
			
		}
		return actionRequest;
	}

}
