package com.mygdx.game.view.screens;



/**
 *  Screen del nivel principal donde se llevara a cabo el juego. Posee la información completa
 *  del juego y se encarga del renderizado.
 *  
 *  @author jcaracciolo
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.controller.GameManager;
import com.mygdx.game.controller.HitmanGame;
import com.mygdx.game.controller.IllegalPositionException;
import com.mygdx.game.serialization.Level;
import com.mygdx.game.view.screens.menu.view.EndGameMenu;
import com.mygdx.game.view.screens.menu.view.InGameMenu;
import com.mygdx.game.view.screens.menu.view.MainMenu;

public class LevelScreen implements Screen {

	private OrthographicCamera camera;
	private Viewport gameport;
	private OrthogonalTiledMapRenderer renderer;
	private SpriteBatch batch;
	private FPSLogger fps_logger = new FPSLogger();
	private GameManager gameManager;
	private HitmanGame game ;
	private Level l ;

	public LevelScreen(HitmanGame game, Level level) {
		
		this.game = game ;
		this.l= level ;

		this.batch = game.getBatch();
		camera = new OrthographicCamera();
		gameport = new FitViewport(864, 864, camera);
		gameport.apply();

		try {
			gameManager = new GameManager(864, 864, 32, gameport, l, batch);
		} catch (IllegalPositionException e) {
			game.setScreen(new MainMenu(game));
		}

		renderer = new OrthogonalTiledMapRenderer(gameManager.getTiledMap());
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		
		switch (gameManager.getState())  {
			case PAUSE :
				game.setScreen(new InGameMenu(game,this));
				break ;
			case PLAY :
				break ;
			case WIN :
				game.setScreen(new EndGameMenu(game,this,"Victory")) ;
				break ;
			case DEFEAT :
				game.setScreen(new EndGameMenu(game,this ,"Defeat")) ;
				break ;
		}
		fps_logger.log();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		renderer.setView(camera);
		renderer.render();
		gameManager.update();
		
	}
	
	public void reload() {
		try {
			this.gameManager = new GameManager(864, 864, 32, gameport, l, batch);
		} catch (IllegalPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			game.setScreen(new MainMenu(game));
		}
	}

	@Override
	public void resize(int width, int height) {
		gameport.update(width, height, true);
	}
	public void unpause() {
		gameManager.unpause();
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