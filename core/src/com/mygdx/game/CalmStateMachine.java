package com.mygdx.game;

public class CalmStateMachine implements StateMachine<NPC>, State<NPC> {
	private State<NPC> initialState;
	private State<NPC> currentState;
	private AlarmStateMachine alarmStateMachine;
	private SuspiciousStateMachine suspiciousStateMachine;
	
	public CalmStateMachine(State<NPC> initialState) {
		this.initialState = initialState;
		this.currentState = initialState;
	}
	
	public CalmStateMachine set(AlarmStateMachine alarmStateMachine) {
		this.alarmStateMachine = alarmStateMachine;
		return this;
	}
	public CalmStateMachine set(SuspiciousStateMachine suspiciousStateMachine) {
		this.suspiciousStateMachine = suspiciousStateMachine;
		return this;
	}
	
	@Override
	public ActionRequest<NPC> updateMachine(Context context) {
		return currentState.updateState(this, context);
	}

	@Override
	public void changeState(State<NPC> state) {
		// TODO Auto-generated method stub

	}

	@Override
	public ActionRequest<NPC> updateState(StateMachine<NPC> owner, Context context) {
		ActionRequest<NPC> actionRequest;
		if (context.playerIsVisible()) {
			owner.changeState(alarmStateMachine);
			changeState(initialState);
			actionRequest = new NullRequest<NPC>();
		}
		else if (context.canHear()){
			owner.changeState(suspiciousStateMachine);
			changeState(initialState);
			suspiciousStateMachine.setTimer(10000l);
			actionRequest = new NullRequest<NPC>();
		}
		else {
			actionRequest = updateMachine(context);
		}
		return actionRequest;
	}

}
