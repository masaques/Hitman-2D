package com.mygdx.game.view.assets;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TargetView extends GoonView {

	public TargetView(SpriteBatch batch, Animation walkAnimation, Animation hurtAnimation, Animation shootWalkAnimation,
			Animation shootHurtAnimation, TextureRegion deadTextureRegion, TextureRegion exclamationTextureRegion,
			TextureRegion interrogationTextureRegion) {
		super(batch, walkAnimation, hurtAnimation, shootWalkAnimation, shootHurtAnimation, deadTextureRegion,
				exclamationTextureRegion, interrogationTextureRegion);
		// TODO Auto-generated constructor stub
	}

}
