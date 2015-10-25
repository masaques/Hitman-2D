package com.mygdx.game;

public interface StateMachine {
	public enum StateCode {
		
	};
	public ActionRequest updateMachine(Context context);
	public void changeState(int stateCode);
}
