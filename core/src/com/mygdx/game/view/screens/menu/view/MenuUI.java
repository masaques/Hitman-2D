package com.mygdx.game.view.screens.menu.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuUI implements Screen{

	private Stage stage;
	
	public MenuUI(){
		//public Stage (Viewport viewport, Batch batch);
		//Tanto el viewport como el batch los saco de levelscreen
		stage=new Stage();
	}
	
	public static void drawOnScreen(){
		HitmanSkin skin = new HitmanSkin();
		
		HitmanButton start = new HitmanButton("Start",skin);
				
		start.setOnClickListener(new ClickListener(){
			
			public void clicked(InputEvent event, float x, float y){
				Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width,Gdx.graphics.getDesktopDisplayMode().width,true);
			}
			
		});
		
	}
	
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		stage.act();
		stage.draw();
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
