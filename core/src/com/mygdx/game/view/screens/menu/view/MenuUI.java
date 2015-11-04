package com.mygdx.game.view.screens.menu.view;

import java.util.TreeMap;

import java.util.Map.Entry;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.model.GameManager;
import com.mygdx.game.model.HitmanGame;
import com.mygdx.game.view.screens.menu.core.MenuData;

public class MenuUI implements MenuDrawable, Screen {
	
	HitmanGame game;
	
	

	private static MenuUI self = null;
	
	public MenuUI(HitmanGame game){
		this.game = game;
		
	}
	
	
	/*public MenuUI getInstance(){
		if(self==null)
			self = new MenuUI();
		
		return self;
	}*/
	
	@Override
	public void draw(SpriteBatch batch) {
		drawMenu(batch);
	}
	
	private void drawMenu(SpriteBatch batch){
		MenuChoose type = MenuManager.getInstance().getChoose();
	
		switch(type){
			case Main:
					showMain(batch);
				break;
			case Help:
					showHelp(batch);
				break;
			case HighScore:
					showTable(batch);
				break;
			case ChangeResolution:
				showChangeResolution(batch);
				break;
		default:
			break;				
		}
	}
	
	private void showMain(SpriteBatch batch){
		MenuData.TITLE_FONT.draw(batch, "HITMAN 2D", MenuData.WIDTH*3/8 , MenuData.HEIGHT*7/8);
		MenuData.FONT.draw(batch, "1- Nuevo",  MenuData.WIDTH/8, MenuData.HEIGHT*5/8);
		MenuData.FONT.draw(batch, "2- Configuraciones", MenuData.WIDTH/8, MenuData.HEIGHT*4/8);
		MenuData.FONT.draw(batch, "3- Ayuda",  MenuData.WIDTH/8, MenuData.HEIGHT*3/8);
		MenuData.FONT.draw(batch, "4- Puntajes",  MenuData.WIDTH/8, MenuData.HEIGHT*2/8);
		MenuData.FONT.draw(batch, "Esc - Salir",  MenuData.WIDTH/8, MenuData.HEIGHT*1/8);
	}
	
	private void showHelp(SpriteBatch batch){
		MenuData.TITLE_FONT.draw(batch, "Ayuda", MenuData.WIDTH*3/8 , MenuData.HEIGHT*7/8);
		MenuData.FONT.draw(batch, "Esc - Salir",  MenuData.WIDTH/8, MenuData.HEIGHT*1/8);
		//ARMAR LA AYUDA EN BASE A LOS MOVIMIENTOS EN TECLADOS
	}
	
	private void showTable(SpriteBatch batch){
		MenuData.TITLE_FONT.draw(batch, "Puntajes", MenuData.WIDTH*3/8 , MenuData.HEIGHT*7/8);
		MenuData.FONT.draw(batch, "Esc - Salir", MenuData.WIDTH/8, MenuData.HEIGHT*1/8);
		//Hay que implementar el HighScore Manager -- La idea es:
		//HighScoreManager.getInstance().loadScores();
		//TreeMap<Float, String> highscores = HighScoreManager.getInstance().getHighScores();
		
		MenuData.FONT.draw(batch, "Name", MenuData.WIDTH*2/8, MenuData.HEIGHT*2/3);
		MenuData.FONT.draw(batch, "Score", MenuData.WIDTH*5/8,  MenuData.HEIGHT*2/3);
				
		int count = 0;
		/*for (Entry<Float, String> each : highscores.entrySet()) {
			count++;
			String score = each.getKey().toString();
			String name = each.getValue();

			MenuData.FONT.draw(batch, name, MenuData.WIDTH*2/8, MenuData.HEIGHT*8/13 - count*30);
			MenuData.FONT.draw(batch, score, MenuData.WIDTH*5/8, MenuData.HEIGHT*8/13 - count*30);
			if(count == 10) break;
		}*/
	}
	
	private void showChangeResolution(SpriteBatch batch){
		MenuData.TITLE_FONT.draw(batch, "Resoluci�n", MenuData.WIDTH*3/8 , MenuData.HEIGHT*7/8);
		MenuData.FONT.draw(batch, "Esc - Salir", MenuData.WIDTH/8, MenuData.HEIGHT*1/8);
		MenuData.FONT.draw(batch,"1- 16:9", MenuData.WIDTH/8,MenuData.HEIGHT*5/8);
		MenuData.FONT.draw(batch,"2- 16:10",MenuData.WIDTH/8,MenuData.HEIGHT*4/8);
		MenuData.FONT.draw(batch,"3- 4:3", MenuData.WIDTH/8,MenuData.HEIGHT*3/8);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		drawMenu(game.batch);
		
	}

	@Override
	public void resize(int width, int height) {
		
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
