package com.mygdx.game.view.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.character.Player;

public class PlayerView extends CharacterView<Player> {
	private static final float SHOOT_DURATION = 1f;
	private Animation shootWalkAnimation;
	private Animation shootHurtAnimation;
	private float shootTimer;
	private boolean isShooting;

	public PlayerView(SpriteBatch batch, Animation walkAnimation, Animation hurtAnimation, Animation shootWalkAnimation,
			Animation shootHurtAnimation, TextureRegion deadTextureRegion) {
		super(batch, walkAnimation, hurtAnimation, deadTextureRegion);
		this.shootWalkAnimation = shootWalkAnimation;
		this.shootHurtAnimation = shootHurtAnimation;
	}

	@Override
	public void draw() {
		if (!isDead() && isShooting) {
			if (shootTimer >= SHOOT_DURATION) {
				shootTimer = 0;
				isShooting = false;
			} else {
				shootTimer += Gdx.graphics.getDeltaTime();

				if (isHurt()) {
					setCurrentFrame(shootHurtAnimation.getKeyFrame(getStateTime(), true));
				} else {
					setCurrentFrame(shootWalkAnimation.getKeyFrame(getStateTime(), true));
				}
			}
			drawFrame();
		} else {
			super.draw();
		}

	}

	public void setShooting(boolean isShooting) {
		shootTimer = 0f;
		this.isShooting = isShooting;
	}

}
