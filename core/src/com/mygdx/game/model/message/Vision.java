package com.mygdx.game.model.message;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.LevelMap;
import com.mygdx.game.model.character.Player;

public class Vision implements Message<VisionListener> {
	
	private Player source ;
	private LevelMap map ;
	
	public Vision(Player player, LevelMap map) {
		this.source= player ;
		this.map=map ;
	}
	
	@Override
	public void notify(VisionListener l) {
		Vector2 goonDirection = l.getMoveDirection() ;
		Vector2 goonCenter = l.getCenter() ;
		if (l.visualRange() > 0 && source.getCenter().dst2(goonCenter)> l.visualRange()){
			return ;
		}
		if (!map.isValid(goonCenter, source.getCenter())) {
			return ;
		}
		Vector2 relativeDirection = source.getCenter().sub(goonCenter).nor() ;
		float visualAngle = l.visualAngle();
		if (visualAngle == 0 || Math.abs(relativeDirection.angle(goonDirection))<=visualAngle/2){
			l.addPlayer(source.getCenter());
		}
	}

}