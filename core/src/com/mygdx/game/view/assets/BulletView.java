package com.mygdx.game.view.assets;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.controller.FlyingBullet;

public class BulletView implements View{
	private List<FlyingBullet> bullets;
	private ShapeRenderer bulletRenderer;
	private Camera camera;
	private static final float BULLET_DURATION = .05f;

	public BulletView(Camera camera) {
		this.camera = camera;
		bulletRenderer = new ShapeRenderer();
		bullets = new ArrayList<FlyingBullet>();
	}

	public void addFlyingBullets(List<FlyingBullet> newBullets) {
		bullets.addAll(newBullets);
	}

	public void draw() {
		bulletRenderer.setProjectionMatrix(camera.combined);
		bulletRenderer.begin(ShapeType.Line);
		bulletRenderer.setColor(1, 0, 0, 1); /* Amarillo */

		for (FlyingBullet b : bullets) {
			bulletRenderer.line(b.getOrigin(), b.getEnd());
		}

		bulletRenderer.end();
	}

	public void update() {
		List<FlyingBullet> removeList = new ArrayList<FlyingBullet>();
		for (FlyingBullet b : bullets) {
			b.update();
			if (b.getTimer() > BULLET_DURATION) {
				removeList.add(b);
			}
		}
		bullets.removeAll(removeList);
	}
}
