package com.mygdx.game;

public abstract class NPCStateMachine implements StateMachine<NPC> {
	
	private State<NPC> currentState;
	
	@Override
	public ActionRequest<NPC> updateMachine(Context context) {
		return currentState.updateState(this, context);
	}
	@Override
	public void changeState(State<NPC> state){
		currentState = state;
	}
}
