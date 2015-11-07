package com.mygdx.game.controller;

import com.mygdx.game.model.character.Goon;
import com.mygdx.game.view.assets.NPCView;

public class GoonController extends NPCController{
	public GoonController(Goon goon, NPCView view) {
		// TODO Auto-generated constructor stub
		super(goon,view);
	}
	
	@Override 
	public void updateView() {
		
		if (!isDead()){
			if (getModel().isShooting()){
				getView().setShooting(true);
			}
		}
		super.updateView();
		
	}
}
