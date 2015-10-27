package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Clase que simula un disparo 
 * 
 * @see Message
 * @see BulletListener
 * @see BulletManager
 * 
 * @author masaques
 *
 */


public class Bullet implements Message<BulletListener> {
	private Character source ;
	private Vector2 position ;
	private Vector2 direction ;
	/*
	 * 
	 * por ahora soluciona el problema
	 */
	private LevelMap map ;
	/*
	 * Quizas esto podria ser una variable,
	 * diferentes rangos representan diferentes armas.
	 */
	private static float RANGE = 1500f ;
	/*
	 * Serian como los puntos de vida que disminuye del objetivo
	 * Idem anterior
	 */
	private static float DAMAGE = 50f ;
	
	/**
	 * Genera una bala que tiene un disparador, posicion y dirección.
	 * 
	 * @param source - Personaje que dispara (en caso de que se implemente FriendlyFire)
	 * @param position - Posición de origen
	 * @param direction - Dirección de trayectoria
	 * @param map - Mapa del juego
	 */
	public Bullet(Character source, Vector2 position, Vector2 direction,LevelMap map){
		this.source = source ;
		this.position = position ;
		this.direction = direction ;
		this.map = map ;
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
	
	/**
	 * Método que calcula si, dado un BulletListener, la bala le dió
	 * 
	 * @param l - BulletListener a analizar
	 */
	@Override
	public void notify(BulletListener l) {
		if (l.getPosition().dst(this.getPosition())<=this.getRange()) {
			if(l.getPosition().sub(this.getPosition()).nor().isCollinear(this.getDirection())){
				if (map.isValid(this.getPosition(), l.getPosition())) {
					l.dealDamage(this.getDamage());
				}
			}
		}
	}
	
}
