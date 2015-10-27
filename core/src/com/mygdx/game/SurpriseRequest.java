package com.mygdx.game;

public class SurpriseRequest extends StopRequest {
	private long duration;
	
	public SurpriseRequest(long duration) {
		this.duration = duration;
	}
	@Override
	public void execute(NPC npc) {
		super.execute(npc);
		npc.setSurpriseTimer(duration);
	}
}
