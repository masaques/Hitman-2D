package com.mygdx.game.controller;

import java.util.List;

import com.mygdx.game.model.message.BulletManager;
import com.mygdx.game.view.assets.BulletView;

/**
 *	Se encarga de generar las lineas que se veran por pantalla que representan las balas
 */
public class BulletController {
	private BulletManager bulletManager;
	private BulletView bulletView;

	public BulletController(BulletManager bulletManager, BulletView bulletView) {
		this.bulletManager = bulletManager;
		this.bulletView = bulletView;
	}

	public void manage() {
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
