package com.mygdx.game.controller;

import com.mygdx.game.model.character.Civilian;
import com.mygdx.game.view.assets.CivilianView;

/**
 * Controler del Civil
 * 
 * @see Civilian
 * @see CivilianVIew
 * @see NPC
 * @see NPCController
 */
public class CivilianController extends NPCController<Civilian, CivilianView> {

	public CivilianController(Civilian npc, CivilianView npcView) {
		super(npc, npcView);
	}

}
