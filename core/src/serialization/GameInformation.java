package serialization;

import java.io.Serializable;
import java.util.Set;

public class GameInformation implements Serializable {
	private static final long serialVersionUID = 7557907401528727695L;
	private int width;
	private int height;
	private String mapPath;
	private Set<NPCInformation> goonData ;
	private CharacterInformation playerData ;
	
	public GameInformation(int width, int height, String mapPath,
			Set<NPCInformation> goons,CharacterInformation player) {
		this.width=width;
		this.height= height;
		this.mapPath= mapPath;
		this.goonData = goons ;
		this.playerData= player;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getMapPath() {
		return mapPath;
	}

	public Set<NPCInformation> getGoonData() {
		return goonData;
	}

	public CharacterInformation getPlayerData() {
		return playerData;
	}
}
