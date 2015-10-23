package com.mygdx.game;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase para filtrar los Shots
 * @author masaques
 *
 */

public class ShotFilter implements MessageFilter {
	private LevelMap map ;
	public ShotFilter (LevelMap map) {
		this.map = map ;
	}
	/**
	 * @param shot: el disparo
	 * @param listeners todos los characters, incluido el player
	 * @returns Los characters muertos tras el ataque
	 * 
	 */
	@Override
	public Set<Listener> filter(Message message, Set<Listener> listeners) {
		if (message.getClass()!=Shot.class){
			throw new WrongMessageException();
		}
		Shot shot = (Shot)message;
		if (listeners == null){
			throw new IllegalArgumentException() ;
		}
		Set<Listener> killed_set = new HashSet<Listener>() ;
		for (Listener l : listeners) {
			Character c = (Character)l ;
			if (!(c.getPosition().dst(shot.getPosition()) > shot.getRange())){
				if (c.getPosition().sub(shot.getPosition()).isCollinear(shot.getDirection())) {
					if(!map.isValid(shot.getPosition(), c.getPosition())) {
						if (c.dealDamage(shot.getDamage())){
							killed_set.add(l) ;
						}
					}
				}
			}
			
		}
		return killed_set;
	}

}
