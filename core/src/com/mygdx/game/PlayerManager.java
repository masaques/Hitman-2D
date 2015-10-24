package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
/**
 * Clase que recibe datos del ControlProcessor acerca de lo que hizo el jugador y,
 * en base a esto, actualiza el modelo.
 * @author masaques
 *
 */
public class PlayerManager {
	private ControlProcessor control;
	private Player player;
	private NoiseManager postOffice ;
	
	public PlayerManager(Player player){
		this.player = player ;
		control = new ControlProcessor() ;
		Gdx.input.setInputProcessor(control);
		postOffice = NoiseManager.getInstance() ;
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
		Vector2 noisePosition = control.getMouseClick() ;
		if (noisePosition!=null){
			Noise message = new Noise(noisePosition,100, true);
			postOffice.dispatchMessage(message);
		}
	}
}
