package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

import sun.util.resources.CalendarData;

public class GoonStateMachine extends NPCStateMachine {
	public GoonStateMachine(RandArray<Vector2> searchPositions) {
		super();
		setAlarmState(new AlarmStateMachine(new FollowState()));
		setSuspiciousState(new SuspiciousStateMachine(new PatrolState(searchPositions)));
		setCalmState(new CalmStateMachine(new PatrolState(searchPositions)));
	}
}
