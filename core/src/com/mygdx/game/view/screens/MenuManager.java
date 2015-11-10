package com.mygdx.game.view.screens;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input.Keys;

public class MenuManager {

	private static MenuManager self;
	private String playerName = "";
	private MenuChoose screenResolutionState = MenuChoose.FullScreen;
	private MenuChoose choose = MenuChoose.Main; // te dice donde estas parado o
													// a donde tenes que ir, en
													// su defecto

	public MenuManager() {

	}

	public static MenuManager getInstance() {
		if (self == null) {
			self = new MenuManager();
		}
		return self;
	}

	public void reset() {
		choose = MenuChoose.Main;
		playerName = "";
	}

	public void goBack() {
		switch (choose) {
		case Main:
			Gdx.app.exit();
			break;
		case New:
		case Help:
		case ChangeResolution:
			choose = MenuChoose.Main;
			break;
		default:
			break;
		}
	}

	public void keyDown(int keycode) {
		switch (keycode) {
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

	public void pressedOption1() {
		switch (choose) {
		case Main:
			choose = MenuChoose.New;
			break;
		case ChangeResolution:
			screenResolutionState = MenuChoose.NormalScreen;
			break;
		case HighScore:
		case Help:
			// tampoco tengo que hacer nada
			break;
		default:
			break;
		}
	}

	public void pressedOption2() {
		switch (choose) {
		case Main:
			choose = MenuChoose.Help;
			break;
		case ChangeResolution:
			screenResolutionState = MenuChoose.WideScreen;
			// cambiar la resolucion a la segunda opcion
			break;
		case HighScore:
		case Help:
			// tampoco tengo que hacer nada
			break;
		default:
			break;
		}
	}

	public void pressedOption3() {
		switch (choose) {
		case Main:
			choose = MenuChoose.ChangeResolution;
			break;
		case ChangeResolution:
			screenResolutionState = MenuChoose.FullScreen;
			// cambiar la resolucion a la tercera opcion
			break;
		case HighScore:
		case Help:
			// tampoco tengo que hacer nada
			break;
		default:
			break;
		}
	}

	public void pressedOption4() {
		switch (choose) {
		case Main:
			choose = MenuChoose.HighScore;
			break;
		case ChangeResolution:
		case HighScore:
		case Help:
			// tampoco tengo que hacer nada
			break;
		default:
			break;
		}
	}

}
