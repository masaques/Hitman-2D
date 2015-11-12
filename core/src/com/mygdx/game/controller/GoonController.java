package com.mygdx.game.controller;

import com.mygdx.game.model.character.Goon;
import com.mygdx.game.view.assets.GoonView;

/**
 * Controller para el Goon
 * 
 * @see NPCController
 * @see Goon
 * @see NPC
 *
 */
public class GoonController extends NPCController<Goon, GoonView> {

	public GoonController(Goon goon, GoonView view) {
		super(goon, view);
	}

	@Override
	public void updateView() {

		if (!isDead()) {
			if (getModel().isShooting()) {
				getView().setShooting(true);
			}
		}
		super.updateView();

	}
}
