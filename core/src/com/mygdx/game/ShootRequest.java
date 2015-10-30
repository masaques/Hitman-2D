package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class ShootRequest implements ActionRequest<NPC> {
	private Vector2 shootPosition;
	
	public ShootRequest(Vector2 shootPosition){
		this.shootPosition = shootPosition;
	}
	public void execute (NPC npc) {
		npc.shoot(shootPosition);
	}
}
