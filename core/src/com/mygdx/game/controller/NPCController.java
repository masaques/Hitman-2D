package com.mygdx.game.controller;

import com.mygdx.game.model.character.NPC;
import com.mygdx.game.view.assets.NPCView;

public class NPCController extends CharacterController<NPC,NPCView> {
	
	public NPCController(NPC npc, NPCView npcView) {
		super(npc,npcView);
	}
	public void control() {
		updateModel();
		updateView();
	}
	
	public void updateModel() {
		super.updateModel();
	}
	
	public void updateView() {
		super.updateView();
		if (!isDead()) {
			getView().updateState(getModel().getState());
		}
	}
}
