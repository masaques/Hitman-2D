package serialization;

import java.io.Serializable;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Noise;
import com.mygdx.game.Path;
import com.mygdx.game.Step;

/**
 * Clase que agrega valores a CharacterInformation,
 * necesaria para los NPC
 * @author masaques
 * @see NPC
 */
public class NPCInformation extends CharacterInformation implements Serializable, Information {

	private Path currentPath;
	private Step finalStep;
	private Step currentStep ;
	private List<Noise> noiseList ;
	private List<Vector2> visionList ;
	
	private static final long serialVersionUID = 7135254762958640372L;
	
	
	public NPCInformation(Vector2 direction, Rectangle hitBox, float hp, 
						  List<Noise> noiseList, List<Vector2> visionList) {
		super(direction, hitBox, hp);
		this.noiseList= noiseList;
		this.visionList = visionList;
	}
	public Path getCurrentPath() {
		return currentPath;
	}
	public Step getFinalStep() {
		return finalStep;
	}
	public Step getCurrentStep() {
		return currentStep;
	}
	public List<Noise> getNoiseList() {
		return noiseList;
	}
	public List<Vector2> getVisionList() {
		return visionList;
	}

}
