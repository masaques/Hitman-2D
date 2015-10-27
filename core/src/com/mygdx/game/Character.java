/*
 *@author Tomas Raies
 *@date   13 de oct. de 2015
 */

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/*
 * Clase abstracta para todos los personajes del juego, incluyendo el jugador 
 * y los npc. Implementa Movable.
 */
public abstract class Character implements Movable {
	protected static final float DIRECTIONAL_EPSILON = 0.05f;
	protected static final float NORMAL_SPEED = 60f;
	protected static final float RUNNING_SPEED = 100f;
	protected Vector2 direction;
	protected Rectangle hitBox;
	protected CharacterView model;
	protected LevelMap map;
	protected boolean running;
	protected boolean isMoving = false;
	protected float healthPoints ;
	
	public Character(Rectangle hitBox, LevelMap map){
		this.direction = new Vector2();
		this.map = map;
		this.hitBox = hitBox;
		this.running = false;
		this.healthPoints = 100f ;
	}
	/*
	 * Devulelve si el personaje se esta moviendo.
	 */
	public boolean isMoving(){
		return isMoving;
	}
	/*
	 * Devuelve la posicion del personaje como la posicion de su hit box.
	 * Notese como funciona el metodo getPosition() de rectangle para
	 * que devuelva un Vector2.
	 */
	@Override
	public Vector2 getPosition() {
		return hitBox.getPosition(new Vector2());
	}
	
	/*
	 * Devuelve la direccion a la que se esta moviendo el personaje. En 
	 * una revision futura, conviene separar entre lookDirection y moveDirection.
	 */
	@Override
	public Vector2 getDirection() {
		return new Vector2(direction);
	}
	
	/*
	 * Setea direction. En una revision futura deberia setear moveDirection.
	 * Running no se modifica.
	 * @param direction
	 */
	@Override
	public boolean move(Vector2 direction) {
		if (direction.isZero()){
			return false;
		}
		this.direction.set(direction.nor());
		this.isMoving = true;
		return true;
	}
	/*
	 * Idem anterior, pero modifica running por un nuevo valor.
	 * @param direction
	 * @param running
	 */
	public boolean move(Vector2 direction, boolean running) {
		move(direction);
		this.running = running;
		return true;
	}
	
	/*
	 * Devuelve el ancho del hit box.
	 */
	@Override
	public float getWidth() {
		return hitBox.getWidth();
	}
	
	/*
	 * Devuleve el alto del hit box.
	 */
	@Override
	public float getHeight() {
		return hitBox.getHeight();
	}
	
	/*
	 * Update del personaje. Por ahora solo llama al metodo moveAlong.
	 * En un futuro deberia llamar a todos los send de los mensajes que el 
	 * personaje quiera mandar (bullets, noise, dialog, etc..)
	 */
	@Override
	public void update(){
		if (isMoving) {
			moveAlong();
		}
		return;
		
	}
	
	/*
	 * Metodo privado que calcula la proxima posicion del Character segun su 
	 * direccion y su rapidez. Deberia ser llamado por el update si el
	 * boolean isMoving es true. Prueba tres direcciones posibles: derecho, 
	 * y a lo largo del eje x e y si la primera es imposible. 
	 */
	
	private void moveAlong(){
		float speed;
		if (running){
			speed = RUNNING_SPEED;
		}
		else {
			speed = NORMAL_SPEED;
		}
		Rectangle currHitBox = getDirectionalHitBox(direction, speed);
		
		if (!map.isValid(currHitBox)) {
			/*
			 * Este if sirve para que los personajes no se queden trabados si la coordenada
			 * x del movimiento es muy chica y hay un obstaculo que no les permite continuar,
			 * sobre todo en el caso de las esquinas. Mas abajo hay un if analogo para la
			 * coorenada y.
			 */
			if (direction.x!= 0 && Math.abs(direction.x) <  DIRECTIONAL_EPSILON) {
				direction = new Vector2(1f * Math.signum(direction.y), direction.y).nor();
			}
			currHitBox = getDirectionalHitBox(new Vector2(direction.x,0).nor(), speed);
			if (!map.isValid(currHitBox) || direction.x == 0f){
				if (direction.y!= 0 && Math.abs(direction.y) < DIRECTIONAL_EPSILON) {
					direction = new Vector2(direction.x, 1f * Math.signum(direction.y)).nor();
				}
				currHitBox = getDirectionalHitBox(new Vector2(0,direction.y).nor(), speed);
				if (!map.isValid(currHitBox) || direction.y == 0f){
				    isMoving = false;
					return;
				}
			}
		}
		hitBox.set(currHitBox);
		return;
	}
	
	/*
	 * Metodo privado que calcula un Rectangle segun una direccion determinada, una 
	 * posicion inicial y una velocidad. Usado por el metodo moveAlong.
	 * @param direction
	 * @param speed
	 */
	
	private Rectangle getDirectionalHitBox(Vector2 direction, float speed) {
		Vector2 position = this.hitBox.getPosition(new Vector2());
		Vector2 velocity = new Vector2(direction).scl(speed);
		Vector2 movement = velocity.scl(Gdx.graphics.getDeltaTime());
		position.add(movement);
		Rectangle currHitBox = new Rectangle(hitBox);
		currHitBox.setPosition(position); 
		return currHitBox;
	}
	
	public float getHealthPoints() {
		return this.healthPoints ;
	}
	private void setHealthPoints(float dmg){
		if (dmg >= this.healthPoints){
			this.healthPoints = 0 ;
		}
		this.healthPoints -= dmg ;
	}
	/**
	 * Metodo para infligir da�o en los 
	 * Characters
	 * @param dmg
	 * @return true si, como resultado del da�o, el Character muere. false si no
	 */
	public boolean dealDamage(float dmg) {
		if (dmg >= this.getHealthPoints()) {
			return true ;
		}
		this.setHealthPoints(dmg);
		return false ;
	}
	public LevelMap getMap() {
		return map;
	}
}
