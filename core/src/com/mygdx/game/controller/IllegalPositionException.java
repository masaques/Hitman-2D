package com.mygdx.game.controller;

public class IllegalPositionException extends Exception {

	private static final long serialVersionUID = -8340574912631386046L;

	public IllegalPositionException() {
		super("Error: Character en posicion invalida");
	}
}
