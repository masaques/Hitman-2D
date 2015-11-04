package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.character.Character;

/**
 * Implementacion del View de los Character segun el patron MVC.
 * Dibuja en pantalla el sprite del personaje.
 */
public abstract class CharacterView<T extends Character> {

	private static final float HIT_DURATION = 1f;
	private Vector2 lookDirection;
	private Vector2 position;
	private float healthPoints;
	private boolean isRunning;
	private boolean isMoving;
	private float hitTimer;
	public SpriteBatch batch;
	private Animation normalWalkAnimation;
	private Animation hurtWalkAnimation;
	private TextureRegion currentFrame;
	private TextureRegion deadSpriteFrame;
	private float stateTime;
	
	private int spriteWidth;
	private int spriteHeight;
	private int offsetX;
	private int offsetY;
	private boolean isDead = false;
	
	/**
	 * 
	 * @param sprite_path - Path de la textura a mostrar
	 * @param sprite_width - Ancho de la textura
	 * @param spriteLength - Largo de cada frame
	 * @param animation_length - Cantidad de fotogramas en la animacion
	 */
	public CharacterView(String normalSpritePath,
			String hurtSpritePath,
			//String shootingSpritePath,	TODO
			String deadSpritePath,
			int spriteWidth,
			int spriteHeight, 
			int animation_length){
		
		batch   = new SpriteBatch();
		Texture normalSpriteTexture = new Texture(Gdx.files.internal(normalSpritePath));
		
		TextureRegion[][] tmp = TextureRegion.split(normalSpriteTexture, spriteWidth, spriteHeight);
		
		TextureRegion[] normalWalkFrames = new TextureRegion[animation_length];
		for(int i=0; i<animation_length;i++){
			normalWalkFrames[i] = tmp[0][i];
		}
		normalWalkAnimation = new Animation(0.025f,normalWalkFrames);
		stateTime = 0f;
		
		Texture hurtSpriteTexture = new Texture(Gdx.files.internal(hurtSpritePath));
		
		TextureRegion[][] tmp2 = TextureRegion.split(hurtSpriteTexture, spriteWidth, spriteHeight);
		
		TextureRegion[] hurtWalkFrames = new TextureRegion[animation_length];
		for(int i=0; i<animation_length;i++){
			hurtWalkFrames[i] = tmp2[0][i];
		}
		hurtWalkAnimation = new Animation(0.025f,hurtWalkFrames);
		
		Texture deadSpriteTexture = new Texture(Gdx.files.internal(deadSpritePath));
		deadSpriteFrame = new TextureRegion(deadSpriteTexture);
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.lookDirection = new Vector2();
		this.position = new Vector2();
	}
	

	/**
	 * Dibuja el sprite en pantalla.
	 */
	public void draw(){
		if (!isDead) {
			if (isMoving){
				updateAnimation();
			}
			if (hitTimer >=0) {
				hitTimer -= Gdx.graphics.getDeltaTime();
				currentFrame = hurtWalkAnimation.getKeyFrame(stateTime, true);
			}
			else {
				currentFrame = normalWalkAnimation.getKeyFrame(stateTime, true);
			}
		}
		batch.begin();
		float rotation = lookDirection.angle() + 90f;
		int width = currentFrame.getRegionWidth();
		int height = currentFrame.getRegionHeight();
		batch.draw(currentFrame, position.x, position.y, width / 2, height / 2,width,height, 1f,1f, rotation);
		batch.end();
	}
	/**
	 * Actualiza la animacion segun el tiempo que paso desde el ultimo render.
	 */
	private void updateAnimation(){
		 stateTime += Gdx.graphics.getDeltaTime() / 2;
	}
	
	public void updateInfo(Vector2 position, Vector2 lookDirection,float hp,boolean isRunning) {
		this.position.set(position);
		this.lookDirection.set(lookDirection);
		this.healthPoints= hp;
		this.isRunning = isRunning;	
	}
	

	public Vector2 getLookDirection() {
		return lookDirection;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void setPosition(Vector2 position) {
		this.position.set(position);
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	public void setRunning(boolean running) {
		this.isRunning = running;
	}
	public float getHealthPoints() {
		return healthPoints ;
	}

	public void setHit() {
		this.hitTimer = HIT_DURATION;
	}
	public float getWidth() {
		return spriteWidth;
	}
	
	public void setLookDirection(Vector2 direction) {
		this.lookDirection.set(direction);
	}

	public float getHeight() {
		return spriteHeight;
	}
	
	public void setDead() {
		isDead = true;
		currentFrame = deadSpriteFrame;
	}
	public boolean isDead() {
		return isDead;
	}
}
