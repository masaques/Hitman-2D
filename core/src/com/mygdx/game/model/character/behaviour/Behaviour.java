package com.mygdx.game.model.character.behaviour;

import com.mygdx.game.model.character.Context;

public interface Behaviour<T> {
	public void behave (T t, Context context);
}
