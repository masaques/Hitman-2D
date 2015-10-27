package com.mygdx.game;

public class ShootState implements State<NPC> {
	@Override
	public ActionRequest<NPC> updateState(StateMachine<NPC> owner, Context context) {
		
		owner.changeState(new FollowState());
		ActionRequest<NPC> actionRequest = new ShootRequest(context.getPlayerPosition());
		return actionRequest;
	}

}
