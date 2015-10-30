package com.mygdx.game;

public class SuspiciousStateMachine implements StateMachine<NPC>, State<NPC> {
	private State<NPC> initialState;
	private State<NPC> currentState;
	private AlarmStateMachine alarmStateMachine;
	private CalmStateMachine calmStateMachine;
	private long timer;
	
	public SuspiciousStateMachine(State<NPC> initialState){
		this.initialState = initialState;
		this.currentState = initialState;
	}
	public SuspiciousStateMachine set(AlarmStateMachine alarmState) {
		this.alarmStateMachine = alarmState;
		return this;
	}
	public SuspiciousStateMachine set(CalmStateMachine calmState) {
		this.calmStateMachine = calmState;
		return this;
	}
	public void setTimer(long duration) {
		timer = System.currentTimeMillis() + duration;
	}
	@Override
	public ActionRequest<NPC> updateMachine(Context context) {
		
		return currentState.updateState(this, context);
	}
	@Override
	public ActionRequest<NPC> updateState(StateMachine<NPC> owner, Context context){
		if (context.playerIsVisible()){
			owner.changeState(alarmStateMachine);
			changeState(initialState);
			return new NullRequest<NPC>();
		}
		else if (System.currentTimeMillis() > timer) {
			owner.changeState(calmStateMachine);
			changeState(initialState);
			return new NullRequest<NPC>();
		}
		return updateMachine(context);
	}
	@Override
	public void changeState(State<NPC> state) {
		currentState = state;
	}

}
