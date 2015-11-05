package com.mygdx.game.view.screens.menu.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class MenuManager {
	
	/*
	 * Esta clase es la que se encarga de controlar lo que el usuario
	 * elegir� cuando inicie el Juego.
	 * Contar� con 5 opciones.
	 * -1- NEW -> Inicio de una partida nueva
	 * -2- RESUME -> Abre una partida almacenada
	 * -3- AYUDA -> Muestra como se juega. Esto es meramente informativo
	 * -4- RESOLUTION -> Puede cambiar la resolucion del juego.
	 * -5- SCORE -> Muestra una tabla con los puntajes obtenidos
	 * 
	 * A su vez, estar� la clase MenuManagerView que es la que se encarga de 
	 * mostrarle al usuario todas las opciones
	 */
	
	private static MenuManager self;
	private String playerName = "";
	//Iniciamos la pantalla en Fullscreen!!!
	private MenuChoose screenResolutionState = MenuChoose.FullScreen;
	//private final int MAXPLAYERNAME = 10;
	private MenuChoose choose = MenuChoose.Main; //te dice donde estas parado o a donde tenes que ir, en su defecto
	
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
	}
		
	public MenuChoose getChoose() {
		return choose;
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
			pressedOption1();
			break;
		case Keys.NUM_2:
		case Keys.NUMPAD_2:
			pressedOption2();
			break;
		case Keys.NUM_3:
		case Keys.NUMPAD_3:
			pressedOption3();
			break;
		case Keys.NUM_4:
		case Keys.NUMPAD_4:
			pressedOption4();
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
	
	public void pressedOption1(){
		switch(choose){
			case Main:
				choose = MenuChoose.New;
				break;
			case ChangeResolution:
				screenResolutionState=MenuChoose.NormalScreen;
				break;
			case HighScore:
			case Help:
				//tampoco tengo que hacer nada
				break;
			default:
				break;
		}
	}
	
	public void pressedOption2(){
		switch(choose){
		case Main:
			choose = MenuChoose.Help;
			break;
		case ChangeResolution:
			screenResolutionState=MenuChoose.WideScreen;
			//cambiar la resolucion a la segunda opcion
			break;
		case HighScore:
		case Help:
			//tampoco tengo que hacer nada
			break;
		default:
			break;
		}
	}

	public void pressedOption3(){
		switch(choose){
		case Main:
			choose = MenuChoose.ChangeResolution;
			break;
		case ChangeResolution:
			screenResolutionState=MenuChoose.FullScreen;
			//cambiar la resolucion a la tercera opcion
			break;
		case HighScore:
		case Help:
			//tampoco tengo que hacer nada
			break;
		default:
			break;
		}
	}
	
	public void pressedOption4(){
		switch(choose){
		case Main:
			choose = MenuChoose.HighScore;
			break;
		case ChangeResolution:
		case HighScore:
		case Help:
			//tampoco tengo que hacer nada
			break;
		default:
			break;
		}
	}
	

}
