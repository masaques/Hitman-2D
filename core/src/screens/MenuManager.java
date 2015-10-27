package screens;

public class MenuManager {

	/*
	 * Esta clase es la que se encarga de controlar lo que el usuario
	 * elegirá cuando inicie el Juego.
	 * Contará con 4 opciones.
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
	private MenuChoose choose = MenuChoose.Main;
	
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
	
	
	
	
	
}
