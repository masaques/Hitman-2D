package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

public class RandArray<T>{
	private ArrayList<T> array = new ArrayList<T>();
	private Random rand = new Random();
	
	public void add(T e){
		array.add(e);
	}
	public T get(){
		return array.get(Math.abs(rand.nextInt()) % array.size());
	}
}
