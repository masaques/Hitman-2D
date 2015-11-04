package com.mygdx.game.model.character;

import com.mygdx.game.model.util.State;
import com.mygdx.game.model.util.StateMachine;

/**
 * La idea de este enum es que represente los distintos estados de la maquina de estados,
 * cada uno sabe a que estado irse segun el contexto, o llamar al metodo de la interfaz
 * Moody que le corresponde, para que el NPC se comporte segun su implementacion.
 * @author traies
 *
 */
public enum NPCState implements State<Moody> {
	
	/**
	 * El estado alarm toma el control cuando el jugador esta a la vista del NPC, o cuando
	 * todavia puede moverse a la ultima posicion que ocupo. En caso contrario, el NPC pasa a
	 * SUSPICIOUS
	 */
	ALARM() {
		@Override
		public void updateState(StateMachine<Moody> stateMachine, Context context) {
			if (context.playerIsVisible() || context.isMoving()){
				stateMachine.getOwner().alarm(context);
				
			}
			else {
				stateMachine.changeState(SUSPICIOUS_SURPRISE);
				stateMachine.getOwner().setState(SUSPICIOUS);
			}
		}
	},
	
	/**
	 * El estado SUSPICIOUS toma el control cuando el npc escucha un sonido o cuando, despues de 
	 * haber visto al jugador, continua investigando el area. Pasa a ALARM si ve al jugador o 
	 * a calm si se acaba el tiempo.
	 * 
	 */
	SUSPICIOUS() {
		private static final float SUSPICIOUS_TIME = 10;
		@Override
		public void updateState(StateMachine<Moody> stateMachine, Context context) {
			if (context.playerIsVisible()) {
				stateMachine.changeState(ALARM);
				stateMachine.getOwner().setState(ALARM);
			}
			else if (!context.canHear() && stateMachine.getStateTimer() > SUSPICIOUS_TIME && !context.isMoving()) {
				stateMachine.changeState(CALM);
				stateMachine.getOwner().setState(CALM);
			}
			else {
				if (context.canHear()) {
					stateMachine.changeState(SUSPICIOUS);
				}
				stateMachine.getOwner().suspicious(context);
			}
		}
	},
	
	CALM() {
		@Override
		public void updateState(StateMachine<Moody> stateMachine, Context context) {
			if (context.playerIsVisible()) {
				stateMachine.changeState(ALARM_SURPRISE);
				stateMachine.getOwner().stop();
				stateMachine.getOwner().setState(ALARM);
			}
			else if (context.canHear()) {
				stateMachine.changeState(SUSPICIOUS_SURPRISE);
				stateMachine.getOwner().stop();
				stateMachine.getOwner().setState(SUSPICIOUS);
			}
			else {
				stateMachine.getOwner().calm(context);
			}
		}
	},
	
	ALARM_SURPRISE() {
		private static final float SURPRISE_WAIT = 1f;
		@Override
		public void updateState(StateMachine<Moody> stateMachine, Context context) {
			stateMachine.getOwner().surprised(context);
			if (stateMachine.getStateTimer() > SURPRISE_WAIT) {
				stateMachine.changeState(ALARM);
			}
		}
	},
	SUSPICIOUS_SURPRISE() {
		private static final float SURPRISE_WAIT = 1;
		@Override
		public void updateState(StateMachine<Moody> stateMachine, Context context) {
			stateMachine.getOwner().surprised(context);
			if (context.playerIsVisible()) {
				stateMachine.changeState(ALARM_SURPRISE);
				stateMachine.getOwner().setState(ALARM);
			}
			else if (stateMachine.getStateTimer() > SURPRISE_WAIT) {
				stateMachine.changeState(SUSPICIOUS);
			}
		}
	}
}
