package com.mygdx.game.view.screens.menu.view;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.controller.HitmanGame;
import com.mygdx.game.serialization.Level;
import com.mygdx.game.view.screens.LevelScreen;

public class LevelSelectionMenu implements Screen {
	private HitmanGame  game ;
	private HitmanSkin skin ;
	private Stage stage;
	
	private JAXBContext context ;
	private Unmarshaller unmarshaller ;
	
	public LevelSelectionMenu(HitmanGame game) {
		this.game = game ;
		// TODO Revisar
		
		try {
			context = JAXBContext.newInstance(Level.class);
			unmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
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
		
		TextButton l1 = new TextButton("Level 1", skin.getButtonSkin());
		l1.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				// TODO Ver
				try {
					Level l = (Level) unmarshaller.unmarshal(new File("assets/Level2.xml"));
					LevelScreen screen = new LevelScreen(game,l) ;
					game.setScreen(screen);
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		});
		l1.setPosition(stage.getViewport().getWorldWidth() /2 - skin.getSkinWidth() /2,
				stage.getViewport().getWorldHeight() * 6/8 );
		
		TextButton l2 = new TextButton("Level 2", skin.getButtonSkin());
		l2.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("HOLA");
		}
		});
		l2.setPosition(stage.getViewport().getWorldWidth() /2  - skin.getSkinWidth()/2 ,
				stage.getViewport().getWorldHeight() * 5 / 8);
		
		TextButton l3 = new TextButton("Level 3", skin.getButtonSkin());
		l3.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("HOLA");
		}
		});
		l3.setPosition(stage.getViewport().getWorldWidth() /2 - skin.getSkinWidth() /2,
				stage.getViewport().getWorldHeight() * 4 / 8);
		
		TextButton l4 = new TextButton("Level 4", skin.getButtonSkin());
		l4.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("HOLA");
		}
		});
		l4.setPosition(stage.getViewport().getWorldWidth() /2 - skin.getSkinWidth() /2 ,
				stage.getViewport().getWorldHeight() * 3 / 8);
		
		TextButton l5 = new TextButton("Main Menu", skin.getButtonSkin());
		l5.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new MainMenu(game));
		}
		});
		l5.setPosition(stage.getViewport().getWorldWidth() /2 - skin.getSkinWidth() /2 ,
				stage.getViewport().getWorldHeight() * 2/ 8);
	
		stage.addActor(l1);
		stage.addActor(l2);
		stage.addActor(l3);
		stage.addActor(l4);
		stage.addActor(l5);
		
		
		
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
