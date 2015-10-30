package com.mygdx.game;

public interface State<T> {
	void updateState(StateMachine<T> owner,Context context );
}
