package com.mygdx.game;


public class AlarmStateMachine implements StateMachine, State {
	protected static final int FOLLOW_STATE = 4;
	protected static final int SHOOT_STATE = 5;
	
	private State currentState;
	
	
	public AlarmStateMachine(State initialState){
		this.currentState = initialState;
	}
	@Override
	public ActionRequest updateMachine(Context context) {
		return currentState.updateState(this, context);
	}
	@Override
	public ActionRequest updateState(StateMachine owner, Context context){
		if (!context.playerIsVisible()){
			owner.changeState(NPCStateMachine.SUSPICIOUS_STATE);
			return new ActionRequest();
		}
		return updateMachine(context);
	}
	
	@Override
	public void changeState(int state){
		switch(state) {
		case FOLLOW_STATE:
			currentState = new FollowState();
			break;
		case SHOOT_STATE:
			currentState = new ShootState();
			break;
		}
	}

}
