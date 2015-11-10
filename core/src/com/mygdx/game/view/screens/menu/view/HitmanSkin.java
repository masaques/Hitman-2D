package com.mygdx.game.view.screens.menu.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class HitmanSkin {

	Skin skin;
	int ancho = 200;
	int alto = (int) Gdx.graphics.getHeight() / 10;

	public HitmanSkin() {
		BitmapFont font = new BitmapFont();
		skin = new Skin();
		skin.add("default", font);

		Pixmap pixmap = new Pixmap(ancho, alto, Pixmap.Format.RGBA4444);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("background", new Texture(pixmap));

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
		// textButtonStyle.down = skin.newDrawable("background",
		// Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
		textButtonStyle.over = skin.newDrawable("background", Color.GREEN);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);
	}

	public Skin getButtonSkin() {
		return skin;
	}

	public int getSkinHeight() {
		return alto;
	}

	public int getSkinWidth() {
		return ancho;
	}

}
