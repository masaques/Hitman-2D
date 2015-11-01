package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.model.character.NPC;
import com.mygdx.game.model.character.NPCState;

public class NPCView extends CharacterView<NPC> {
	Texture exclamation;
	Texture interrogation;
	
	public NPCView(String sprite_path, int sprite_width, int spriteLength, int animation_length) {
		super(sprite_path, sprite_width, spriteLength, animation_length);
		exclamation    = new Texture(Gdx.files.internal("assets/sprite_exclamation.png"));
		interrogation = new Texture(Gdx.files.internal("assets/sprite_interrogation.png"));
	}
	
	@Override
	public void draw(){
		super.draw();
		float balloonPositionX = character.getPosition().x + character.getWidth() / 2 - 5f;
		float balloonPositionY = character.getPosition().y + character.getHeight();
		batch.begin();
		if (character.getState() == NPCState.ALARM) {
			batch.draw(exclamation, balloonPositionX, balloonPositionY );
		}
		else if (character.getState() == NPCState.SUSPICIOUS) {
			batch.draw(interrogation, balloonPositionX, balloonPositionY );
		}
		batch.end();
	}
}
