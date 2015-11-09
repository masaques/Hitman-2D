package com.mygdx.game.view.screens.menu.view;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.model.HitmanGame;
import com.mygdx.game.view.screens.LevelScreen;

import java.util.*;

public class MenuUI implements Screen{
	
	private static List<TextButton> hitmanButtons;
	private static final int defaultHeight = 864;
	private static final int defaultWidth = 864;
	
	HitmanGame game;
	HitmanSkin hitmanSkin;
	
	
	public MenuUI(HitmanSkin hitmanSkin,HitmanGame game){
		MenuData.m.play();
		MenuData.m.setLooping(true);
		this.game=game;

		hitmanButtons = new ArrayList<TextButton>();
		this.hitmanSkin = hitmanSkin;
		initialize(hitmanSkin);
	}
	
	void initialize(HitmanSkin hitmanSkin){
		
		TextButton newGame = new TextButton("New Game",hitmanSkin.getButtonSkin());
		TextButton help = new TextButton("Help",hitmanSkin.getButtonSkin());
		TextButton table = new TextButton("Highscore",hitmanSkin.getButtonSkin());

		
		newGame.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				game.setScreen(new LevelScreen(game));
			}
		});
		newGame.setPosition(864/2-hitmanSkin.getSkinWidth()/2, 864*4/8);

		
		help.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				
				System.out.println("HOLA 3");
			}
			
		});
		help.setPosition(864/2-hitmanSkin.getSkinWidth()/2, 864*3/8);
		
		
		table.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				System.out.println("HOLA 4");
			}
			
		});
		table.setPosition(864/2-hitmanSkin.getSkinWidth()/2, 864*2/8);
				
		hitmanButtons.add(newGame);
		hitmanButtons.add(help);
		hitmanButtons.add(table);
	}
	
	public List<TextButton> getMenuButtons(){
		return hitmanButtons;
	}

	public static int getDefaultheight() {
		return defaultHeight;
	}
	
	public static int getDefaultwidth() {
		return defaultWidth;
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
