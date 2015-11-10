package com.mygdx.game.model.util;

import com.mygdx.game.model.character.Context;

public interface State<T> {
	void updateState(StateMachine<T> owner, Context context);
}
