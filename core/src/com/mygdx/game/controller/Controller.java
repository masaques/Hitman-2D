package com.mygdx.game.controller;

/**
 * Clase abstracta que controla un modelo y a su view correspondiente.
 * 
 * @param <M> - {@link Model}
 * @param <V> - {@link View}
 */
public abstract class Controller<M, V> {
	private M model;
	private V view;

	/**
	 * Inicializa con un modelo y un view.
	 * 
	 * @param model
	 * @param view
	 */
	public Controller(M model, V view) {
		this.model = model;
		this.view = view;
	}

	/**
	 * Actualiza el modelo.
	 */

	public abstract void updateModel();

	/**
	 * Dibuja el view.
	 */

	public abstract void updateView();


	protected M getModel() {
		return model;
	}


	protected V getView() {
		return view;
	}
}
