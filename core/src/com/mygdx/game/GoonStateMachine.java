package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class GoonStateMachine extends NPCStateMachine {
	public GoonStateMachine(RandList<Vector2> searchPositions) {
		super();
		AlarmStateMachine alarmState = new AlarmStateMachine(new FollowBehaviour());
		SuspiciousStateMachine suspiciousState = new SuspiciousStateMachine(new InspectBehaviour());
		CalmStateMachine calmState = new CalmStateMachine(new PatrolBehaviour(searchPositions));
		alarmState.set(suspiciousState);
		suspiciousState.set(alarmState).set(calmState);
		calmState.set(alarmState).set(suspiciousState);
		changeState(calmState);
		
	}
}
