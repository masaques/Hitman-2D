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
public class PlayerManager {
	private ControlProcessor control;
	private Player player;
	
	public PlayerManager(Player player,ControlProcessor control){
		this.player = player ;
		this.control = control ;
	}
	
	public void manage() {
		control.update();
		PlayerMovement movement ;
		movement = control.getPlayerMovement();
		if (movement.getDirection().x != 0 || movement.getDirection().y != 0) {
			player.move(movement.getDirection().nor(),movement.isRunning()) ;
		}
		else {
			player.stopMoving();
		}
		player.look(movement.getLookDirection());
	}
}
