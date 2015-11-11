package com.mygdx.game.view.screens.menu.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.controller.HitmanGame;
import com.mygdx.game.view.screens.LevelScreen;

public class EndGameMenu implements Screen{
	
	private HitmanGame  game ;
	private LevelScreen screen ;
	private HitmanSkin skin ;
	private Stage stage;
	private String state;
	
	public EndGameMenu(HitmanGame game, LevelScreen screen, String state) {
		this.game = game ;
		this.screen = screen ;
		this.state = state ;
		
	}
	
	@Override
	public void show() {
		// Audio.playMusic(true);

		skin = new HitmanSkin();

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.09f, 0.28f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();

	}
	

	@Override
	public void resize(int width, int height) {
		
		if (stage == null)
			stage = new Stage();
		stage.clear();
		stage.getViewport().update(width, height);

		Gdx.input.setInputProcessor(stage);
		
		TextButton restart = new TextButton("Restart", skin.getButtonSkin());
		restart.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				screen.reload();
				game.setScreen(screen);
		}
		});
		restart.setPosition(stage.getViewport().getWorldWidth() /2  - skin.getSkinWidth()/2 ,
				stage.getViewport().getWorldHeight() * 5 / 8);
		
		TextButton mainmenu = new TextButton("Main Menu", skin.getButtonSkin());
		mainmenu.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new MainMenu(game));
		}
		});
		mainmenu.setPosition(stage.getViewport().getWorldWidth() /2  - skin.getSkinWidth()/2 ,
				stage.getViewport().getWorldHeight() * 4 / 8);
		
		TextButton quit = new TextButton("Quit", skin.getButtonSkin());
		quit.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit() ;
		}
		});
		quit.setPosition(stage.getViewport().getWorldWidth() /2  - skin.getSkinWidth()/2 ,
				stage.getViewport().getWorldHeight() * 3 / 8);
		
		stage.addActor(restart);
		stage.addActor(mainmenu);
		stage.addActor(quit);
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
