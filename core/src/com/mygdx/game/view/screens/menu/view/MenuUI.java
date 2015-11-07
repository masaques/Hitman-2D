package com.mygdx.game.view.screens.menu.view;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.util.*;

public class MenuUI implements Screen{
	
	private static List<TextButton> hitmanButtons;
	
	public MenuUI(HitmanSkin hitmanSkin){
		hitmanButtons = new ArrayList<TextButton>();
		initialize(hitmanSkin);
	}
	
	void initialize(HitmanSkin hitmanSkin){
		TextButton newGame = new TextButton("New Game",hitmanSkin.getButtonSkin());
		TextButton resolution = new TextButton("Resolution",hitmanSkin.getButtonSkin());
		
		newGame.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				System.out.println("HOLA");
			}
			
		});
		newGame.setPosition(864/2, 864*0.25f);

		
		resolution.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				System.out.println("HOLA 2");
			}
			
		});
		resolution.setPosition(864/2, 864*0.75f);
		
		hitmanButtons.add(newGame);
		hitmanButtons.add(resolution);		
	}
	
	public List<TextButton> getMenuButtons(){
		return hitmanButtons;
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
