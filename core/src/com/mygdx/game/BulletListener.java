package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;


/**
 * Interface para los personajes dañables
 * @author masaques
 *
 */
public interface BulletListener extends Listener {
	public Vector2 getPosition() ;
	public Vector2 getDirection() ;
	public void dealDamage(float dmg) ;
}
