package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.character.Player;
/**
 * Clase que recibe datos del ControlProcessor acerca de lo que hizo el jugador y,
 * en base a esto, actualiza el modelo.
 * @author masaques
 *
 */
public class PlayerManager extends CharacterController<Player, PlayerView> {
	private ControlProcessor control;
	
	public PlayerManager(Player player,ControlProcessor control, PlayerView playerView){
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
			
			getModel().look(movement.getLookDirection());
			
			if (movement.isShooting()) {
				getModel().shoot();
			}
		}
		super.updateModel();
	}
}
