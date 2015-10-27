package com.mygdx.game;

public interface State<T> {
	ActionRequest<T> updateState(StateMachine<T> owner,Context context );
}
