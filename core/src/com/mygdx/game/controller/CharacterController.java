package com.mygdx.game.controller;

import com.badlogic.gdx.math.Vector2;

import com.mygdx.game.model.character.Character;
import com.mygdx.game.view.assets.CharacterView;

/**
 * Clase abstracta que maneja el control de los Character.
 * 
 * @param <M> - {@link Character} del Modelo
 * @param <V> - {@link CharacterView} del View
 * 
 * @see Controller
 * @see Character
 */
public abstract class CharacterController<M extends Character, V extends CharacterView<M>> extends Controller<M, V>
		implements Comparable<CharacterController<M, V>> {

	private boolean isDead;

	/**
	 * Constructor, recibe el Character y el CharacterView
	 * 
	 * @param model - {@link Character} del Modelo
	 * @param view - {@link CharacterView} correspondiente al modelo
	 */
	public CharacterController(M character, V characterView) {
		super(character, characterView);
	}

	/**
	 * Actualiza el modelo. Si el personaje esta muerto no hace nada.
	 */
	@Override
	public void updateModel() {
		if (!isDead) {
			getModel().update();
		}
	}

	/**
	 * Actualiza la view. Si el personaje esta muerto no lo actualiza, elimina
	 * su referencia y se marca como muerto el controler, pero aun asi llama al
	 * draw.
	 */

	@Override
	public void updateView() {
		V characterView = getView();
		if (!isDead){
			M character = getModel();
			characterView.setPosition(character.getCenter());
			characterView.setLookDirection(character.getLookDirection());
			characterView.setRunning(character.isRunning());
			characterView.setMoving(character.isMoving());
			if (character.isHurt()) {
				characterView.setHit();
				character.setHurt(false);
			}
			if (character.isDead()) {
				characterView.die();
				character.die();
				character = null;
				isDead = true;
			}
		}
	}
	
	
	public void draw() {
		getView().draw();
	}
	/**
	 * Devuelve si el jugador esta muerto.
	 * 
	 */
	public boolean isDead() {
		return isDead;
	}

	/**
	 * Se comparan los characterController segun si estan muertos o no
	 */
	@Override
	public int compareTo(CharacterController<M, V> other) {
		if (this.isDead() && other.isDead()) {
			return 0;
		} else if (this.isDead()) {
			return -1;
		} else {
			return 1;
		}
	}
	
	public Vector2 position() {
		return getModel().getCenter();
	}
}
