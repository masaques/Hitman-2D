package com.mygdx.game.view.screens.menu.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ResolutionListener {

	public static ClickListener option1() {
		ClickListener cl = new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Gdx.graphics.setDisplayMode(StartMenu.getDefaultwidth(), StartMenu.getDefaultheight(), false);
			}
		};
		return cl;
	}

	public static ClickListener option2() {
		ClickListener cl = new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Gdx.graphics.setDisplayMode(StartMenu.getDefaultwidth() * 5 / 3, StartMenu.getDefaultheight(), false);
			}
		};
		return cl;
	}

	public static ClickListener option3() {
		ClickListener cl = new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width,
						Gdx.graphics.getDesktopDisplayMode().height, true);
			}
		};
		return cl;
	}

}
