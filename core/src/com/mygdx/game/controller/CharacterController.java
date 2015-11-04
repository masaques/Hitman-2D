package com.mygdx.game.controller;
import com.mygdx.game.model.character.Character;
import com.mygdx.game.view.assets.CharacterView;

public abstract class CharacterController <M extends Character, V extends CharacterView<M>> {
	private M model;
	private V view;
	private boolean isDead;
	
	public CharacterController(M model,V view) {
		this.model = model;
		this.view = view;
	}
	
	public void control() {
		updateModel();
		updateView();
	}
	public void updateModel() {
		if (!isDead){
			model.update();
		}
	}
	public void updateView() {
		if (!isDead){
			view.setPosition(model.getPosition());
			view.setLookDirection(model.getLookDirection());
			view.setRunning(view.isRunning());
			view.setMoving(model.isMoving());
			if (model.isHurt()){
				view.setHit();
				model.setHurt(false);
			}
			if (model.isDead()) {
				view.setDead();
				model.die();
				model = null;
				isDead = true;
			}
		}
		view.draw();
	}
	
	protected M getModel(){
		return model;
	}
	protected V getView(){
		return view;
	}
	protected boolean isDead() {
		return isDead;
	}
}
