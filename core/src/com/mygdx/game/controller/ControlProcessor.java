package com.mygdx.game.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * La vieja clase ControlHandler, que ahora se ocupa mas puramente de cuestiones
 * vinculadas con el input
 * 
 * @author traies
 * @author masaques
 *
 */
public class ControlProcessor implements InputProcessor {

	private static double SIN45 = 0.7071;

	private Viewport viewport;
	private boolean move_left;
	private boolean move_right;
	private boolean move_up;
	private boolean move_down;
	private boolean move_run;
	private float x;
	private float y;
	private Vector2 mousePosition;
	private boolean mouse_click;
	private boolean request_save ;
	private boolean paused;
	/**
	 * El control processor recibe el viewport para hacer los ajustes necesarios
	 * al mouseposition
	 * 
	 * @param viewport
	 */
	public ControlProcessor(Viewport viewport) {
		this.viewport = viewport;
		this.mousePosition = new Vector2();
	}

	public void update() {
		if (move_left)
			x = -1;
		if (move_right)
			x = 1;
		if (move_up)
			y = 1;
		if (move_down)
			y = -1;
		if (y != 0 && x != 0) {
			x *= SIN45;
			y *= SIN45;
		}
	}

	/**
	 * Obtiene el movimiento del jugador y genera un {@link PlayerMovement} con
	 * el correspondiente
	 * 
	 * @return {@link PlayerMovement} - Información del movimiento del jugador
	 */
	public PlayerMovement getPlayerMovement() {
		PlayerMovement ans = new PlayerMovement(new Vector2(x, y).nor(), move_run,
				new Vector2(mousePosition.x, mousePosition.y), mouse_click);
		this.mouse_click = false;
		this.x = 0f;
		this.y = 0f;
		return ans;
	}

	/**
	 * Devuelve la posicion en la que se hizo click
	 * 
	 * @return null si no se hizo click
	 */
	public Vector2 getMouseClick() {
		Vector2 ans = null;
		if (mouse_click) {
			mouse_click = false;
			ans = new Vector2(mousePosition.x, mousePosition.y);
		}
		return ans;
	}

	/**
	 * Realiza acción segun el Key que este levantado
	 */
	@Override
    public boolean keyUp(int keycode) {
		if(keycode == Input.Keys.SHIFT_LEFT)
    		move_run = false;
    	if(keycode == Input.Keys.A)
    		move_left = false;
        if(keycode == Input.Keys.D)
        	move_right = false;
        if(keycode == Input.Keys.W)
        	move_up = false;
        if(keycode == Input.Keys.S)
            move_down = false;
        return false;
    }


	/**
	 * Realiza acción segun el Key que este apretado
	 */
	@Override
    public boolean keyDown(int keycode) {
    	if(keycode == Input.Keys.SHIFT_LEFT)
    		move_run = true;
    	if(keycode == Input.Keys.A)
    		move_left = true;
        if(keycode == Input.Keys.D)
        	move_right = true;
        if(keycode == Input.Keys.W)
        	move_up = true;
        if(keycode == Input.Keys.S)
            move_down = true;
        if (keycode == Input.Keys.O)
        	request_save = true ;
        if (keycode == Input.Keys.ESCAPE)
        	paused = true ;
        return false;
    }
	public boolean requestSave() {
		boolean tmp = request_save ;
		request_save = false ;
		return tmp ;
	}

	public boolean paused() {
		boolean ans = paused ;
		paused = false ;
		return ans ;
	}
	
	public void reset(){
		x = 0;
		y = 0;
		move_left = false;
		move_right = false;
		move_up = false;
		move_down = false;
		move_run = false;
	}
	
    @Override
    public boolean keyTyped(char character) {
    	
        return false;
    }


	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		mouse_click = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;

	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		viewport.unproject(mousePosition.set(screenX, screenY));
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
