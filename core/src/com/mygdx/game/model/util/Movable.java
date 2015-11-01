/**
 *@author Tomas Raies
 *
 *@date 13 de oct. de 2015
 */


package com.mygdx.game.model.util;

import com.badlogic.gdx.math.Vector2;

/**
 * Interfaz para los objetos que se puedan mover
 * 
 */
public interface Movable {
	
	Vector2 getPosition();
	Vector2 getMoveDirection();
	Vector2 getLookDirection();
	float getWidth();
	float getHeight();
	
	/**
	 * @param direction direction en la que se deben mover
	 * @return true si pudo realizar el movimiento con exito
	 */
	boolean move(Vector2 direction);
	void update();
}
