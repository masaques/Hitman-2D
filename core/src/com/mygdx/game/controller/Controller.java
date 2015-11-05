package com.mygdx.game.controller;

import com.mygdx.game.model.Model;
import com.mygdx.game.view.assets.View;
/**
 * Clase abstracta que controla un modelo y a su view correspondiente.
 * @author traies
 * 
 * @param <M>
 * @param <V>
 */
public abstract class Controller<M extends Model, V extends View> {
	private M model;
	private V view;
	
	/**
	 * Inicializa con un modelo y un view.
	 * @param model
	 * @param view
	 */
	public Controller(M model,V view) {
		this.model = model;
		this.view = view;
	}
	/**
	 * Actualiza el modelo.
	 */
	public void updateModel() {
		model.update();
	}
	
	/**
	 * Dibuja el view.
	 */
	public void updateView() {
		view.draw();
	}
	
	/**
	 * TODO este metodo debe ser protected. Mover el metodo dump de character a aca.
	 * @return
	 */
	protected M getModel(){
		return model;
	}
	/**
	 * TODO este metodo debe ser protected.
	 * @return
	 */
	protected V getView(){
		return view;
	}
	
	
}
