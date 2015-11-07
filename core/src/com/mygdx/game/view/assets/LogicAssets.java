package com.mygdx.game.view.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LogicAssets {
	
	private static final float FRAME_DURATION = 0.05f;
	public static Animation walkAnimation = makeAnimation("assets/goon_straight_walk.png", 18,13,15, FRAME_DURATION);
	public static Animation hurtWalkAnimation = makeAnimation("assets/goon_hit.png", 18,13,15, FRAME_DURATION);
	public static Animation shootAnimation = makeAnimation("assets/goon_shooting_walk.png", 18,20,15,FRAME_DURATION);
	public static Animation hurtShootAnimation = makeAnimation("assets/goon_shooting_hit.png",18, 20,15,FRAME_DURATION);
	public static Texture deadTexture = new Texture(Gdx.files.internal("assets/goon_dead.png"));
	public static TextureRegion deadTextureRegion = new TextureRegion(deadTexture);
	public static Texture interrogationTexture = new Texture(Gdx.files.internal("assets/sprite_interrogation.png"));
	public static TextureRegion interrogationTextureRegion = new TextureRegion(interrogationTexture);
	public static Texture exclamationTexture = new Texture(Gdx.files.internal("assets/sprite_exclamation.png"));
	public static TextureRegion exclamationTextureRegion = new TextureRegion(exclamationTexture);
	public static Animation playerWalkAnimation         = makeAnimation("assets/hitman_straight_walk.png", 18,13,15, FRAME_DURATION);
	public static Animation playerHurtWalkAnimation     = makeAnimation("assets/hitman_hit.png", 18,13,15, FRAME_DURATION);
	public static Animation playerShootAnimation        = makeAnimation("assets/hitman_shooting_walk.png", 18,20,15,FRAME_DURATION);
	public static Animation playerHurtShootAnimation    = makeAnimation("assets/hitman_shooting_hit.png",  18,20,15,FRAME_DURATION);
	public static Texture playerDeadTexture             = new Texture(Gdx.files.internal("assets/hitman_dead.png"));
	public static TextureRegion playerDeadTextureRegion = new TextureRegion(playerDeadTexture);
	
	
	/**
	 * Crea una animacion a partir de un path, las dimensiones del sprite y la cantidad de frames.
	 * @param path
	 * @param spriteWidth
	 * @param spriteHeight
	 * @return
	 */
	private static Animation makeAnimation(String path, int spriteWidth, int spriteHeight, int length, float frameDuration) {
		
		Texture normalSpriteTexture = new Texture(Gdx.files.internal(path));
		TextureRegion[][] tmp = TextureRegion.split(normalSpriteTexture, spriteWidth, spriteHeight);
		TextureRegion[] normalWalkFrames = new TextureRegion[length];
		for(int i=0; i<length;i++){
			normalWalkFrames[i] = tmp[0][i];
		}
		return new Animation(frameDuration,normalWalkFrames);
	}
	
}
