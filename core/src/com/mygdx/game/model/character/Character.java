/*
 *@author Tomas Raies
 *@date   13 de oct. de 2015
 */

package com.mygdx.game.model.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.LevelMap;
import com.mygdx.game.model.message.BulletListener;
import com.mygdx.game.model.util.Movable;
import com.mygdx.game.model.Model;
import serialization.CharacterInformation;
import serialization.Dumpeable;

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


public abstract class Character implements Movable, BulletListener,Dumpeable,Model {
	private static int IDS = 0;
	private int id ;
	private static final float DIRECTIONAL_EPSILON = .05f;
	private static final float NORMAL_SPEED = 60f;
	private static final float RUNNING_SPEED = 80f;
	private Vector2 moveDirection;
	private Vector2 lookDirection;
	private Vector2 seekForce;
	private boolean isRunning;
	private float healthPoints ;
	private boolean isDead ;
	private Rectangle hitBox;
	protected LevelMap map;
	protected boolean isMoving = false;
	private boolean isHurt = false;
	private Team team ;
	
	
	public Character(Rectangle hitBox, LevelMap map, Team team){
		this.moveDirection = new Vector2();
		this.lookDirection = new Vector2();
		this.seekForce 	   = new Vector2();
		this.map = map;
		this.hitBox = hitBox;
		this.isRunning = false;
		this.healthPoints = 100f ;
		this.isDead = false ;
		this.team = team ;
		this.id= IDS ;
		IDS++ ;
	}
	/**
	 * Constructor alternativo usado al cargar la informacion desde un archivo
	 * @param data La minima informacion requerida para cargar al character
	 * @param map El mapa generado por quien carga el juego
	 */
	public Character(CharacterInformation data,LevelMap map) {
		this.lookDirection= data.getDirection();
		this.moveDirection= data.getDirection();
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
	 * Devuelve si el personaje esta corriendo
	 */
	public boolean isRunning() {
		return isRunning;
	}
	/**
	 * Devuelve si el personaje esta herido.
	 */
	public boolean isHurt() {
		return isHurt;
	}
	/**
	 * Setea si el personaje esta herido.
	 */
	public void setHurt(boolean isHurt) {
		this.isHurt = isHurt;
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
	 * Devuelve la posicion del centro de su hitbox.
	 * @return
	 */
	public Vector2 getCenter() {
		return hitBox.getCenter(new Vector2());
	}
		
	/**
	 * Devuelve la direccion a la que se esta moviendo el personaje. En 
	 * una revision futura, conviene separar entre lookDirection y moveDirection.
	 */
	@Override
	public Vector2 getMoveDirection() {
		return new Vector2(moveDirection);
	}
	/**
	 * Devuelve la direccion a la que el character esta
	 * mirando.
	 * @return
	 */
	@Override
	public Vector2 getLookDirection() {
		return new Vector2(lookDirection);
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
		moveDirection.set(direction).nor();
		isMoving = true;
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
		this.isRunning = running;
		return true;
	}
	/**
	 * //TODO deberia hacer un lookWhereYouAreGoing
	 * 
	 * @param position
	 * @return
	 */
	public boolean look(Vector2 direction) {
		this.lookDirection.set(direction).nor();
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
	
	protected void moveAlong() {
		float speed;
		if (isRunning()){
			speed = RUNNING_SPEED;
		}
		else {
			speed = NORMAL_SPEED;
		}
		Vector2 position = hitBox.getCenter(new Vector2());
		Vector2 velocity = moveDirection.nor().scl(speed);
		hitBox.setCenter(position.add(velocity.scl(Gdx.graphics.getDeltaTime())));
		return;
	}
	/**
	 * Devuelve los puntos de vida.
	 * @return
	 */
	public float getHealthPoints() {
		return this.healthPoints ;
	}
	/**
	 * Setea los puntos de vida.
	 * @param dmg
	 */
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
		setHurt(true);
		this.setHealthPoints(dmg);
	}
	
	/**
	 * Devuelve si el jugador esta muerto.
	 * @return
	 */
	public boolean isDead() {
		return this.isDead ;
	}
	
	/**
	 * Devuelve el id.
	 * @return
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * hashCode.
	 */
	public int hashCode() {
		return  this.id ;
	}
	
	/**
	 * equals.
	 */
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
		return new CharacterInformation(this.getMoveDirection(),this.hitBox,this.getHealthPoints()) ;
	}
	
	/**
	 * Devuelve el mapa.
	 * @return
	 */
	public LevelMap getMap() {
		return map;
	}
	/**
	 * Devuelve el hitBox.
	 */
	public Rectangle getHitBox() {
		return this.hitBox;
	}
	
	/**
	 * Metodo a llamar
	 * 
	 */
	public abstract void die();
	/**
	 * Devuelve el equipo al que pertenece el character.
	 * @return
	 */
	public Team getTeam() {
		return team;
	}
	
}
