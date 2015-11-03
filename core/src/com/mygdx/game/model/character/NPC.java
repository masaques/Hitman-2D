/*
 *@author Tomas Raies
 *@date   13 de oct. de 2015
 */

package com.mygdx.game.model.character;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.LevelMap;
import com.mygdx.game.model.character.behaviour.BulletManager;
import com.mygdx.game.model.message.Bullet;
import com.mygdx.game.model.message.Noise;
import com.mygdx.game.model.message.NoiseListener;
import com.mygdx.game.model.message.NoiseManager;
import com.mygdx.game.model.message.VisionListener;

import serialization.NPCInformation;

/*
 * Personajes no controlados por el jugador. Debem implementar comportamientos diferentes
 * segun el contexto del juego. Ademas, deben ser capaces de encontrar el camino entre dos 
 * puntos en el mapa.
 */
public abstract class NPC extends Character implements NoiseListener, Moody, VisionListener, Aggressive{
	private static final float VISUAL_RANGE = 9000f ;
	private static final float VISUAL_ANGLE = 100f ;
	
	protected static final float EPSILON = 2f;
	private Path currentPath;
	private Step finalStep;
	private Step currentStep = null;
	private PathFinder aStarPathFinder;
	private PathFinder linearPathFinder;
	private NPCStateMachine stateMachine;
	private List<Noise> noiseInbox;
	private List<Vector2> visualInbox;
	private NPCState currentState;
	
	public NPC (Rectangle hitBox, LevelMap map){
		super(hitBox, map);
		noiseInbox   = new ArrayList<Noise>();
		visualInbox  = new ArrayList<Vector2> ();
		stateMachine = new NPCStateMachine(this);
		currentState = NPCState.CALM;
	}
	/**
	 * Constructor alternativo usado al cargar la informacion desde un archivo
	 * @param data La minima informacion requerida para cargar al NPC
	 * @param map El mapa generado por quien carga el juego
	 * @see Character
	 */
	public NPC (NPCInformation data,LevelMap map) {
		super(data,map) ;
		this.noiseInbox = new ArrayList<Noise>() ;
		this.noiseInbox.addAll(data.getNoiseList());
		this.visualInbox = new ArrayList<Vector2>() ;
		this.visualInbox.addAll(data.getVisionList());
		stateMachine = new NPCStateMachine(this);
		currentState = NPCState.CALM;
	}
	
	public void setAStarPathFinder(PathFinder pathFinder){
		this.aStarPathFinder = pathFinder;
	}
	public void setLinearPathFinder(PathFinder pathFinder) {
		this.linearPathFinder = pathFinder;
	}
	
	/**
	 * Setea el camino para llegar a un punto en el mapa, si es posible.
	 * @param position
	 */
	public boolean moveTo(Vector2 position, boolean linear) {
		if (finalStep != null && position.epsilonEquals(finalStep.getPosition(), EPSILON )){
			return false;
		}
		Vector2 currPosition = new Vector2();
		currPosition = getHitBox().getCenter(currPosition);
		PathFinder pathFinder;
		if (linear) {
			pathFinder = linearPathFinder;
		}
		else {
			pathFinder = aStarPathFinder;
		}
		Path auxPath = pathFinder.findPath(this, currPosition, position);
		if (auxPath != null && auxPath.hasNextStep()){
			currentPath = auxPath;
			currentStep = currentPath.nextStep();
			move(currentStep.getPosition().sub(getPosition()));
			look(currentStep.getPosition());
			isMoving = true;
			return true;
		}
		return false;
	}
	
	/**
	 * Actualiza el estado del NPC
	 */
	@Override
	public void update() {
		Context context = new Context(
				noiseInbox,
				visualInbox, 
				isMoving);
		
		stateMachine.updateMachine(context);
		updatePosition();
		super.update();	
	}
	
