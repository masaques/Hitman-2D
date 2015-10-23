/*
 *@author Tomas Raies
 *
 *@date 13 de oct. de 2015
 */


package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/*
 * Interfaz para los objetos que se puedan mover, como los Character o los Bullets.
 */
public interface Movable {
	Vector2 getPosition();
	Vector2 getDirection();
	float getWidth();
	float getHeight();
	boolean move(Vector2 direction);
	void update();
}
