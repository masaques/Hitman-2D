package com.mygdx.game;

/**
 * Clase singleton para el manejo de las balas
 * @author masaques
 *
 */


public class BulletManager extends MessageManager<BulletListener, Bullet> {
	private static final BulletManager INSTANCE = new BulletManager();
	
	private BulletManager(){
		super();
	}
	
	public static BulletManager getInstance() {
		return INSTANCE;
	}
}
