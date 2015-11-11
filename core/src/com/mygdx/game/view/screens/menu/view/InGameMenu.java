package com.mygdx.game.view.screens.menu.view;

import javax.xml.bind.JAXBException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.controller.HitmanGame;
import com.mygdx.game.view.screens.LevelScreen;

public class InGameMenu implements Screen {
	
	private HitmanGame game;
	private LevelScreen screen;
	private Stage stage ;
	private HitmanSkin skin ;
	
	public InGameMenu(HitmanGame game,LevelScreen screen) {
		this.game = game ;
		this.screen = screen ;
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
		restart.setPosition(stage.getViewport().getWorldWidth() / 2 - skin.getSkinWidth() / 2,
				stage.getViewport().getWorldHeight() * 5 / 8);
		
		TextButton proceed = new TextButton("Proceed", skin.getButtonSkin());
		proceed.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				screen.unpause();
				game.setScreen(screen);
			}
		});
		proceed.setPosition(stage.getViewport().getWorldWidth() / 2 - skin.getSkinWidth() / 2,
				stage.getViewport().getWorldHeight() * 4 / 8);
		
		TextButton main = new TextButton("Main Menu", skin.getButtonSkin());
		main.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new MainMenu(game));
			}
		});
		main.setPosition(stage.getViewport().getWorldWidth() / 2 - skin.getSkinWidth() / 2,
				stage.getViewport().getWorldHeight() * 3 / 8);
		
		TextButton quit = new TextButton("Quit", skin.getButtonSkin());
		quit.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		quit.setPosition(stage.getViewport().getWorldWidth() / 2 - skin.getSkinWidth() / 2,
				stage.getViewport().getWorldHeight() * 2 / 8);
		
		
		
		stage.addActor(restart);
		stage.addActor(proceed);
		stage.addActor(main);
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
		stage.dispose();
		
	}

}
