package com.mygdx.game;

import java.util.Set;

@Deprecated
public interface MessageFilter {
	Set<Listener> filter (Message message, Set<Listener> baseListeners);
}
