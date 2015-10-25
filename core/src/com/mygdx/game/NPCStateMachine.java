package com.mygdx.game;

public abstract class NPCStateMachine implements StateMachine {
	protected static final int ALARM_STATE = 0;
	protected static final int SUSPICIOUS_STATE = 1;
	protected static final int CALM_STATE = 2;
	private State alarmStateMachine;
	private State suspiciousStateMachine;
	private State calmStateMachine;
	private State currentState;
	
	public void setAlarmState(State alarmState){
		this.alarmStateMachine = alarmState;
	}
	public void setSuspiciousState(State suspiciousState){
		this.suspiciousStateMachine = suspiciousState;
	}
	public void setCalmState(State calmState){
		this.calmStateMachine = calmState;
	}
	
	@Override
	public ActionRequest updateMachine(Context context) {
		if (currentState == null) {
			currentState = calmStateMachine;
		}
		return currentState.updateState(this, context);
	}
	@Override
	public void changeState(int stateCode){
		switch(stateCode){
		case ALARM_STATE:
			currentState = alarmStateMachine;
			break;
		case SUSPICIOUS_STATE:
			currentState = suspiciousStateMachine;
			break;
		case CALM_STATE:
			currentState = calmStateMachine;
			break;
		}
	}
}
