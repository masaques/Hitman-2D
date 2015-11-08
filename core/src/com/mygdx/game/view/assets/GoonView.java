package com.mygdx.game.view.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.character.Goon;

public class GoonView extends NPCView<Goon> {
	
	private static final float SHOOT_DURATION = 1f;
	private Animation shootWalkAnimation;
	private Animation shootHurtAnimation;
	private float shootTimer;
	private boolean isShooting;
	
	public GoonView(SpriteBatch batch, 
			Animation walkAnimation, 
			Animation hurtAnimation, 
			Animation shootWalkAnimation,
			Animation shootHurtAnimation, 
			TextureRegion deadTextureRegion, 
			TextureRegion exclamationTextureRegion,
			TextureRegion interrogationTextureRegion) {
		
		super(batch, walkAnimation, hurtAnimation, deadTextureRegion, exclamationTextureRegion, interrogationTextureRegion);
		this.shootWalkAnimation = shootWalkAnimation;
		this.shootHurtAnimation = shootHurtAnimation;
	}
	
	@Override
	public void draw() {
		if (!isDead() && isShooting) {
			if (shootTimer >= SHOOT_DURATION){
				shootTimer = 0;
				isShooting = false;
			}
			else {
				shootTimer += Gdx.graphics.getDeltaTime();
			
				if (isHurt()) {
					setCurrentFrame(shootHurtAnimation.getKeyFrame(getStateTime(), true));
				}
				else {
					setCurrentFrame(shootWalkAnimation.getKeyFrame(getStateTime(), true));
				}
			}
			drawFrame();
			drawBallon();
		}
		else {
			super.draw();
		}
		
	}
	
	public void setShooting(boolean isShooting) {
		shootTimer = 0f;
		this.isShooting = isShooting;
	}

}
