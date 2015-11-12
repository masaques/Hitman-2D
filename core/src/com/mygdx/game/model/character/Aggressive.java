package com.mygdx.game.model.character;

/**
 * Interface que implementan los Characters agresivos
 * 
 * @see NPC
 * @see Player
 */
public interface Aggressive {
	/**
	 * Metodo que envia un Bullet a BulletManager y un Noise a NoiseManager
	 * 
	 */
	public void shoot();
}
