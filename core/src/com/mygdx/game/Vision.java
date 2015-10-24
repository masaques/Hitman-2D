package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Vision implements Message<VisionListener> {
	
	private Player source ;
	private LevelMap map ;
	@Override
	public void notify(VisionListener l) {
		Vector2 goonDirection = l.getDirection() ;
		Vector2 goonPosition = l.getPosition() ;
		if (source.getPosition().dst2(goonPosition)> l.visualRange()){
			return ;
		}
		if (!map.isValid(goonPosition, source.getPosition())) {
			return ;
		}
		Vector2 relativeDirection = source.getPosition().sub(goonPosition).nor() ;
		if (Math.abs(relativeDirection.angle(goonDirection))<=l.visualAngle()/2){
			l.addPlayer(l.getPosition());
		}
	}

}
