package com.mygdx.game.model.character;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.model.util.State;
import com.mygdx.game.model.util.StateMachine;

public class NPCStateMachine implements StateMachine<Moody> {
	
	private State<Moody> currentState;
	private Moody owner;
	private float stateTimer = 0f;
	/**
	 * El constructor recibe como parametro el duenp de la maquina de estados.
	 * Setea como parametro inicial CALM.
	 * @param owner
	 */
	public NPCStateMachine(Moody owner) {
		this.owner = owner;
		this.currentState = NPCState.CALM;
	}
	/**
	 * Recibe un contexto y se lo pasa al estado actual.
	 * @param context
	 */
	@Override
	public void updateMachine(Context context) {
		stateTimer += Gdx.graphics.getDeltaTime();
		currentState.updateState(this, context);
	}
	/**
	 * Cambia el estado de la maquina.
	 * @param state
	 */
	@Override
	public void changeState(State<Moody> state){
		currentState = state;
		stateTimer = 0f;
	}
	/**
	 * Devuelve el duenio de la maquina. Lo van a pedir los estados 
	 * para llamar a los metodos de Moody(calm suspicious y alarm)
	 * @return
	 */
	@Override
	public Moody getOwner() {
		return owner;
	}
	/**
	 * Devuelve el tiempo transcurrido desde el ultimo cambio de estado.
	 * @return
	 */
	@Override
	public float getStateTimer() {
		return stateTimer;
	}
}