	private void updatePosition() {
		if (!isMoving || currentPath == null){
			isMoving = false;
			currentPath = null;
			currentStep = null;
			return;
		}
	
		if (currentStep == null || currentStep.getPosition().epsilonEquals(getPosition(), EPSILON)){
			if (currentPath.hasNextStep()){
				currentStep = currentPath.nextStep();
			}
			else {
				currentPath = null;
				isMoving = false;
			}
		}
		if (isMoving){
			move(currentStep.getPosition().sub(getPosition()));
			look(currentStep.getPosition());
		}
	}
	
	
	/**
	 * Metodo para calcular si el jugador esta en el campo visual de este NPC
	 * Utilizado por VisionHandler
	 * @param playerPosition
	 * @return true si el jugador es visible, false si no
	 */
	@Deprecated
	public boolean canSee(Vector2 playerPosition) {
		Vector2 goonPosition  = getCenter() ;
		Vector2 goonDirection = getLookDirection() ;
		if (playerPosition.dst2(goonPosition)> VISUAL_RANGE){
			return false ;
		}
		if (!map.isValid(goonPosition, playerPosition)) {
			return false ;
		}
		Vector2 relativeDirection = playerPosition.sub(goonPosition).nor() ;
		if (Math.abs(relativeDirection.angle(goonDirection))<=VISUAL_ANGLE/2) {
			return true ;
		}
		return false ;
	}
	
	public void addPlayer(Vector2 playerPosition) {
		visualInbox.add(playerPosition);
	}
	
	public void addNoise(Noise n){
		noiseInbox.add(n);
	}
	
	/**
	 * los siguientes metodos abstractos van a definir el comportamiento de los npc segun
	 * su estado "animico" (NPC es Moody).
	 */
	@Override
	public void stop() {
		isMoving = false;
	
	}
	
	public float visualRange() {
		float visualRange;
		if (currentState == NPCState.ALARM){
			visualRange =  -1f;
		}
		else {
			visualRange = VISUAL_RANGE;
		}
		return visualRange ;
	}
	
	public float visualAngle() {
		float visualAngle;
		if (currentState == NPCState.ALARM) {
			visualAngle = 0f;
		}
		else {
			visualAngle = VISUAL_ANGLE;
		}
		return visualAngle ;
	}
	
	@Override
	public void shoot(Vector2 to) {
		Vector2 relative = to.sub(this.getPosition()).nor();
		BulletManager.getInstance().dispatchMessage(new Bullet(this,this.getPosition(),relative,map));
		NoiseManager.getInstance().dispatchMessage(new Noise(this.getPosition(),100,true));
	}
	
	@Override
	public NPCInformation dump() {
		if (this.isDead()) {
			return null ;
		}
		return new NPCInformation(this.getMoveDirection(),getHitBox(),this.getHealthPoints(),
				this.noiseInbox,this.visualInbox) ;
	}
	@Override
	public abstract void alarm(Context context);
	@Override
	public abstract void suspicious(Context context);
	@Override
	public abstract void calm(Context context);
	
	@Override
	public void surprised(Context context) {
		if (context.playerIsVisible()) {
			moveTo(context.getPlayerPosition(), true);
			stop();
		}
	}
	public void refreshNoiseInbox() {
		noiseInbox.clear();
	}
	public void refreshVisualInbox() {
		visualInbox.clear();
	}
	/**
	 * Devuelve el estado actual del npc (util para el characterView)
	 * @return
	 */
	@Override
	public NPCState getState() {
		return currentState;
	}
	/**
	 * TODO ver si esto se puede omitir
	 * Este setter del estado sirve para ahorrarme pedirle al stateMachine el estado actual.
	 * La state machine no conoce el tipo NPCState, y por lo tanto no podria pedirle el estado actual
	 * manteniendo el tipo que necesito para saber diferenciar los estados (alarm, susp, calm) en el 
	 * characterView.
	 * @param state
	 */
	@Override
	public void setState(NPCState state) {
		currentState = state;
	}
	
	@Override
	protected void moveAlong() {
		Rectangle boundingHitBox = new Rectangle(getHitBox());
		boundingHitBox.setWidth(boundingHitBox.width * 1.1f);
		boundingHitBox.setHeight(boundingHitBox.height * 1.1f);
		float maxforce = 2f;
		Vector2 position = boundingHitBox.getCenter(new Vector2());
		Vector2 velocity = new Vector2(getMoveDirection());
		Vector2 ahead  = new Vector2(position)
				.add(new Vector2(velocity).nor().scl(5f));
		Vector2 ahead2 = new Vector2(position).add(new Vector2(velocity).nor().scl(2.5f));
		Rectangle obstacle = map.avoidanceDetection(boundingHitBox, ahead, ahead2);
		Vector2 avoidanceForce = new Vector2();
		if (obstacle != null) {
			avoidanceForce = new Vector2(ahead).sub(obstacle.getCenter(new Vector2())).nor().scl(maxforce);
		}
		Vector2 steering = new Vector2();
		steering.add(avoidanceForce);
		move(velocity.add(steering));
		super.moveAlong();
		return;
	}
}
