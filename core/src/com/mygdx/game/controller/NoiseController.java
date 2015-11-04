package com.mygdx.game.controller;

import java.util.List;

import com.mygdx.game.model.message.Noise;
import com.mygdx.game.model.message.NoiseManager;
import com.mygdx.game.view.assets.NoisePlayer;
/**
 * Clase que controla los sonidos de la partida
 * y los cede a NoisePlayer para que los reproduzca
 * @author masaques
 *
 */
public class NoiseController {
	private NoiseManager manager ;
	private NoisePlayer player ;
	private List<Noise> toPlay ;
	
	
	public NoiseController(){
		this.manager = NoiseManager.getInstance() ;
		this.player = new NoisePlayer() ;
	}
	
	public void manage(){
		updateModel();
		updateView();
	}
	
	public void updateModel(){
		toPlay = manager.update();
	}
	
	public void updateView(){
		player.addNoises(toPlay);
		player.playNoises();
	}
	
}
