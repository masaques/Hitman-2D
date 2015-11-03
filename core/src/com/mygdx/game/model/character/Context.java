/**
 *@author Tomas Raies
 *@date   17 de oct. de 2015
 */

package com.mygdx.game.model.character;

import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.message.Noise;
/**
 * Maneja el contexto sobre el cual los Behaviours de los npc deciden sus
 * acciones, incluyendo informacion como sonidos y la posicion del jugador.
 */
public class Context {
	private List<Noise> noiseList;
	private List<Vector2> positionList;
	private boolean isMoving;
	
	public Context(List<Noise> noiseList, List<Vector2> positionList, boolean isMoving) {
		if (noiseList == null || positionList == null){
			throw new IllegalArgumentException();
		}
		this.noiseList = noiseList;
		this.positionList = positionList;
		this.isMoving = isMoving;
	}
	/**
	 * Devuelve si el npc puede ver al jugador.
	 */
	public boolean playerIsVisible() {
		return positionList.size() > 0 && positionList.get(0) != null;
	}
	/**
	 * Devuelve el sonido mas fuerte, null si es vacio.
	 */
	public Noise getNoise() {
		Collections.sort(noiseList);
		Noise noise = noiseList.get(0);
		return noise;
	}
	public boolean canHear() {
		return noiseList.size() > 0;
	}
	/**
	 * Agrega un sonido al contexto.
	 * 
	 * @param noise - {@link Noise} a agregar
	 */
	public void add(Noise noise) {
		noiseList.add(noise);
	}
	
	public Vector2 getPlayerPosition() {
		Vector2 playerPosition;
		if (positionList.size() <= 0) {
			playerPosition = null;
		}
		else {
			playerPosition = new Vector2(positionList.get(positionList.size() - 1));
		}
		return playerPosition;
	}
	
	
	public boolean isMoving() {
		return isMoving;
	}
	
	
}
