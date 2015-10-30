package com.mygdx.game;

public class NullRequest<T> implements ActionRequest<T> {
	public void execute(T t) {
		return;
	}
}
