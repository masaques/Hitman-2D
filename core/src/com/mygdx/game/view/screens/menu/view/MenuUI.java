package com.mygdx.game.view.screens.menu.view;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.util.*;

public class MenuUI implements Screen{
	
	private static List<TextButton> hitmanButtons;
	private static final int defaultHeight = 864;
	private static final int defaultWidth = 864;
	
	
	public MenuUI(HitmanSkin hitmanSkin){
		MenuData.m.play();
		MenuData.m.setLooping(true);
//		MenuData.TITLE_FONT.draw(batch, "H I T M A N", 0, 0);

		hitmanButtons = new ArrayList<TextButton>();
		initialize(hitmanSkin);
	}
	
	void initialize(HitmanSkin hitmanSkin){
		TextButton newGame = new TextButton("New Game",hitmanSkin.getButtonSkin());
		TextButton resolution = new TextButton("Resolution",hitmanSkin.getButtonSkin());
		TextButton help = new TextButton("Help",hitmanSkin.getButtonSkin());
		TextButton table = new TextButton("Highscore",hitmanSkin.getButtonSkin());
		
		TextButton opt1 = new TextButton("4:3",hitmanSkin.getButtonSkin());
		TextButton opt2 = new TextButton("16:10",hitmanSkin.getButtonSkin());
		TextButton opt3 = new TextButton("Fullscreen",hitmanSkin.getButtonSkin());
		
		opt1.setVisible(false);
		opt2.setVisible(false);		
		opt3.setVisible(false);
		
		newGame.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				System.out.println("HOLA");
			}
			
		});
		newGame.setPosition(864/2-hitmanSkin.getSkinWidth()/2, 864*4/8);

		
		resolution.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				opt1.setVisible(true);
				opt2.setVisible(true);
				opt3.setVisible(true);
				opt1.addListener(ResolutionListener.option1());
				opt2.addListener(ResolutionListener.option2());				
				opt3.addListener(ResolutionListener.option3());
				opt1.setPosition(defaultWidth/2+1.1f*hitmanSkin.getSkinWidth()/2, defaultHeight*0);
				opt2.setPosition(defaultWidth/2+1.1f*hitmanSkin.getSkinWidth()/2, defaultHeight*4/8);				
				opt3.setPosition(defaultWidth/2+1.1f*hitmanSkin.getSkinWidth()/2, defaultHeight*3/8);
			}			
		});
		resolution.setPosition(864/2-hitmanSkin.getSkinWidth()/2, 864*3/8);
		
		help.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				
				System.out.println("HOLA 3");
			}
			
		});
		help.setPosition(864/2-hitmanSkin.getSkinWidth()/2, 864*2/8);
		
		table.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				System.out.println("HOLA 4");
			}
			
		});
		table.setPosition(864/2-hitmanSkin.getSkinWidth()/2, 864*1/8);
				
		hitmanButtons.add(newGame);
		hitmanButtons.add(resolution);		
		hitmanButtons.add(help);
		hitmanButtons.add(table);
		hitmanButtons.add(opt3);
		
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
