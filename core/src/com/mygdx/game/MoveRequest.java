package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class MoveRequest implements ActionRequest<NPC>{
	private Vector2 position;
	private boolean linear;
	
	public MoveRequest(Vector2 position, boolean linear) {
		this.position = position;
		this.linear = linear;
	}
	
	public void execute(NPC npc) {
		npc.moveTo(position, linear);
	}
}
