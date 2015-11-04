package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.character.NPC;
import com.mygdx.game.model.character.NPCState;

public class NPCView extends CharacterView<NPC> {
	Texture exclamation;
	Texture interrogation;
	
	private NPCState currentState;
	
	public NPCView(String sprite_path, String hurt_sprite_path, 
			String deadSpritePath,
			int sprite_width, int spriteLength, int animation_length) {
		super(sprite_path, hurt_sprite_path, deadSpritePath, sprite_width, spriteLength, animation_length);
		exclamation    = new Texture(Gdx.files.internal("assets/sprite_exclamation.png"));
		interrogation = new Texture(Gdx.files.internal("assets/sprite_interrogation.png"));
	}
	
	@Override
	public void draw(){
		super.draw();
		if (!isDead()){
			float balloonPositionX = getPosition().x + getWidth() / 2 - 5f;
			float balloonPositionY = getPosition().y + getHeight();
			batch.begin();
			if (currentState == NPCState.ALARM) {
				batch.draw(exclamation, balloonPositionX, balloonPositionY );
			}
			else if (currentState == NPCState.SUSPICIOUS) {
				batch.draw(interrogation, balloonPositionX, balloonPositionY );
			}
			batch.end();
		}
	}
	
	public void updateState(NPCState npcState){
		currentState = npcState;
	}
	
	
	
}
