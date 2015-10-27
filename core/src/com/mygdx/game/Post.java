/*
 *@author Tomas Raies
 *@date   17 de oct. de 2015
 */

package com.mygdx.game;

/*
 * Representa un mensaje a resolver por la postOffice, segun su tipo y un tiempo
 * de espera determinado.
 */
@Deprecated
public class Post {
	private Integer type;
	private Timer timer;
	private Message message;
	
	public Post (Integer type, Timer timer, Message message) {
		this.type = type;
		this.timer = timer;
		this.message = message;
	}
	/*
	 * Devuelve el tipo del mensaje. Ver PostOffice para ver los posibles valores
	 */
	public Integer getType() {
		return type;
	}
	/*
	 * Verifica si ya paso el tiempo de espera.
	 */
	public boolean checkTimer() {
		return timer.isOver();
	}
	/*
	 * Devuelve el mensaje.
	 */
	public Message getMessage() {
		return message;
	}
}
