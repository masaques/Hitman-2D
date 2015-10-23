package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;


public class FollowStrategy implements Strategy {
	private static final float EPSILON = 5f;
	private Vector2 lastPosition;
	private boolean done;
	private NPC npc;
	
	/*
	 * Deberia guardar una referencia a npc? esto significa que cada npc
	 * tiene su propia instancia de FollowStrategy. La alternatica seria hacer que
	 * exista una referencia a npc en context. Otra es que el metodo behave devuelva
	 * un mensaje para npc que le diga que tiene que hacer (con commands).
	 */
	public FollowStrategy() {
		this.done = false;
	}
	/*
	 * el metodo behave decide que debe hacer el npc. Si el npc no ve al 
	 * jugador, va a tratar de ir en linea recta a la ultima posicion en la que estuvo.
	 */
	@Override
	public ActionRequest behave(Context context) {
		ActionRequest actionRequest = new ActionRequest();
		if (context.playerIsVisible()) {
			
			actionRequest.setRequest(ActionRequest.REQUEST_MOVETO);
			actionRequest.setPosition(context.getPlayerPosition());
			actionRequest.setRunning(true);
			lastPosition = context.getPlayerPosition();
			done = false;
		}
		else if (lastPosition == null || lastPosition.epsilonEquals(context.getNpcPosition(), EPSILON)) {
			done = true;
		}
		return actionRequest;
	}

	@Override
	public boolean done() {
		return done;
	}

}
