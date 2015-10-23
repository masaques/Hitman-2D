
package com.mygdx.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * WIP
 * Control system for emitted noises
 * @author masaques
 * @author traies
 *
 */


public class NoiseFilter implements MessageFilter{
	
	
	public NoiseFilter(){
	}

	@Override
	public Set<Listener> filter (Message message, Set<Listener> listeners) {
		if (message.getClass() != Noise.class){
			throw new WrongMessageException();
		}
		Noise noise = (Noise)message;
		if (listeners == null) {
			throw new IllegalArgumentException();
		}
		
		Set<Listener> removeSet = new HashSet<Listener>();
		for (Listener l : listeners){
			NPC npc = (NPC)l;
			if (npc.getPosition().dst(noise.getPosition()) > noise.getRange()) {
				removeSet.add(l);
			}
		}
		listeners.removeAll(removeSet);
		return listeners;
	}
}
