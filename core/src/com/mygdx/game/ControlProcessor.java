package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
/**
 * La vieja clase ControlHandler, que ahora se ocupa mas puramente 
 * de cuestiones vinculadas con el input
 * 
 * @author traies
 * @author masaques
 *
 */
public class ControlProcessor implements InputProcessor {
	private static int MOVE_LEFT = 0;
	private static int MOVE_UP = 1;
	private static int MOVE_RIGHT = 2;
	private static int MOVE_DOWN = 3;
	private static double SIN45 = 0.7071;
	
	private boolean move_left;
	private boolean move_right;
	private boolean move_up;
	private boolean move_down;
	private boolean move_run;
	private float x ;
	private float y ;
	private int mouse_x;
	private int mouse_y;
	private boolean mouse_click;
	
	public void update(){
		if (move_left)
			x = -1;
		if (move_right)
			x = 1;
		if (move_up)
			y = 1;
		if (move_down)
			y = -1;
		if (y!=0 && x!=0){
			x *= SIN45;
			y *= SIN45;
        }
	}
	
	public PlayerMovement getPlayerMovement() {
		PlayerMovement ans = new PlayerMovement(
				new Vector2(x,y).nor(),
				move_run,
				new Vector2(mouse_x, mouse_y));
		this.x = 0f;
		this.y = 0f;
		return ans ;
	}
	/**
	 * Devuelve la posicion en la que se hizo click
	 * 
	 * @return null si no se hizo click
	 */
	public Vector2 getMouseClick(){
		Vector2 ans = null ;
		if (mouse_click) {
			mouse_click = false ;
			ans= new Vector2((float)mouse_x,(float)mouse_y) ;
		}
		return ans ;
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
        return false;
    }
	
    @Override
    public boolean keyTyped(char character) {
    	
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    	mouse_click = true;
        return false;
    	
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
    	mouse_x = screenX;
    	mouse_y = 864-screenY;
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    
}
