package serialization;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.mygdx.game.GameManager;


public final class GameSerializer {

	public static GameManager create(String path) throws IOException, ClassNotFoundException{
		ObjectInputStream file = new ObjectInputStream(
				 				 new BufferedInputStream(
				 				 new FileInputStream(path)));
		GameInformation game = (GameInformation)file.readObject();
		return new GameManager(game);
	}
	
	public static void save(GameManager manager, String path) throws IOException{
		GameInformation toFile = manager.dump();
		ObjectOutputStream file = new ObjectOutputStream(
				  new BufferedOutputStream(
				  new FileOutputStream(path)));
		file.writeObject(toFile);
		file.close();
	}
}
