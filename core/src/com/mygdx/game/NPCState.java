package com.mygdx.game;
import com.badlogic.gdx.Gdx;

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
				stateMachine.changeState(SUSPICIOUS);
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
		private float suspiciousTimer = 0f;
		@Override
		public void updateState(StateMachine<Moody> stateMachine, Context context) {
			suspiciousTimer += Gdx.graphics.getDeltaTime();
			if (context.playerIsVisible()) {
				stateMachine.changeState(ALARM);
				suspiciousTimer = 0f;
			}
			else if (!context.canHear() && suspiciousTimer > 10000 && !context.isMoving()) {
				stateMachine.changeState(CALM);
				suspiciousTimer = 0f;
			}
			else {
				if (context.canHear()) {
					suspiciousTimer = 0f;
				}
				stateMachine.getOwner().suspicious(context);
			}
		}
	},
	
	CALM() {
		@Override
		public void updateState(StateMachine<Moody> stateMachine, Context context) {
			System.out.println("algo");
			if (context.playerIsVisible()) {
				stateMachine.changeState(ALARM_SURPRISE);
				stateMachine.getOwner().stop();
			}
			else if (context.canHear()) {
				stateMachine.changeState(SUSPICIOUS_SURPRISE);
				stateMachine.getOwner().stop();
			}
			else {
				stateMachine.getOwner().calm(context);
			}
		}
	},
	
	ALARM_SURPRISE() {
		private static final float SURPRISE_WAIT = 2;
		private float surpriseTimer = 0f;
		@Override
		public void updateState(StateMachine<Moody> stateMachine, Context context) {
			surpriseTimer += Gdx.graphics.getDeltaTime();
			if (surpriseTimer > SURPRISE_WAIT) {
				stateMachine.changeState(ALARM);
				surpriseTimer = 0f;
			}
		}
	},
	SUSPICIOUS_SURPRISE() {
		private static final float SURPRISE_WAIT = 3000;
		private float surpriseTimer = 0f;
		@Override
		public void updateState(StateMachine<Moody> stateMachine, Context context) {
			surpriseTimer += Gdx.graphics.getDeltaTime();
			if (surpriseTimer > SURPRISE_WAIT) {
				stateMachine.changeState(SUSPICIOUS);
				surpriseTimer = 0f;
			}
		}
	}
}
