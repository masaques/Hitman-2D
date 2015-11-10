package com.mygdx.game.view.assets;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.character.Civilian;

public class CivilianView extends NPCView<Civilian> {

	public CivilianView(SpriteBatch batch, Animation walkAnimation, Animation hurtAnimation,
			TextureRegion deadTextureRegion, TextureRegion exclamationTextureRegion,
			TextureRegion interrogationTextureRegion) {
		super(batch, walkAnimation, hurtAnimation, deadTextureRegion, exclamationTextureRegion,
				interrogationTextureRegion);
	}

}
