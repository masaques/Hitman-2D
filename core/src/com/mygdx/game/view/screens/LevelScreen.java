package com.mygdx.game.view.screens;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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
import com.mygdx.game.controller.GameState;
import com.mygdx.game.controller.HitmanGame;
import com.mygdx.game.controller.IllegalPositionException;
import com.mygdx.game.serialization.Level;
import com.mygdx.game.view.screens.menu.view.InGameMenu;

public class LevelScreen implements Screen {

	private OrthographicCamera camera;
	private Viewport gameport;
	private OrthogonalTiledMapRenderer renderer;
	private SpriteBatch batch;
	private FPSLogger fps_logger = new FPSLogger();
	private GameManager gameManager;
	private HitmanGame game ;
	private Level l ;

	public LevelScreen(HitmanGame game) throws JAXBException {
		/**
		 * TODO : Al levelScreen le deberian pasar el Level
		 * Carga el nivel desde archivo
		 */
		this.game = game ;
		JAXBContext context = JAXBContext.newInstance(Level.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		l = (Level) unmarshaller.unmarshal(new File("assets/Level2.xml"));

		this.batch = new SpriteBatch();
		camera = new OrthographicCamera();
		gameport = new FitViewport(864, 864, camera);
		gameport.apply();

		try {
			gameManager = new GameManager(864, 864, 32, gameport, l, batch);
		} catch (IllegalPositionException e) {
			e.printStackTrace();
		}

		renderer = new OrthogonalTiledMapRenderer(gameManager.getTiledMap());

	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		if (gameManager.getState() == GameState.PAUSE) {
			game.setScreen(new InGameMenu(game,this));
			return ;
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