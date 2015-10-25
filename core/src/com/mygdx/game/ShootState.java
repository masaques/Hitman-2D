package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class ShootState implements State {

	@Override
	public ActionRequest updateState(StateMachine owner, Context context) {
		
		owner.changeState(AlarmStateMachine.FOLLOW_STATE);
		ActionRequest actionRequest = new ActionRequest();
		actionRequest.setRequest(ActionRequest.REQUEST_SHOOT);
		actionRequest.setPosition(context.getPlayerPosition());
		return actionRequest;
	}

}
