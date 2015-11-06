package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.PlayerMovement;
import com.mygdx.game.model.character.Player;
import com.mygdx.game.view.assets.PlayerView;
/**
 * Clase que recibe datos del ControlProcessor acerca de lo que hizo el jugador y,
 * en base a esto, actualiza el modelo.
 * @author masaques
 *
 */
public class PlayerController extends CharacterController<Player, PlayerView> {
	private ControlProcessor control;
	
	public PlayerController(Player player,ControlProcessor control, PlayerView playerView){
		super(player, playerView);
		this.control = control ;
	}
	
	public void control() {
		updateModel();
		updateView();
	}
	
	public void updateView() {
		super.updateView();
	}
	public void updateModel() {
		if (!isDead()){
			control.update();
			PlayerMovement movement ;
			movement = control.getPlayerMovement();
			if (movement.getDirection().x != 0 || movement.getDirection().y != 0) {
				getModel().move(movement.getDirection().nor(),movement.isRunning()) ;
			}
			else {
				getModel().stopMoving();
			}
			Vector2 lookDirection = movement.getLookDirection();
			lookDirection.sub(getModel().getCenter());
			getModel().look(lookDirection);
			
			if (movement.isShooting()) {
				getModel().shoot();
			}
		}
		super.updateModel();
	}
}
