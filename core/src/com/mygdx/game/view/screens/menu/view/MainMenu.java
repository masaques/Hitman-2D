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
import com.mygdx.game.controller.HitmanGame;
import com.mygdx.game.view.screens.LevelScreen;

import java.util.*;

/**
 * {@link Screen} que se encarga del Menu
 * 
 *
 */
public class MainMenu implements Screen {

	private static List<TextButton> hitmanButtons;
	private static final int defaultHeight = 864;
	private static final int defaultWidth = 864;

	private HitmanGame game;
	private HitmanSkin skin;
	private Stage stage;

	public void create() {
		stage = new Stage();

	}

	public MainMenu(HitmanGame game) {
		this.game = game;
	}

	public MainMenu(HitmanSkin hitmanSkin, HitmanGame game, Stage stage) {
//		MenuData.m.play();
//		MenuData.m.setLooping(true);
		this.game = game;

		hitmanButtons = new ArrayList<TextButton>();
		this.skin = hitmanSkin;

	}

	public void resize(int width, int height) {
		if (stage == null)
			stage = new Stage();
		stage.clear();
		stage.getViewport().update(width, height);

		Gdx.input.setInputProcessor(stage);

		TextButton newGame = new TextButton("New Game", skin.getButtonSkin());
		newGame.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
					game.setScreen(new LevelSelectionMenu(game));
			}
		});
		newGame.setPosition(stage.getViewport().getWorldWidth() / 2 - skin.getSkinWidth() / 2,
				stage.getViewport().getWorldHeight() * 5 / 8);

		TextButton help = new TextButton("Help", skin.getButtonSkin());
		help.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {

				System.out.println("HOLA 3");
			}

		});
		help.setPosition(stage.getViewport().getWorldWidth() / 2 - skin.getSkinWidth() / 2,
				stage.getViewport().getWorldHeight() * 4 / 8);
		
		TextButton quit = new TextButton("Quit", skin.getButtonSkin());
		quit.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		quit.setPosition(stage.getViewport().getWorldWidth() / 2 - skin.getSkinWidth() / 2,
				stage.getViewport().getWorldHeight() * 3 / 8);

		stage.addActor(newGame);
		stage.addActor(help);
		stage.addActor(quit);
	}

	public List<TextButton> getMenuButtons() {
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
	public void pause() {

	}

	@Override
	public void resume() {


	}

	@Override
	public void hide() {

		dispose();

	}

	@Override
	public void dispose() {

		stage.dispose();

	}

}
