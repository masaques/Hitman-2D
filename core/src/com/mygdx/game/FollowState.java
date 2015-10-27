package com.mygdx.game;


public class FollowState implements State<NPC> {
	
	@Override
	public ActionRequest<NPC> updateState(StateMachine<NPC> owner, Context context) {
		ActionRequest<NPC> actionRequest ;
		if (System.currentTimeMillis() >= context.shootTimer()) {
			owner.changeState(new ShootState());
			actionRequest = new NullRequest<NPC>();
		}
		else {
			actionRequest = new MoveRequest(context.getPlayerPosition(),true);
		}
		return actionRequest;
	}

}
