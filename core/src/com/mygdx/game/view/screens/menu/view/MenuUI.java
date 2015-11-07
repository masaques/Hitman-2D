package com.mygdx.game.view.screens.menu.view;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.HitmanGame;
import com.mygdx.game.view.screens.menu.core.MenuData;

public class MenuUI implements Screen {
	
	public HitmanGame game;
	
	public MenuUI(HitmanGame game){
		this.game = game;
	}
	
		
	public void drawMenu(SpriteBatch batch, MenuChoose type){
		//MenuChoose type = MenuManager.getInstance().getChoose();
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
	
	private static void showMain(SpriteBatch batch){
		//ResolutionManager.fullScreen(true);
		MenuData.m.play();
		MenuData.m.setLooping(true);
		MenuData.TITLE_FONT.draw(batch, "H I T M A N", MenuData.WIDTH*3/8 , MenuData.HEIGHT*7/8);
		MenuData.FONT.draw(batch, "1- Nuevo",  MenuData.WIDTH/8, MenuData.HEIGHT*5/8);
		MenuData.FONT.draw(batch, "2- Configuraciones", MenuData.WIDTH/8, MenuData.HEIGHT*4/8);
		MenuData.FONT.draw(batch, "3- Ayuda",  MenuData.WIDTH/8, MenuData.HEIGHT*3/8);
		MenuData.FONT.draw(batch, "4- Screens",  MenuData.WIDTH/8, MenuData.HEIGHT*2/8);
		MenuData.FONT.draw(batch, "Esc - Salir",  MenuData.WIDTH/8, MenuData.HEIGHT*1/8);
	}
	
	private static void showHelp(SpriteBatch batch){
		MenuData.TITLE_FONT.draw(batch, "Ayuda", MenuData.WIDTH*3/8 , MenuData.HEIGHT*7/8);
		MenuData.FONT.draw(batch, "Esc - Salir",  MenuData.WIDTH/8, MenuData.HEIGHT*1/8);
		//ARMAR LA AYUDA EN BASE A LOS MOVIMIENTOS EN TECLADOS
	}
	
	private static void showTable(SpriteBatch batch){
		MenuData.TITLE_FONT.draw(batch, "Puntajes", MenuData.WIDTH*3/8 , MenuData.HEIGHT*7/8);
		MenuData.FONT.draw(batch, "Esc - Salir", MenuData.WIDTH/8, MenuData.HEIGHT*1/8);		
	}
	
	private static void showChangeResolution(SpriteBatch batch){
		MenuData.TITLE_FONT.draw(batch, "Resolucion", MenuData.WIDTH*3/8 , MenuData.HEIGHT*7/8);
		MenuData.FONT.draw(batch, "Esc - Salir", MenuData.WIDTH/8, MenuData.HEIGHT*1/8);
		MenuData.FONT.draw(batch,"1- 16:9", MenuData.WIDTH/8,MenuData.HEIGHT*5/8);
		MenuData.FONT.draw(batch,"2- 16:10",MenuData.WIDTH/8,MenuData.HEIGHT*4/8);
		MenuData.FONT.draw(batch,"3- 4:3", MenuData.WIDTH/8,MenuData.HEIGHT*3/8);
	}

	
	@Override
	public void show() {
		//drawMenu(game.batch);		
	}

	@Override
	public void render(float delta) {
		//drawMenu(game.batch);
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
