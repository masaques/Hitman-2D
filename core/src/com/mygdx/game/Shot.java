package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Clase que simula un disparo 
 * @author masaques
 *
 */


public class Shot implements Message {
	private Character source ;
	private Vector2 position ;
	private Vector2 direction ;
	/**
	 * Quizas esto podria ser una variable,
	 * diferentes rangos representan diferentes armas.
	 */
	private static float RANGE = 1500f ;
	/**
	 * Serian como los puntos de vida que disminuye del objetivo
	 * Idem anterior
	 */
	private static float DAMAGE = 50f ;
	
	/**
	 * Quizas no deberia recibir source?
	 * Lo hago por si queremos revisar cuestiones 
	 * sobre fuego amigo o no.
	 * @param source
	 * @param position
	 * @param direction
	 */
	public Shot(Character source, Vector2 position, Vector2 direction){
		this.source = source ;
		this.position = position ;
		this.direction = direction ;
	}
	
	public Character getShooter(){
		return source ;
	}
	
	public Vector2 getPosition(){
		return position ;
	}
	
	public Vector2 getDirection() {
		return direction ;
	}
	
	public float getRange() {
		return RANGE ;
	}
	
	public float getDamage() {
		return DAMAGE ;
	}
	
}
