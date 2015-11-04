package com.mygdx.game.model.message;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.FlyingBullet;
import com.mygdx.game.LevelMap;
/**
 * <p>Clase <b>Singleton</b> que extiende de <b>MessageManager</b>
 * para el manejo de las balas y los <b>BulletListener</b>.</p>
 * 
 * @author masaques
 * @see MessageManager
 * @see BulletListener
 *
 */


public class BulletManager extends MessageManager<BulletListener, Bullet> {
	/**
	 *  <p>Variable que genera un unico MessageManager de manera que sea un Singleton.</p>
	 */
	private static final BulletManager INSTANCE = new BulletManager();
	private List<FlyingBullet> flyingBullets;
	private LevelMap map;
	/**
	 *  Esta implementada como un Singleton (solo exite una instancia de esta clase), asi que 
	 *  el constructor es protected. Si obetener la instancia, usar getInstace en lugar de new.
	 */
	private BulletManager(){
		super();
		flyingBullets = new ArrayList<FlyingBullet>();
	}
	
	public void setMap(LevelMap map){
		this.map = map;
	}
	
	public static BulletManager getInstance() {
		return INSTANCE;
	}
	
	@Override
	protected Set<BulletListener> filter( Bullet bullet, Set<BulletListener> set) {
		if (map == null) {
			throw new NoMapException();
		}
		Vector2 collision = map.getBulletCollision(bullet);
		
		if (collision == null) {
			throw new IllegalBulletException();
		}
		set.remove(bullet.getShooter());
		float maxRange = bullet.getPosition().dst(collision);
		BulletListener reciever = null;
		for (BulletListener l:set) {
			Vector2 listenerCollision = bullet.intersects(l.getHitBox());
			float distance = bullet.getPosition().dst(listenerCollision);
			if (!listenerCollision.isZero() && distance < maxRange){
				collision = listenerCollision;
				reciever = l;
				maxRange = distance;
			}
		}
		FlyingBullet flyingBullet;
		Set<BulletListener> recieveSet = new HashSet<BulletListener>();
		if (reciever != null){
			recieveSet.add(reciever);
		}
		
		flyingBullet = new FlyingBullet(bullet.getPosition(), collision);
		flyingBullets.add(flyingBullet);
		return recieveSet;
	}
	
	public List<FlyingBullet> getFlyingBullets() {
		List<FlyingBullet> aux = flyingBullets;
		flyingBullets = new ArrayList<FlyingBullet>();
		return aux;
	}
}
