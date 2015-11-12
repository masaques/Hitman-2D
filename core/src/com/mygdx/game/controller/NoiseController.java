package com.mygdx.game.controller;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.model.message.Noise;
import com.mygdx.game.model.message.NoiseManager;
import com.mygdx.game.model.message.NoiseType;
import com.mygdx.game.view.assets.NoisePlayer;

/**
 * Clase que controla los sonidos de la partida y los cede a NoisePlayer para
 * que los reproduzca
 * 
 */
public class NoiseController {
	private NoiseManager manager;
	private NoisePlayer player;
	private List<Noise> noiseList;

	public NoiseController() {
		this.manager = NoiseManager.getInstance();
		this.player = new NoisePlayer();
	}
	
	public void updateModel(){
		noiseList = manager.update();
	}
	
	public void updateView(){
		player.addNoises(toPlay(noiseList));
		player.playNoises();
	}

	private List<NoiseType> toPlay(List<Noise> list) {
		List<NoiseType> types = new ArrayList<NoiseType>();
		for (Noise n : list) {
			types.add(n.getType());
		}
		return types;
	}

}
