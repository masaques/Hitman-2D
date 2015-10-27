package com.mygdx.game;


public class AlarmStateMachine implements StateMachine<NPC>, State<NPC> {
	private State<NPC> initialState;
	private State<NPC> currentState;
	private SuspiciousStateMachine suspiciousStateMachine;
	
	public AlarmStateMachine(State<NPC> initialState){
		this.initialState = initialState;
		this.currentState = initialState;
	}
	public AlarmStateMachine set(SuspiciousStateMachine suspiciousStateMachine) {
		this.suspiciousStateMachine = suspiciousStateMachine;
		return this;
	}
	
	@Override
	public ActionRequest<NPC> updateMachine(Context context) {
		return currentState.updateState(this, context);
	}
	@Override
	public ActionRequest<NPC> updateState(StateMachine<NPC> owner, Context context){
		if (System.currentTimeMillis() < context.getSurpriseTimer()) {
			return new StopRequest();
		}
		if (!context.playerIsVisible()){
			owner.changeState(suspiciousStateMachine);
			/*
			 * Sospecha durante 10 segundos.
			 */
			suspiciousStateMachine.setTimer(10000);
			changeState(initialState);
			return new NullRequest<NPC>();
		}
		return updateMachine(context);
	}
	
	@Override
	public void changeState(State<NPC> state){
		currentState = state;
	}

}
