/*
 *@author Tomas Raies
 *@date   17 de oct. de 2015
 */

package com.mygdx.game;

import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
/*
 * Maneja el contexto sobre el cual los Behaviours de los npc deciden sus
 * acciones, incluyendo informacion como sonidos y la posicion del jugador.
 */
public class Context {
	private List<Noise> noiseList;
	private List<Vector2> positionList;
	private boolean isMoving;
	private long shootTimer;
	private long surpriseTimer;
	private LevelMap map;
	private Vector2 npcPosition;
	
	public Context(List<Noise> noiseList, 
			List<Vector2> positionList,
			Vector2 npcPosition,
			boolean isMoving, 
			long shootTimer,
			long surpriseTimer,
			LevelMap map) {
		
		if (noiseList == null || positionList == null){
			throw new IllegalArgumentException();
		}
		this.noiseList = noiseList;
		this.positionList = positionList;
		this.isMoving = isMoving;
		this.shootTimer = shootTimer;
		this.map = map;
		this.npcPosition = npcPosition;
		this.surpriseTimer = surpriseTimer;
	}
	/*
	 * Devuelve si el npc puede ver al jugador.
	 */
	public boolean playerIsVisible() {
		return positionList.size() > 0 && positionList.get(0) != null;
	}
	/*
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
	/*
	 * Agrega un sonido al contexto.
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
			playerPosition = new Vector2(positionList.get(0));
		}
		return playerPosition;
	}
	public Vector2 getNpcPosition() {
		return npcPosition;
	}
	public boolean isMoving() {
		return isMoving;
	}
	public long shootTimer() {
		return shootTimer;
	}
	public LevelMap getMap() {
		return map;
	}
	public long getSurpriseTimer() {
		return surpriseTimer;
	}
}
