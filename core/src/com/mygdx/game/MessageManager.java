/*
 *@author Tomas Raies
 *@date   17 de oct. de 2015
 */

package com.mygdx.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/*
 * Clase Singleton que maneja la distrubucion de los mensajes a sus respectivos
 * handlers.
 */
public class MessageManager {
	private static final MessageManager INSTANCE = new MessageManager();
	public static final Integer NOISE = 0;
	public static final Integer BULLET = 1;
	private List<Post> postList;
	private Map<Integer, Set<Listener>> listenerMap;
	private Map<Integer, MessageFilter> filterMap;

	/*
	 * Esta implementada como un Singleton (solo exite una instancia de esta clase), asi que 
	 * el constructor es privado. Usar getInstace en lugar de new.
	 */
	private MessageManager() {
		this.postList = new ArrayList<Post>();
		this.listenerMap = new HashMap<Integer, Set<Listener>>();
		this.filterMap = new HashMap<Integer, MessageFilter>();
	}
	/*
	 * Agrega un listener a un  tipo de mensaje determinado. Si es el primer Listener de 
	 * su tipo lo crea.
	 * @param listener
	 * @param messageType
	 */
	public void addListener(Listener listener, Integer messageType) {
		Set<Listener> listenerSet = listenerMap.get(messageType);
		if (listenerSet == null) {
			listenerSet = new HashSet<Listener>();
			listenerMap.put(messageType, listenerSet);
		}
		listenerSet.add(listener);
	}
	/*
	 * Elimina un listener del set. si no existe no hace nada
	 * @param listener
	 * @param messageType
	 */
	public void removeListener(Listener listener, Integer messageType) {
		Set<Listener> listenerSet = listenerMap.get(messageType);
		if (listenerSet == null){
			return;
		}
		listenerSet.remove(listener);
	}
	/*
	 * Limpia el listenerMap.
	 */
	public void clearAllListeners() {
		listenerMap.clear();
	}
	/*
	 * Retorna la instancia unica.
	 */
	public static MessageManager getInstance() {
		return INSTANCE;
	}
	/*
	 * anade un post al buzon de entrada.
	 * @Integer messageType
	 * @Message message
	 */
	public void dispatchMessage(Integer messageType, Message message) {
		Post post = new Post(messageType, new Timer(0), message);
		postList.add(post);
	}
	public void dispatchMessage(Integer messageType, Message message, int delay) {
		Post post = new Post(messageType, new Timer(delay), message);
		postList.add(post);
	}
	/*
	 * Anade un filtro a un tipo de mensaje. Si ya existia uno lo sobreescribe.
	 * @param filter
	 * @param messageType
	 */
	public void addFilter(MessageFilter filter, Integer messageType) {
		filterMap.put(messageType, filter);
	}
	
	public void removeFilter(Integer messageType) {
		filterMap.remove(messageType);
	}
	public void clearFilters() {
		filterMap.clear();
	}
	
	
	/*
	 * Recorre cada post en el buzon y lo manda al handler que le corresponde.
	 */
	public void update() throws WrongMessageException{
		List<Post> handleList = new ArrayList<Post>();
		for (Post p : postList) {
			if (p.checkTimer()) {
				handleList.add(p);
			}
		}
		postList.removeAll(handleList);
		
		Set<Listener> receivers;
		for (Post h: handleList) {
			receivers = new HashSet<Listener>(listenerMap.get(h.getType()));
			MessageFilter filter = filterMap.get(h.getType());
			if (filter != null){
				receivers = filter.filter(h.getMessage(), receivers);
			}
			notifyListeners(receivers, h.getMessage());
		}
		
	}
	/*
	 * Invoca el metodo handleMessage de los Listeners con el mensaje.
	 * @param listeners
	 * @param message
	 */
	private void notifyListeners(Set<Listener> listeners, Message message) {
		if (listeners == null) {
			return;
		}
		for (Listener l: listeners){
			l.handleMessage(message);
		}
	}
}
