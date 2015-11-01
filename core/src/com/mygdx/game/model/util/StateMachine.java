package com.mygdx.game.model.util;

import com.mygdx.game.model.character.Context;

public interface StateMachine<T> {
	
	public void updateMachine(Context context);
	public void changeState(State<T> state);
	public T getOwner();
	public float getStateTimer();
}
