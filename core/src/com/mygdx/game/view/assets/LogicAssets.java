package com.mygdx.game.view.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Clase de constantes relacionadas a las Animaciones y las Texturas, usada por
 * GameManager para la creacion de las Views
 * 
 * @author traies
 * @author masaques
 * @see com.mygdx.game.controller.GameManager
 */
public class LogicAssets {

	private static final float FRAME_DURATION = 0.05f;
	public static final Animation walkAnimation = makeAnimation("assets/goon_straight_walk.png", 18, 13, 15,
			FRAME_DURATION);
	public static final Animation hurtWalkAnimation = makeAnimation("assets/goon_hit.png", 18, 13, 15, FRAME_DURATION);
	public static final Animation shootAnimation = makeAnimation("assets/goon_shooting_walk.png", 18, 20, 15,
			FRAME_DURATION);
	public static final Animation hurtShootAnimation = makeAnimation("assets/goon_shooting_hit.png", 18, 20, 15,
			FRAME_DURATION);
	public static final Texture deadTexture = new Texture(Gdx.files.internal("assets/goon_dead.png"));
	public static final TextureRegion deadTextureRegion = new TextureRegion(deadTexture);
	public static final Texture interrogationTexture = new Texture(
			Gdx.files.internal("assets/sprite_interrogation.png"));
	public static final TextureRegion interrogationTextureRegion = new TextureRegion(interrogationTexture);
	public static final Texture exclamationTexture = new Texture(Gdx.files.internal("assets/sprite_exclamation.png"));
	public static final TextureRegion exclamationTextureRegion = new TextureRegion(exclamationTexture);
	public static final Animation playerWalkAnimation = makeAnimation("assets/hitman_straight_walk.png", 18, 13, 15,
			FRAME_DURATION);
	public static final Animation playerHurtWalkAnimation = makeAnimation("assets/hitman_hit.png", 18, 13, 15,
			FRAME_DURATION);
	public static final Animation playerShootAnimation = makeAnimation("assets/hitman_shooting_walk.png", 18, 20, 15,
			FRAME_DURATION);
	public static final Animation playerHurtShootAnimation = makeAnimation("assets/hitman_shooting_hit.png", 18, 20, 15,
			FRAME_DURATION);
	public static final Texture playerDeadTexture = new Texture(Gdx.files.internal("assets/hitman_dead.png"));
	public static final TextureRegion playerDeadTextureRegion = new TextureRegion(playerDeadTexture);

	public static final Animation civilianWalkAnimation = makeAnimation("assets/civilian_straight_walk.png", 18, 13, 15,
			FRAME_DURATION);
	public static final Animation civilainHurtWalkAnimation = makeAnimation("assets/civilian_hit.png", 18, 13, 15,
			FRAME_DURATION);
	public static final Texture civilianDeadTexture = new Texture(Gdx.files.internal("assets/civilian_dead.png"));
	public static final TextureRegion civilianDeadTextureRegion = new TextureRegion(civilianDeadTexture);

	/**
	 * Crea una animacion a partir de un path, las dimensiones del sprite y la
	 * cantidad de frames.
	 * 
	 * @param path
	 * @param spriteWidth
	 * @param spriteHeight
	 * @return
	 */
	private static Animation makeAnimation(String path, int spriteWidth, int spriteHeight, int length,
			float frameDuration) {

		Texture normalSpriteTexture = new Texture(Gdx.files.internal(path));
		TextureRegion[][] tmp = TextureRegion.split(normalSpriteTexture, spriteWidth, spriteHeight);
		TextureRegion[] normalWalkFrames = new TextureRegion[length];
		for (int i = 0; i < length; i++) {
			normalWalkFrames[i] = tmp[0][i];
		}
		return new Animation(frameDuration, normalWalkFrames);
	}

}
