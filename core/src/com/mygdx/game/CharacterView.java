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

	
	private Vector2 lookDirection;
	private Vector2 position;
	private float healthPoints;
	private boolean isRunning;
	
	
	
	public SpriteBatch batch;
	TextureRegion[] walkFrames;
	Sprite sprite;
	Animation walkAnimation;
	TextureRegion currentFrame;
	float stateTime;
	
	int sprite_width;
	int spriteLength;
	
	/**
	 * 
	 * @param sprite_path - Path de la textura a mostrar
	 * @param sprite_width - Ancho de la textura
	 * @param spriteLength - Largo de cada frame
	 * @param animation_length - Cantidad de fotogramas en la animacion
	 */
	public CharacterView(String sprite_path,
			int sprite_width,
			int spriteLength, 
			int animation_length){
		
		batch   = new SpriteBatch();
		Texture spriteTexture = new Texture(Gdx.files.internal(sprite_path));
		
		TextureRegion[][] tmp = TextureRegion.split(spriteTexture, sprite_width, spriteLength);
		walkFrames = new TextureRegion[animation_length];
		for(int i=0; i<animation_length;i++){
			walkFrames[i] = tmp[0][i];
		}
		walkAnimation = new Animation(0.025f,walkFrames);
		stateTime = 0f;
		
		this.sprite_width = sprite_width;
		this.spriteLength = spriteLength;
	}
	

	/**
	 * Dibuja el sprite en pantalla.
	 */
	public void draw(){
		
		update();
		
		currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		batch.begin();
		float rotation = lookDirection.angle() + 90f;
		batch.draw(currentFrame, position.x, position.y, 9f, 6.5f,18f,13f, 1f,1f, rotation);
		batch.end();
	}
	/**
	 * Actualiza la animacion segun el tiempo que paso desde el ultimo render.
	 */
	public void update(){
		 stateTime += Gdx.graphics.getDeltaTime();
	}
	
	public void updateInfo(Vector2 position, Vector2 lookDirection,float hp,boolean isRunning) {
		this.position = position;
		this.lookDirection = lookDirection;
		this.healthPoints= hp;
		this.isRunning = isRunning;	
	}
	

	public Vector2 getLookDirection() {
		return lookDirection;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public boolean isRunning() {
		return isRunning;
	}

	public float getHealthPoints() {
		return healthPoints ;
	}


	public float getWidth() {
		return sprite_width;
	}


	public float getHeight() {
		return sprite.getHeight();
	}
	
}
