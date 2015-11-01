package com.mygdx.game.model.util;

import java.util.ArrayList;
import java.util.Random;

public class RandList<T> extends ArrayList<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Random rand = new Random();
	
	@Override
	public T get(int index){
		return super.get(Math.abs(rand.nextInt()) % size());
	}
}
