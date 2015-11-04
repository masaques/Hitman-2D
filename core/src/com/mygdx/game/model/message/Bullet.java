package com.mygdx.game.model.message;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.character.Character;

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
	public Bullet(Character source, Vector2 position, Vector2 direction){
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
	
	/**
	 * Metodo que calcula si, dado un BulletListener, la bala le da
	 * 
	 * @param l - BulletListener a analizar
	 */
	@Override
	public void notify(BulletListener l) {
		l.dealDamage(getDamage());
		
	}
	/**
	 * Metodo privado que verifica en cada uno de los segmentos del
	 * hitBox del listener si el segmento que representa la Bullet lo
	 * intersecta
	 * @param hitBox
	 * @return true si lo intersecta, false si no
	 */
	public Vector2 intersects(Rectangle hitBox) {
		Vector2 p1 = new Vector2(hitBox.x,hitBox.y) ;
		Vector2 p2 = new Vector2(hitBox.x + hitBox.width, hitBox.y);
		Vector2 p3 = new Vector2(hitBox.x + hitBox.width, hitBox.y + hitBox.height);
		Vector2 p4 = new Vector2(hitBox.x, hitBox.y + hitBox.height);
		Segment[] segments = new Segment[4] ;
		segments[0] = new Segment(p1,p2) ;
		segments[1] = new Segment(p2,p3) ;
		segments[2] = new Segment(p3,p4) ;
		segments[3] = new Segment(p4,p1) ;
		
		
		Vector2 nearestCollision = new Vector2();
		float minDistance = RANGE;
		Vector2 pos2 = new Vector2(this.position).add(new Vector2(this.direction).scl(RANGE));
		Vector2 pos1 = new Vector2(this.position);
		for (Segment s : segments) {
			Vector2 collision = new Vector2();
			if(Intersector.intersectSegments(s.p, s.q,pos1,pos2, collision)) {
				float distance = position.dst(collision);
				if (distance < minDistance) {
					nearestCollision = collision;
					minDistance = distance;
				}
			}
		}
		return nearestCollision;
		
	}
	
	private class Segment {
		protected Vector2 p ;
		protected Vector2 q ;
		
		protected Segment(Vector2 p, Vector2 q)  {
			this.p = new Vector2(p)  ;
			this.q = new Vector2(q) ;
		}
	}
}
