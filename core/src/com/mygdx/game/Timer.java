/*
 *@author Tomas Raies
 *@date   17 de oct. de 2015
 */

package com.mygdx.game;

/*
 * Setea un timer con una duracion determinada. Sirve para meterle un delay a los mensajes.
 */
public class Timer {
	
	private long targetTime;
	
	public Timer(long duration) {
	
		this.targetTime = System.currentTimeMillis() + duration;
	}
	
	public boolean isOver () {
		return targetTime < System.currentTimeMillis();
	}

}
