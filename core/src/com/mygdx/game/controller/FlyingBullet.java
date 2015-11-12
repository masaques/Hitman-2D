package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Clase auxiliar del controller que almacena las balas "volando" en el juego.
 * Guarda el origen, el destino y la duracion que lleva en el aire.
 * 
 */
public class FlyingBullet {
	private Vector2 origin;
	private Vector2 end;
	private float timer;

	/**
	 * Recibe la posicion de origen y la posicion de la interseccion.
	 * 
	 * @param origin - Origen de la bala
	 * @param end - Posicion de colision de la bala
	 */
	public FlyingBullet(Vector2 origin, Vector2 end) {
		this.origin = new Vector2(origin);
		this.end = new Vector2(end);
	}

	/**
	 * Actualiza el tiempo que lleva en el aire.
	 */
	public void update() {
		timer += Gdx.graphics.getDeltaTime();
	}

	/**
	 * Devuelve el tiempo que lleva en el "aire".
	 */
	public float getTimer() {
		return timer;
	}

	/**
	 * Devuelve el origen de la bala.
	 * 
	 * @return origin
	 */
	public Vector2 getOrigin() {
		return new Vector2(origin);
	}

	/**
	 * Devuelve el destino de la bala.
	 * 
	 * @return end
	 */
	public Vector2 getEnd() {
		return new Vector2(end);
	}
}
