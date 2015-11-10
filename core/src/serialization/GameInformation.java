package serialization;

import java.io.Serializable;
import java.util.List;

/**
 * Clase que contiene toda la informacion necesaria para reinicializar el juego
 * 
 * @author masaques
 *
 */
public class GameInformation implements Serializable, Information {
	private static final long serialVersionUID = -3667205157514021744L;
	private String map;
	private List<NPCInformation> goonInfo;
	private CharacterInformation playerInfo;

	public GameInformation(List<NPCInformation> goonInfo, CharacterInformation playerInfo, String map) {
		this.map = map;
		this.goonInfo = goonInfo;
		this.playerInfo = playerInfo;
	}

	public String getMap() {
		return map;
	}

	public List<NPCInformation> getGoonInfo() {
		return goonInfo;
	}

	public CharacterInformation getPlayerInfo() {
		return playerInfo;
	}

}
