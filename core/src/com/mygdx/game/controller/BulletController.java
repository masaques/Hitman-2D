package com.mygdx.game.controller;

import java.util.List;

import com.mygdx.game.model.message.BulletManager;
import com.mygdx.game.view.assets.BulletView;

/**
 * TODO deberia extender de una interfaz controller.
 * @author traies
 *
 */
public class BulletController {
	private BulletManager bulletManager;
	private BulletView bulletView;
	
	
	
	public BulletController() {
		this.bulletManager = BulletManager.getInstance();
		this.bulletView = new BulletView();
	}
	
	public void manage(){
		updateModel();
		updateView();
	}
	
	public void updateModel() {
		bulletManager.update();
	}
	/**
	 * Dibuja la lista de balas volando.Ademas, actualiza su timer.
	 */
	public void updateView() {
		List<FlyingBullet> flyingBullets = bulletManager.getFlyingBullets();
		bulletView.addFlyingBullets(flyingBullets);
		bulletView.update();
		bulletView.draw();
	}
	
}
