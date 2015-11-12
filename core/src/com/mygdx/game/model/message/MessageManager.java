/**
 *@author Tomas Raies
 *@date   17 de oct. de 2015
 */

package com.mygdx.game.model.message;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * Clase <b>abstracta</b> que maneja la distribución de los mensajes a sus
 * respectivos managers. Cada manager le corresponde un {@link Listener}
 * <i>L</i> y un {@link Message} <i>M</i>
 * </p>
 * 
 */
public abstract class MessageManager<L extends Listener, M extends Message<L>> {
	private List<M> messageList;
	private Set<L> listeners;

	/**
	 * Esta implementada como un Singleton (solo exite una instancia de esta
	 * clase), asi que el constructor es protected. Si obetener la instancia,
	 * usar getInstace en lugar de new.
	 */
	protected MessageManager() {
		this.messageList = new ArrayList<M>();
		this.listeners = new HashSet<L>();
	}

	/**
	 * Agrega un listener al Manager. Si es el primer Listener de su tipo lo
	 * crea.
	 * 
	 * @param listener
	 */
	public void addListener(L listener) {
		if(listener != null)
			listeners.add(listener);
	}

	/**
	 * Elimina un listener del set. si no existe no hace nada
	 * 
	 * @param listener
	 */
	public void removeListener(Listener listener) {
		listeners.remove(listener);
	}

	/**
	 * Limpia el listener Map.
	 */
	public void clearAllListeners() {
		listeners.clear();
	}

	/**
	 * Agrega un mensage a la lista de mensages.
	 * 
	 * @Message message
	 */
	public void dispatchMessage(M message) {
		if(message != null)
		messageList.add(message);
	}
	
	/**
	 * Resetea el estado interno del manager al inicial.
	 */
	public void reset() {
		listeners = new HashSet<L> ();
		messageList = new ArrayList<M> ();
	}
	
	/**
	 * Recorre cada <b>Listener</b> y lo lo notifica según indique el mensaje.
	 * 
	 * @see Message
	 */
	public List<M> update() {

		for (M h : messageList) {
			Set<L> recievers = filter(h, new HashSet<L>(listeners));
			for (L l : recievers) {
				try{
					h.notify(l);
				}catch(NullPointerException e){
					throw new NullPointerException("Null pointer encounter during the message distribution");
				}
			}
		}
		List<M> aux = new ArrayList<M>(messageList);
		messageList.clear();
		return aux;
	}

	/**
	 * Filtra el set de listeners. Por defecto devuelve el mismo set.
	 * 
	 * @param message
	 *            Mensaje por el cual se filtra
	 * @param listenersSet
	 *            Set de Listener a filtrar
	 */
	protected Set<L> filter(M message, Set<L> listenersSet) {
		return listenersSet;
	}
}
