package com.mygdx.game.view.screens.menu.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class VictoryDefeatSkin {

	Skin skin;
	int width = 200;
	int height = (int) Gdx.graphics.getHeight() / 10;

	public VictoryDefeatSkin() {
		BitmapFont font = new BitmapFont();
		skin = new Skin();
		skin.add("default", font);

		Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA4444);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("background", new Texture(pixmap));

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("background", Color.RED);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);
	}

	public Skin getButtonSkin() {
		return skin;
	}

	public int getSkinHeight() {
		return height;
	}

	public int getSkinWidth() {
		return width;
	}

}
