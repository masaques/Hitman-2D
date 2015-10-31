package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Implementacion del View de los Character segun el patron MVC.
 * Dibuja en pantalla el sprite del personaje.
 */
public abstract class CharacterView<T extends Character> {
	T character;
	SpriteBatch batch;
	TextureRegion[] walkFrames;
	Sprite sprite;
	Animation walkAnimation;
	TextureRegion currentFrame;
	float stateTime;
	
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
	}
	/**
	 * Setea una referencia al personaje. En una revision futura, deberia setear a un CharacterController,
	 * que represente al controller del player segun el patron MVC.
	 * @param character - {@link Character} a referenciar
	 */
	public void setPlayer(T character){
		this.character = character;
	}
	/**
	 * Dibuja el sprite en pantalla.
	 */
	public void draw(){
		if (character.isMoving()){
			update();
		}
		currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		batch.begin();
		float rotation = character.getLookDirection().angle() + 90f;
		batch.draw(currentFrame, character.getPosition().x, character.getPosition().y, 9f, 6.5f,18f,13f, 1f,1f, rotation);
		batch.end();
	}
	/**
	 * Actualiza la animacion segun el tiempo que paso desde el ultimo render.
	 */
	public void update(){
		 stateTime += Gdx.graphics.getDeltaTime();
	}
}
