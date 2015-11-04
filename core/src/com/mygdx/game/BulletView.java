package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class BulletView {
	private List<FlyingBullet> bullets;
	private ShapeRenderer bulletRenderer;
	private static final float BULLET_DURATION = .5f;
	
	public BulletView() {
		bulletRenderer = new ShapeRenderer();
		bullets = new ArrayList<FlyingBullet>();
	}
	public void addFlyingBullets(List<FlyingBullet> newBullets) {
		bullets.addAll(newBullets);
	}
	
	public void draw() {
		bulletRenderer.begin(ShapeType.Line);
		bulletRenderer.setColor(1,0,0,1); /* Amarillo */
		
		for (FlyingBullet b: bullets) {
			bulletRenderer.line(b.getOrigin(), b.getEnd());
		}
		
		bulletRenderer.end();
	}
	public void update() {
		List<FlyingBullet> removeList = new ArrayList<FlyingBullet>();
		for (FlyingBullet b:bullets) {
			b.update();
			if (b.getTimer() > BULLET_DURATION) {
				removeList.add(b);
			}
		}
		bullets.removeAll(removeList);
	}
}
