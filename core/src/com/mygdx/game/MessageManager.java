/*
 *@author Tomas Raies
 *@date   17 de oct. de 2015
 */

package com.mygdx.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*
 * Clase Singleton que maneja la distrubucion de los mensajes a sus respectivos
 * handlers.
 */
public abstract class MessageManager<L extends Listener, M extends Message<L>> {
	private List<M> messageList;
	private Set<L> listeners;

	/*
	 * Esta implementada como un Singleton (solo exite una instancia de esta clase), asi que 
	 * el constructor es privado. Usar getInstace en lugar de new.
	 */
	protected MessageManager() {
		this.messageList = new ArrayList<M>();
		this.listeners = new HashSet<L>();
	}
	/*
	 * Agrega un listener a un  tipo de mensaje determinado. Si es el primer Listener de 
	 * su tipo lo crea.
	 * @param listener
	 * @param messageType
	 */
	public void addListener(L listener) {
		listeners.add(listener);
	}
	/*
	 * Elimina un listener del set. si no existe no hace nada
	 * @param listener
	 * @param messageType
	 */
	public void removeListener(Listener listener) {
		listeners.remove(listener);
	}
	/*
	 * Limpia el listenerMap.
	 */
	public void clearAllListeners() {
		listeners.clear();
	}
	
	/*
	 * anade un post al buzon de entrada.
	 * @Integer messageType
	 * @Message message
	 */
	public void dispatchMessage(M message) {
		messageList.add(message);
	}
	
	
	/*
	 * Recorre cada post en el buzon y lo manda al handler que le corresponde.
	 */
	public void update(){
	
		List<M> handleList = new ArrayList<M>();
		for (M m : messageList) {
			handleList.add(m);
		}
		messageList.removeAll(handleList);
	
		for (M h: handleList) {
			Set<L> recievers = filter(h,new HashSet<L>(listeners));
			for (L l: recievers) {
				h.notify(l);
			}
		}
		
	}
	/*
	 * Filtra el set de listeners. Por defecto devuelve el mismo set.
	 * @param listenersSet
	 */
	private Set<L> filter(M message, Set<L> listenersSet) {
		return listenersSet;
	}
}
