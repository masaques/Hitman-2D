package com.mygdx.game;


public class FollowState implements State {
	
	@Override
	public ActionRequest updateState(StateMachine owner, Context context) {
		ActionRequest actionRequest = new ActionRequest();
		if (System.currentTimeMillis() >= context.shootTimer()) {
			owner.changeState(AlarmStateMachine.SHOOT_STATE);
		}
		else {
			actionRequest.setRequest(ActionRequest.REQUEST_MOVETO);
			actionRequest.setPosition(context.getPlayerPosition());
			actionRequest.setRunning(true);
			actionRequest.setLinear(true);
		}
		return actionRequest;
	}

}
