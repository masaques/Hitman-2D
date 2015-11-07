package com.mygdx.game.controller;

import com.mygdx.game.model.character.NPC;
import com.mygdx.game.view.assets.NPCView;
/**
 * Controller de las clases que heredan de NPC. Hereda de CharacterController.
 * @author traies
 *
 */
public abstract class NPCController extends CharacterController<NPC,NPCView> {
	/**
	 * Recibe el npc (model) y el npcVie (view)
	 * @param npc
	 * @param npcView
	 */
	public NPCController(NPC npc, NPCView npcView) {
		super(npc,npcView);
	}
	
	/**
	 * Actualiza la vista. Ademas, actualiza el estado de la vista (calm, suspicios,
	 * alarm)
	 */
	@Override
	public void updateView() {
		if (!isDead()) {
			getView().updateState(getModel().getState());
		}
		super.updateView();
	}
}
