package serialization;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.badlogic.gdx.math.Vector2;

/**
 * Clase usada por el JAXBContext para marshalling
 * y unmarshalling de los datos necesarios a un archivo XML
 * @author masaques
 * @see Position
 * @see com.mygdx.game.model.GameManager
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="level")
public class Level {
	@XmlElement(name="mapPath", required=true) 
	private String mapPath ;
	@XmlElementWrapper(name="goonPositions",required=true)
	@XmlElement(name="position",required=true)
	private List<Position> goonPositions = new ArrayList<Position>();
	@XmlElementWrapper(name="civilPositions",required=true)
	@XmlElement(name="position",required=true)
	private List<Position> civilPositions = new ArrayList<Position>() ;
	@XmlElement(name="playerPosition",required=true)
	private Position playerPosition ;
	
	public Level(String map,List<Position> goonPositions,List<Position> civilPositions ,Position playerPosition) {
		this.mapPath=map ;
		this.goonPositions=goonPositions;
		this.playerPosition=playerPosition ;
		this.civilPositions=civilPositions;
	}
	public Level() {
	}
	
	public String getPath() {
		return mapPath ;
	}
	
	public List<Vector2> goonPositions() {
		return Position.positionToVector(goonPositions) ;
	}
	
	public List<Vector2> civilPositions() {
		return Position.positionToVector(civilPositions);
	}
	
	public Vector2 getPlayer() {
		return new Vector2(playerPosition.getX(),playerPosition.getY()) ;
	}
	
}
