package com.mygdx.game.view.screens.menu.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.audio.Music;

public class MenuData {

	public static final float VOLUME_MENU = 0.3f;

	public static final Music m = Gdx.audio.newMusic(Gdx.files.internal("assets/MenuMusic.mp3"));

	public static final Sound MENU_SOUND = Gdx.audio.newSound(Gdx.files.internal("assets/MenuMusic.mp3"));
	public static final Sound MENU_SELECT = Gdx.audio.newSound(Gdx.files.internal("assets/select.mp3"));

	public static final BitmapFont FONT = new BitmapFont(Gdx.files.internal("assets/arcade.fnt"));
	public static final BitmapFont SMALL_FONT = new BitmapFont(Gdx.files.internal("assets/little.fnt"));
	public static final BitmapFont TITLE_FONT = new BitmapFont(Gdx.files.internal("assets/title.fnt"));

	public static final float WIDTH = 800f;
	public static final float HEIGHT = 600f;

}
