package com.mygdx.game.view.screens.menu.view;

import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
	
	Stage stage;
	
	public void create () {
	    stage = new Stage();
	}
	
	
	public MenuUI(HitmanGame game) {
	    this.game = game;
	}
	
	
	public MenuUI(HitmanSkin hitmanSkin,HitmanGame game,Stage stage){
		//MenuData.m.play();
		//MenuData.m.setLooping(true);
		this.game=game;

		hitmanButtons = new ArrayList<TextButton>();
		this.hitmanSkin = hitmanSkin;
		
		
		
		
	}
	
	public void resize(int width, int height){
		 if (stage == null)
		        stage = new Stage();
		    stage.clear();
		    
		    Gdx.input.setInputProcessor(stage);
		    
		TextButton newGame = new TextButton("New Game",hitmanSkin.getButtonSkin());
		TextButton help = new TextButton("Help",hitmanSkin.getButtonSkin());
		TextButton table = new TextButton("Highscore",hitmanSkin.getButtonSkin());

		
		newGame.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				
				
				try {
					game.setScreen(new LevelScreen(game));
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		
		
		
		
		newGame.setPosition(864/2-hitmanSkin.getSkinWidth()/2, 864*4/8);
		newGame.setBounds(864/2-hitmanSkin.getSkinWidth()/2, 864*4/8,hitmanSkin.getSkinWidth(),hitmanSkin.getSkinHeight());
		
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
				
		
		stage.addActor(newGame);
		stage.addActor(help);
		stage.addActor(table);
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
	   // Audio.playMusic(true);
	    
	    hitmanSkin = new HitmanSkin();
	    
	}

	@Override
	public void render(float delta) {
	    Gdx.gl.glClearColor(0.09f, 0.28f, 0.2f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	    stage.act(delta);
	    stage.draw();

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
		
		dispose();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		
		stage.dispose();
		
	}

}
