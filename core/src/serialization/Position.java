package serialization;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.badlogic.gdx.math.Vector2;

/**
 * Clase que modela a un elemento del archivo XML que
 * representa al nivel
 * @author masaques
 * @see Level
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="position")
public class Position {
	private float x ;
	private float y ;
	public Position(Vector2 pos) {
		this.x=pos.x;
		this.y=pos.y;
	}
	public Vector2 getVector2() {
		return new Vector2(x,y) ;
	}
	/**
	 * Metodo de clase para convertir listas de Vector2 a listas de Position,
	 * necesaria para el uso del entorno JAXB
	 * @param vectors : Los vectores en Vector2
	 * @return La lista convertida a Position
	 */
	public static List<Position> vectorToPosition(List<Vector2> vectors) {
		List<Position> ans = new ArrayList<Position>() ;
		for (Vector2 v : vectors) {
			ans.add(new Position(v)) ;
		}
		return ans ;
	}
}
