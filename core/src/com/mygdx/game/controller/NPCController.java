package com.mygdx.game.controller;

import com.mygdx.game.model.character.NPC;
import com.mygdx.game.view.assets.NPCView;

/**
 * Controller de las clases que heredan de NPC. Hereda de CharacterController.
 * 
 */
public abstract class NPCController<M extends NPC, V extends NPCView<M>> extends CharacterController<M, V> {
	/**
	 * Recibe el npc (model) y el npcVie (view)
	 * 
	 * @param npc - {@link Model} del NPC
	 * @param npcView - {@link View} del NPC
	 */
	public NPCController(M npc, V npcView) {
		super(npc, npcView);
	}

	/**
	 * Actualiza la vista. Ademas, actualiza el estado de la vista (calm,
	 * suspicios, alarm)
	 */
	@Override
	public void updateView() {
		if (!isDead()) {
			getView().updateState(getModel().getState());
		}
		super.updateView();
	}
}
