package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

/*
 * Guarda informacion de un camino como una serie de posiciones(pasos). 
 * Esta implementado como un stack, usando ArrayList.
 */
public class Path {
	public ArrayList<Step> steps = new ArrayList<Step>();
	private int counter = 0;
	
	public float getLength(){
		float length = 0;
		for (int i = 0; i < counter-1 ; i++) {
			Vector2 posa = steps.get(i).getPosition();
			Vector2 posb = steps.get(i+1).getPosition();
			length += posa.dst(posb);
		}
		return length;
	}
	/*
	 * Devuelve el proximo paso.
	 */
	public Step nextStep(){
		return steps.get(--counter);
	}
	/*
	 * Devuelve si hay un proximo paso.
	 */
	public boolean hasNextStep(){
		if (counter > 0)
			return true;
		return false;
	}
	/*
	 * Agrega un paso.
	 * @param stepPosition
	 */
	public void prependStep(Vector2 stepPosition){
		steps.add(counter,new Step(stepPosition));
		counter++;
	}
	
	
	public boolean equals(Object o){
		if ( o == null){
			return false;
		}
		if (o.getClass() != Path.class){
			return false;
		}
		return ((Path)o).steps.equals(this.steps);
	}
}
