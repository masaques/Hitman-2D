package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainMenu implements Screen{

	public Stage stage; //necesario para crear el menu. Es un "contenedor" del resto
	private TextureAtlas atlas; //Loads images from texture atlases created by TexturePacker.
	private Skin skin; //Es la apariencia de nuestros buttons
	private Table table; //Para que botones y texto queden alineados
	private TextButton buttonPlay, buttonExit;
	private Label heading;
	private BitmapFont whiteFont, blackFont;
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		atlas = new TextureAtlas("");
		skin = new Skin(atlas);
						
				
		whiteFont = new BitmapFont(Gdx.files.internal("assets/white.fnt"),false);
		blackFont = new BitmapFont(Gdx.files.internal("assets/black.fnt"),false);
	}

}
