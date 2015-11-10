package com.mygdx.game.view.assets;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.character.NPC;
import com.mygdx.game.model.character.NPCState;

public abstract class NPCView<M extends NPC> extends CharacterView<M> {
	TextureRegion exclamation;
	TextureRegion interrogation;

	private NPCState currentState;

	public NPCView(SpriteBatch batch, Animation walkAnimation, Animation hurtAnimation, TextureRegion deadTextureRegion,
			TextureRegion exclamationTextureRegion, TextureRegion interrogationTextureRegion) {
		super(batch, walkAnimation, hurtAnimation, deadTextureRegion);
		exclamation = exclamationTextureRegion;
		interrogation = interrogationTextureRegion;
	}

	@Override
	public void draw() {
		super.draw();
		drawBallon();
	}

	public void drawBallon() {
		if (!isDead()) {
			float balloonPositionX = getPosition().x - 5f;
			float balloonPositionY = getPosition().y + getHeight();
			batch.begin();
			if (currentState == NPCState.ALARM) {
				batch.draw(exclamation, balloonPositionX, balloonPositionY);
			} else if (currentState == NPCState.SUSPICIOUS) {
				batch.draw(interrogation, balloonPositionX, balloonPositionY);
			}
			batch.end();
		}
	}

	public void updateState(NPCState npcState) {
		currentState = npcState;
	}

}
