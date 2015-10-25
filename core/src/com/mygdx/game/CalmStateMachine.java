package com.mygdx.game;

public class CalmStateMachine implements StateMachine, State {
	private State currentState;
	public CalmStateMachine(State initialState) {
		this.currentState = initialState;
	}

	@Override
	public ActionRequest updateMachine(Context context) {
		return currentState.updateState(this, context);
	}

	@Override
	public void changeState(int stateCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public ActionRequest updateState(StateMachine owner, Context context) {
		ActionRequest actionRequest;
		if (context.playerIsVisible()) {
			owner.changeState(NPCStateMachine.ALARM_STATE);
			actionRequest = new ActionRequest();
		}
		else {
			actionRequest = updateMachine(context);
		}
		return actionRequest;
	}

}
