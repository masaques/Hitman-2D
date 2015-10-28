/*
 *@author Tomas Raies
 *@date   13 de oct. de 2015
 */

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import serialization.CharacterInformation;

/**
 * <p>Clase abstracta para todos los personajes del juego, incluyendo el jugador 
 * y los NPC. Implementa {@link Movable} y {@linkplain BulletListener}</p>.
 * 
 * @see Movable
 * @see BulletListener
 * @see Player
 * @see NPC
 * 
 */


public abstract class Character implements Movable, BulletListener {
	private static int IDS = 0;
	private int id ;
	private static final float DIRECTIONAL_EPSILON = 0.05f;
	private static final float NORMAL_SPEED = 60f;
	private static final float RUNNING_SPEED = 100f;
	private Vector2 direction;
	private boolean running;
	private float healthPoints ;
	private boolean isDead ;
	protected Rectangle hitBox;
	protected LevelMap map;
	protected boolean isMoving = false;
	
	
	
	public Character(Rectangle hitBox, LevelMap map){
		this.direction = new Vector2();
		this.map = map;
		this.hitBox = hitBox;
		this.running = false;
		this.healthPoints = 100f ;
		this.isDead = false ;
		this.id= IDS ;
		IDS++ ;
	}
	/**
	 * Constructor alternativo usado al cargar la informacion desde un archivo
	 * @param data La minima informacion requerida para cargar al character
	 * @param map El mapa generado por quien carga el juego
	 */
	public Character(CharacterInformation data,LevelMap map) {
		this.direction= data.getDirection();
		this.hitBox= data.getHitbox();
		this.healthPoints=data.getHealthPoints();
		this.map=map;
		this.id= IDS;
		IDS++;
	}
	
	/**
	 *  Devulelve si el personaje se esta moviendo.
	 */
	public boolean isMoving(){
		return isMoving;
	}
	/**
	 * Devuelve la posicion del personaje como la posicion de su hit box.
	 * 
	 * @see #getPosition() com.badlogic.gdx.math.Rectangle.getPosition(Vector2 position)
	 */
	@Override
	public Vector2 getPosition() {
		return hitBox.getPosition(new Vector2());
	}
	
	/**
	 * Devuelve la direccion a la que se esta moviendo el personaje. En 
	 * una revision futura, conviene separar entre lookDirection y moveDirection.
	 */
	@Override
	public Vector2 getDirection() {
		return new Vector2(direction);
	}
	
	/**
	 * Setea direction. En una revision futura deberia setear moveDirection.
	 * Running no se modifica.
	 * 
	 * @param direction - Dirección de movimiento
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
	/**
	 * Idem anterior, pero modifica running por un nuevo valor.
	 * 
	 * @param direction - Direccion de movimiento
	 * @param running - Boleano si esta corriendo
	 */
	public boolean move(Vector2 direction, boolean running) {
		move(direction);
		this.running = running;
		return true;
	}
	
	/**
	 * Devuelve el ancho del hit box.
	 * 
	 * @see com.badlogic.gdx.math.Rectangle
	 */
	@Override
	public float getWidth() {
		return hitBox.getWidth();
	}
	
	/**
	 * Devuleve el alto del hit box.
	 * 
	 * @see com.badlogic.gdx.math.Rectangle
	 */
	@Override
	public float getHeight() {
		return hitBox.getHeight();
	}
	
	/**
	 * Update del personaje. 
	 */
	@Override
	public void update(){
		if (isMoving) {
			moveAlong();
		}
		return;
		
	}
	
	/**
	 * Metodo privado que calcula la proxima posicion del {@link Character} segun su 
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
	
	/**
	 * Metodo privado que calcula un Rectangle segun una direccion determinada, una 
	 * posicion inicial y una velocidad. Usado por el metodo moveAlong.
	 * 
	 * @param direction - Direción del rectangulo
	 * @param speed - velocidad del objeto
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
	 * Metodo para infligir daño en los {@link Characters}
	 * 
	 * @param dmg - Daño a infligir
	 * 
	 * @return true si, como resultado del daño, el Character muere. false si no
	 */
	public void dealDamage(float dmg) {
		if (dmg >= this.getHealthPoints()) {
			this.isDead = true ;
		}
		this.setHealthPoints(dmg);
	}
	
	public boolean isDead() {
		return this.isDead ;
	}
	
	public int getId() {
		return this.id;
	}
	public int hashCode() {
		return  this.id ;
	}
	
	public boolean equals(Object o) {
		if (o == null) {
			return false ;
		}
		else if (o == this) {
			return true ;
		}
		else if (!(o instanceof Character)) {
			return false ;
		}
		else {
			Character aux = (Character)o ;
			if (aux.getId()!=this.getId()) {
				return false ;
			}
		}
		return true ;
	}
	/**
	 * Este metodo se usa para extraer de este Character 
	 * la minima informacion necesaria para reinicializarlo
	 * @return Una nueva instancia de CharacterInformation, usada para recargar la partida
	 *  ,null si el character esta muerto
	 */
	public CharacterInformation dump() {
		if (this.isDead()) {
			return null;
		}
		return new CharacterInformation(this.getDirection(),this.hitBox,this.getHealthPoints()) ;
	}
}
