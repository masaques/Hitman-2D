/*
 *@author Tomas Raies
 *@date   13 de oct. de 2015
 */

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import serialization.CharacterInformation;

/**
 * El personaje jugable. Extiende de {@link Character}.
 */
public class Player extends Character implements VisionSender , Aggressive {
	private static final double RUNNING_NOISE_RANGE = 200;
	
	public Player(Rectangle hitBox, LevelMap map) {
		super(hitBox, map);
	}
	/**
	 * Constructor alternativo usado al cargar la informacion desde un archivo
	 * @param data
	 * @param map
	 * @see Character
	 */
	public Player(CharacterInformation data,LevelMap map) {
		super(data,map);
	}
	@Override
	public void update() {
		sendPosition() ;
		super.update();
	}
	
	public void stopMoving() {
		this.isMoving = false;
	}
	@Override
	public void sendPosition() {
		VisionManager.getInstance().dispatchMessage(new Vision(this,map));
		if (isMoving() && isRunning()) {
			NoiseManager.getInstance().dispatchMessage(new Noise(this.getPosition(), RUNNING_NOISE_RANGE, false));
		}
	}
	/**
	 * El parametro to aca deberia ser el input del mouse
	 */
	@Override
	public void shoot(Vector2 to) {
		Vector2 relative = to.sub(this.getPosition()).nor();
		BulletManager.getInstance().dispatchMessage(new Bullet(this,this.getPosition(),relative,map));
		NoiseManager.getInstance().dispatchMessage(new Noise(this.getPosition(),100,true));
	}
	

}
