package com.mygdx.game;

public interface StateMachine<T> {
	
	public void updateMachine(Context context);
	public void changeState(State<T> state);
	public T getOwner();
	public float getStateTimer();
}
