package serialization;

import java.io.Serializable;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class CharacterInformation implements Serializable {
	private static final long serialVersionUID = 4224959980047912353L;
	private Vector2 direction;
	private Rectangle hitBox;
	private float healthPoints;
	
	public CharacterInformation(Vector2 direction, Rectangle hitBox,float hp) {
		this.direction= direction ;
		this.hitBox = hitBox ;
		this.healthPoints= hp ;
	}
	
	public Vector2 getDirection() {
		return direction;
	}
	public Rectangle getHitbox() {
		return hitBox;
	}
	public float getHealthPoints() {
		return healthPoints ;
	}
}
