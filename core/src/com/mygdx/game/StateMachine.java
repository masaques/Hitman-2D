package com.mygdx.game;

public interface StateMachine<T> {
	
	public ActionRequest<T> updateMachine(Context context);
	public void changeState(State<T> state);
}
