package com.mygdx.game.view.screens.menu.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;

public class ResolutionManager {
	

	private static ResolutionManager self;
	private static final int wideScreenWidth = 16;
	private static final int wideScreenHeight = 9;
	private static final int normalScreenWidth = 4;
	private static final int normalScreenHeight = 3;
	private static final int myWindowWidth = Gdx.graphics.getWidth();
	private static final int myWindowHeight = Gdx.graphics.getHeight();
	
	private ResolutionManager(){
		DisplayMode[] dm = Gdx.graphics.getDisplayModes();
	}
	
	public static ResolutionManager getInstance(){
		if(self == null){
			self = new ResolutionManager();
		}
		return self;
	}
	
		
	public static void fullScreen(boolean fullscreen){
		Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width, Gdx.graphics.getDesktopDisplayMode().height, true);
	}
	
	//Hacer un calculo  para eso!!!
	public void wideScreen(){
		Gdx.graphics.setDisplayMode(0,0,false);
	}
	
	public void normalScreen(){
		Gdx.graphics.setDisplayMode(0,0,false);
	}
		
	

}
