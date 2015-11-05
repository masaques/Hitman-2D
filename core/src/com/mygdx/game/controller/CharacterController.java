package com.mygdx.game.controller;
import com.mygdx.game.model.character.Character;
import com.mygdx.game.view.assets.CharacterView;

/**
 * 
 * @author traies
 *
 * Clase abstracta que maneja el control de los Character.
 * @param <M>
 * @param <V>
 */
public abstract class CharacterController <M extends Character, V extends CharacterView<M>> {
	private M model;
	private V view;
	private boolean isDead;
	
	/**
	 * Constructor, recibe el Character y el CharacterView
	 * @param model
	 * @param view
	 */
	public CharacterController(M model,V view) {
		this.model = model;
		this.view = view;
	}
	/**
	 * Llama al actualiza el modelo y al view. 
	 */
	public void control() {
		updateModel();
		updateView();
	}
	
	/**
	 * Actualiza el modelo. Si el personaje esta muerto no hace nada.
	 */
	public void updateModel() {
		if (!isDead){
			model.update();
		}
	}
	/**
	 * Actualiza la view. Si el personaje esta muerto no lo actualiza, pero
	 * aun asi llama al draw.
	 */
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
	/**
	 * TODO este metodo debe ser protected. Mover el metodo dump de character a aca.
	 * @return
	 */
	public M getModel(){
		return model;
	}
	/**
	 * TODO este metodo debe ser protected.
	 * @return
	 */
	public V getView(){
		return view;
	}
	/**
	 * Devuelve si el jugador esta muerto.
	 * @return
	 */
	protected boolean isDead() {
		return isDead;
	}
}
