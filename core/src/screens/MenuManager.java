package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

public class MenuManager {

	/*
	 * Esta clase es la que se encarga de controlar lo que el usuario
	 * elegirá cuando inicie el Juego.
	 * Contará con 5 opciones.
	 * -1- NEW -> Inicio de una partida nueva
	 * -2- RESUME -> Abre una partida almacenada
	 * -3- AYUDA -> Muestra como se juega. Esto es meramente informativo
	 * -4- RESOLUTION -> Puede cambiar la resolucion del juego.
	 * -5- SCORE -> Muestra una tabla con los puntajes obtenidos
	 * 
	 * A su vez, estará la clase MenuManagerView que es la que se encarga de 
	 * mostrarle al usuario todas las opciones
	 */
	
	private static MenuManager self;
	private String playerName = "";
	private final int MAXPLAYERNAME = 10;
	private boolean goFullscreen = false;
	private MenuChoose choose = MenuChoose.Main; //te dice donde estas parado o a donde tenes que ir, en su defecto
	
	private static final int OP1 = 1;
	private static final int OP2 = 2;
	private static final int OP3 = 3;
	private static final int OP4 = 4;

	
	public MenuManager(){
		
	}
	
	public static MenuManager getInstance(){
		if (self==null){
			self=new MenuManager();
		}
		return self;
	}
	
	public void reset(){
		choose = MenuChoose.Main;
		playerName = "";
		goFullscreen = false;
	}
	
	public void goBack(){
		switch(choose){
		case Main:
			Gdx.app.exit(); break;
		case New:
		case Help:
		case ChangeResolution:
			choose = MenuChoose.Main; break;
		default:
			break;
		}
	}
	
	public void keyDown(int keycode){
		switch(keycode){
		case Keys.ESCAPE:
			goBack();
			break;
		case Keys.NUM_1:
		case Keys.NUMPAD_1:
			//Tendria que iniciar un juego nuevo
			break;
		case Keys.NUM_2:
		case Keys.NUMPAD_2:
			//ir a ayuda
			break;
		case Keys.NUM_3:
		case Keys.NUMPAD_3:
			//Cambiar la resolucion
			break;
		case Keys.NUM_4:
		case Keys.NUMPAD_4:
			//Ver la tabla de puntajes
			break;
		default:
			break;
		}
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	
	
	
	
}
