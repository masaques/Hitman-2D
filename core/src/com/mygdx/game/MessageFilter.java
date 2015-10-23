package com.mygdx.game;

import java.util.Set;

public interface MessageFilter {
	Set<Listener> filter (Message message, Set<Listener> baseListeners);
}
