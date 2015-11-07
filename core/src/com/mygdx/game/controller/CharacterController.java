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
public abstract class CharacterController <M extends Character, V extends CharacterView<M>> extends Controller<M,V> {
	
	private boolean isDead;
	
	/**
	 * Constructor, recibe el Character y el CharacterView
	 * @param model
	 * @param view
	 */
	public CharacterController(M character, V characterView) {
		super(character, characterView);
	}
	
	/**
	 * Actualiza el modelo. Si el personaje esta muerto no hace nada.
	 */
	@Override
	public void updateModel() {
		if (!isDead){
			getModel().update();
		}
	}
	/**
	 * Actualiza la view. Si el personaje esta muerto no lo actualiza, pero
	 * aun asi llama al draw.
	 */
	@Override
	public void updateView() {
		V characterView = getView();
		if (!isDead){
			M character = getModel();
			characterView.setPosition(character.getCenter());
			characterView.setLookDirection(character.getLookDirection());
			characterView.setRunning(characterView.isRunning());
			characterView.setMoving(character.isMoving());
			if (character.isHurt()){
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
		characterView.draw();
	}
	
	/**
	 * Devuelve si el jugador esta muerto.
	 * @return
	 */
	protected boolean isDead() {
		return isDead;
	}
}
