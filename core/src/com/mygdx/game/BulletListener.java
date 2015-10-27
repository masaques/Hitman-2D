package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;


/**
 * Interface que implementan objetos dañables.
 * @author masaques
 *
 * @see BulletManager
 */
public interface BulletListener extends Listener {
	public Vector2 getPosition() ;
	public Vector2 getDirection() ;
	
	/**
	 * <p> Método que aplica daño al objeto</p>
	 * 
	 * @param dmg - Cantidad de daño a recibir
	 */
	public void dealDamage(float dmg) ;
}
