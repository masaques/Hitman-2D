package com.mygdx.game.model.character.behaviour;

import com.mygdx.game.model.message.Bullet;
import com.mygdx.game.model.message.BulletListener;
import com.mygdx.game.model.message.MessageManager;

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
	
	/**
	 *  Esta implementada como un Singleton (solo exite una instancia de esta clase), asi que 
	 *  el constructor es protected. Si obetener la instancia, usar getInstace en lugar de new.
	 */
	private BulletManager(){
		super();
	}
	
	public static BulletManager getInstance() {
		return INSTANCE;
	}
}
