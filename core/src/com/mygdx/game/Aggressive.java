package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
/**
 * Interface que implementan los Characters agresivos
 * @author masaques
 * @see NPC
 * @see Player
 */
public interface Aggressive {
	/**
	 * Metodo que envia un Bullet a BulletManager
	 * y un Noise a NoiseManager
	 * @param to La posicion a la que se desea disparar
	 */
	public void shoot(Vector2 to) ;
}
