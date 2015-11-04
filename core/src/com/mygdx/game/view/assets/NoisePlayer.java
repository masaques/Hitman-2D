package com.mygdx.game.view.assets;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.model.message.Noise;
import com.mygdx.game.model.message.NoiseType;

/**
 * Clase hace audibles al usuario los sonidos emitidos por los
 * jugadores
 * @author masaques
 *
 */

public class NoisePlayer {
	List<Noise> noiseList ;
	private Sound blast = Gdx.audio.newSound(Gdx.files.internal("assets/blast.mp3"));
	private Sound run = Gdx.audio.newSound(Gdx.files.internal("assets/run.mp3"));
	private Sound walk = Gdx.audio.newSound(Gdx.files.internal("assets/walk.mp3"));
	private float time =0;
	private float lastRun ;
	private float lastWalk ;
	
	
	
	public void addNoises(List<Noise> list){
		this.noiseList=list ;
	}
	public void playNoises() {
		for (Noise n: noiseList) {
			NoiseType type = n.getType();
			switch (type){
				case SHOOT:
					blast.play() ;
					break;
				case RUN:
					if(time-lastRun >=1f ){
						run.play() ;
						lastRun = time ;
					}
					break ;
				case WALK:
					if(time-lastWalk >=1f ){
						walk.play() ;
						lastWalk = time ;
					} 
					break ;
			}	
		}
		time += Gdx.graphics.getDeltaTime() ;
	}
}
