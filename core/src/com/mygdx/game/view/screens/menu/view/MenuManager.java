package com.mygdx.game.view.screens.menu.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.model.HitmanGame;

public class MenuManager {
	
	private String playerName = "";
	private MenuChoose choose = MenuChoose.Main; 
	HitmanGame game;
	MenuUI menuUi;
	
	public MenuManager(HitmanGame game){
			this.game = game;
			menuUi = new MenuUI(game);
			//MenuUI.getInstance();
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
		menuUi.drawMenu(game.batch, choose);
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
				break;
			case HighScore:
				//elije 4:3
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
			//cambiar la resolucion a la segunda opcion
			break;
		case HighScore:
			//elije 16:9
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
			//elije fullscreen
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
	
	public MenuUI setScreen(){
		return menuUi;
	}

}
