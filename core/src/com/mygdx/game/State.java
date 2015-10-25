package com.mygdx.game;

public interface State {
	ActionRequest updateState(StateMachine owner,Context context );
}
