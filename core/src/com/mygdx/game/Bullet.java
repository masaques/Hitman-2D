package com.mygdx.game;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
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
	 * Metodo que calcula si, dado un BulletListener, la bala le da
	 * 
	 * @param l - BulletListener a analizar
	 */
	@Override
	public void notify(BulletListener l) {
		if (l.getPosition().dst(this.getPosition())<=this.getRange()) {
			if(map.isValid(this.getPosition(), l.getPosition())){
				if (this.intersects(l.getHitBox())) {
					l.dealDamage(this.getDamage());
				}
			}
		}
	}
	/**
	 * Metodo privado que verifica en cada uno de los segmentos del
	 * hitBox del listener si el segmento que representa la Bullet lo
	 * intersecta
	 * @param hitBox
	 * @return true si lo intersecta, false si no
	 */
	private boolean intersects(Rectangle hitBox) {
		Vector2 startPoint = new Vector2(hitBox.x,hitBox.y) ;
		Segment[] segments = new Segment[4] ;
		segments[0] = new Segment(startPoint,new Vector2(startPoint.x+hitBox.width,startPoint.y)) ;
		segments[1] = new Segment(startPoint,new Vector2(startPoint.x,startPoint.y+hitBox.width)) ;
		segments[2] = new Segment(segments[0].q,new Vector2(segments[0].q.x,segments[0].q.y+hitBox.height)) ;
		segments[3] = new Segment(segments[1].q,new Vector2(segments[1].q.x,segments[1].q.y+hitBox.width)) ;
		for (Segment s : segments) {
			if(Intersector.intersectSegments(s.p, s.q,this.position,
					new Vector2(this.position).add(new Vector2(this.direction).scl(RANGE)), new Vector2())) {
				return true ;
			}
		}
		return false ;
		
	}
	
	private class Segment {
		protected Vector2 p ;
		protected Vector2 q ;
		
		protected Segment(Vector2 p, Vector2 q)  {
			this.p = q  ;
			this.p = q ;
		}
	}
}
