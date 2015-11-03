/**
 *@author Tomas Raies
 *@date   17 de oct. de 2015
 */

package com.mygdx.game.model.message;

/**
 * Interfaz para los mensajes. Los mensages tienen la habilidad de avisar su ensaje a un listener
 */

public interface Message<L extends Listener> {
	/**
	 * Avisa a un {@link Listener} sobre el mensaje.
	 * @param listener
	 */
	void notify(L listener);
}
