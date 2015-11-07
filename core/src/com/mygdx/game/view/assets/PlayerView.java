package com.mygdx.game.view.assets;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.character.Player;

public class PlayerView extends CharacterView<Player> {

	public PlayerView(
			SpriteBatch batch,
			Animation walkAnimation,
			Animation hurtAnimation,
			Animation shootWalkAnimation,
			Animation shootHurtAnimation,
			TextureRegion deadTextureRegion) {
		super(batch, walkAnimation,hurtAnimation,shootWalkAnimation,shootHurtAnimation,deadTextureRegion);
		// TODO Auto-generated constructor stub
	}

}
