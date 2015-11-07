package com.mygdx.game.view.assets;

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
public abstract class CharacterView<T extends Character> implements View{

	private static final float SHOOT_DURATION = 1f;
	private Vector2 lookDirection;
	private Vector2 position;
	public SpriteBatch batch;
	private Animation walkAnimation;
	private Animation hurtWalkAnimation;
	private Animation shootWalkAnimation;
	private Animation shootHurtAnimation;
	private TextureRegion currentFrame;
	private TextureRegion deadSpriteFrame;
	private float stateTime;
	private float healthPoints;
	private float shootTimer;
	private boolean isShooting;
	private boolean isDead;
	private boolean isRunning;
	private boolean isMoving;
	private boolean isHurt;
	/**
	 * 
	 * @param sprite_path - Path de la textura a mostrar
	 * @param sprite_width - Ancho de la textura
	 * @param spriteLength - Largo de cada frame
	 * @param animation_length - Cantidad de fotogramas en la animacion
	 */
	public CharacterView(
			SpriteBatch batch,
			Animation walkAnimation,
			Animation hurtAnimation,
			Animation shootWalkAnimation,
			Animation shootHurtAnimation,
			TextureRegion deadTextureRegion
			){
		
		this.batch   = batch;
		this.walkAnimation = walkAnimation;
		this.hurtWalkAnimation  = hurtAnimation;
		this.shootWalkAnimation = shootWalkAnimation;
		this.shootHurtAnimation = shootHurtAnimation;
		this.deadSpriteFrame = deadTextureRegion;
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
			if (isShooting && shootTimer >= SHOOT_DURATION){
				shootTimer = 0;
				isShooting = false;
			}
			else if(isShooting) {
				shootTimer += Gdx.graphics.getDeltaTime();
			}
			if (isHurt) {
				if (isShooting) {
					currentFrame = shootHurtAnimation.getKeyFrame(stateTime, true);
				}
				else {
					currentFrame = hurtWalkAnimation.getKeyFrame(stateTime, true);
				}
				
			}
			else {
				if (isShooting) {
					currentFrame = shootWalkAnimation.getKeyFrame(stateTime, true);
					System.out.println(isShooting);
				}
				else {
					currentFrame = walkAnimation.getKeyFrame(stateTime, true);
				}
				
			}
		}
		batch.begin();
		float rotation = lookDirection.angle() + 90f;
		int width = currentFrame.getRegionWidth();
		int height = currentFrame.getRegionHeight();
		batch.draw(currentFrame, position.x - width/2, position.y - height /2, width / 2, height / 2,width,height, 1f,1f, rotation);
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
		this.isHurt = true;
	}
	
	public void setLookDirection(Vector2 direction) {
		this.lookDirection.set(direction);
	}

	
	public void die() {
		isDead = true;
		currentFrame = deadSpriteFrame;
	}
	public boolean isDead() {
		return isDead;
	}
	
	public int getWidth() {
		return currentFrame.getRegionWidth();
	}
	
	public int getHeight() {
		return currentFrame.getRegionHeight();
	}
	public void setShooting(boolean isShooting) {
		
		shootTimer = 0f;
		this.isShooting = isShooting;
	}
}
