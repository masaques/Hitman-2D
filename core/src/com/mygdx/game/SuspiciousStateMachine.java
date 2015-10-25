package com.mygdx.game;

public class SuspiciousStateMachine implements StateMachine, State {
	private State currentState;
	
	public SuspiciousStateMachine(State initialState){
		this.currentState = initialState;
	}
	
	@Override
	public ActionRequest updateMachine(Context context) {
		
		return currentState.updateState(this, context);
	}
	@Override
	public ActionRequest updateState(StateMachine owner, Context context){
		if (context.playerIsVisible()){
			owner.changeState(NPCStateMachine.ALARM_STATE);
			return new ActionRequest();
		}
		return updateMachine(context);
	}
	@Override
	public void changeState(int stateCode) {
		//TODO
		return;
	}

}
