package com.mygdx.game;

public class NPCStateMachine implements StateMachine<Moody> {
	
	private State<Moody> currentState;
	private Moody owner;
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
		currentState.updateState(this, context);
	}
	/**
	 * Cambia el estado de la maquina.
	 * @param state
	 */
	@Override
	public void changeState(State<Moody> state){
		currentState = state;
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
}
