package com.mygdx.game;

public class StopRequest implements ActionRequest<NPC> {

	@Override
	public void execute(NPC t) {
		t.stop();
	}

}
