/*
 *@author Tomas Raies
 *@date   13 de oct. de 2015
 */

package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Context;
import com.mygdx.game.LevelMap;
import com.mygdx.game.Path;
import com.mygdx.game.PathFinder;
import com.mygdx.game.Step;

/*
 * Personajes no controlados por el jugador. Debem implementar comportamientos diferentes
 * segun el contexto del juego. Ademas, deben ser capaces de encontrar el camino entre dos 
 * puntos en el mapa.
 */
public abstract class NPC extends Character implements NoiseListener, Moody{
	private static final float VISUAL_RANGE = 5000f ;
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
	
	public NPC (Rectangle hitBox, LevelMap map){
		super(hitBox, map);
		noiseInbox = new ArrayList<Noise>();
		visualInbox = new ArrayList<Vector2> ();
		stateMachine = new NPCStateMachine(this);
	}
	public void setStateMachine(NPCStateMachine stateMachine){
		this.stateMachine = stateMachine;
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
		currPosition = hitBox.getPosition(currPosition);
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
			/*
			 * Ineficiente? Probablemente, pero me soluciona la vida por ahora. (Tiene que hacer una raiz cuadrada por frame) 
			 */
			move(currentStep.getPosition().sub(getPosition()));
		}
	}
	
	
	/**
	 * Metodo para calcular si el jugador esta en el campo visual de este NPC
	 * Utilizado por VisionHandler
	 * @param playerPosition
	 * @return true si el jugador es visible, false si no
	 */
	
	public boolean canSee(Vector2 playerPosition) {
		Vector2 goonPosition = new Vector2((float)this.hitBox.x,(float)this.hitBox.y) ;
		Vector2 goonDirection = this.direction ;
		if (playerPosition.dst2(goonPosition)> VISUAL_RANGE){
			return false ;
		}
		if (!map.isValid(goonPosition, playerPosition)) {
			return false ;
		}
		float dirAngle = goonDirection.angle() ;
		Vector2 relativeDirection = playerPosition.sub(goonPosition).nor() ;
		float z = relativeDirection.angle();
		if (dirAngle > 180f){
			dirAngle = -180f + (dirAngle-180f) ;
		}
		if (z > 180f){
			z = -180f + (z-180f) ;
		}
		if ( z>=dirAngle-VISUAL_ANGLE/2 && z <= dirAngle+VISUAL_ANGLE/2) {
			return true ;
		}
		return false ;
	}
	
	public void addPlayertoContext(Vector2 playerPosition) {
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
	@Override
	public abstract void alarm(Context context);
	@Override
	public abstract void suspicious(Context context);
	@Override
	public abstract void calm(Context context);
	
	public void refreshNoiseInbox() {
		noiseInbox.clear();
	}
	public void refreshVisualInbox() {
		visualInbox.clear();
	}
}
