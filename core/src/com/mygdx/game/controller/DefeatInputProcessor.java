package com.mygdx.game.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class DefeatInputProcessor implements InputProcessor {
	private boolean requestReset;
	
	public boolean requestReset() {
		boolean aux = requestReset;
		requestReset = false;
		return aux;
	}
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.R) {
			requestReset = true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
