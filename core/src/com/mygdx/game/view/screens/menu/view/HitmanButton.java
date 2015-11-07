package com.mygdx.game.view.screens.menu.view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class HitmanButton extends Actor implements MenuClickable{

	TextButton button;
	
	public HitmanButton(String text, HitmanSkin skin) {
		button = new TextButton(text,skin.getButtonSkin());
	}

	@Override
	public void setOnClickListener(ClickListener listener) {
		button.addListener(listener);
	}


}
