package com.mygdx.game.test;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.LevelMap;
import com.mygdx.game.model.character.AStarPathFinder;
import com.mygdx.game.model.character.Goon;
import com.mygdx.game.model.character.LinearPathFinder;
import com.mygdx.game.model.character.NPCState;
import com.mygdx.game.model.message.Bullet;
import com.mygdx.game.model.message.Noise;
import com.mygdx.game.model.message.NoiseListener;
import com.mygdx.game.model.message.NoiseManager;
import com.mygdx.game.model.message.NoiseType;
import com.mygdx.game.model.util.RandList;

public class MessageTest {
	
	private static List<Vector2> randArray = new RandList<Vector2>();
	private static LevelMap map;
	private static NoiseManager noisemanager;
	
	private static Goon goon; 
	private static NoiseDispChecker noise;
	
	@BeforeClass
	public static void init() {
		
		randArray.add(new Vector2(200, 150));
		randArray.add(new Vector2(700, 700));
		randArray.add(new Vector2(73, 792));
		randArray.add(new Vector2(817, 48));
		noisemanager = NoiseManager.getInstance();
		map = null;
		
		goon = new Goon(new Rectangle(10, 10, 18, 13), map, randArray);
		noise = new NoiseDispChecker (goon.getPosition(),100,NoiseType.SHOOT);
	}
	
	
	@Test
	public void ThrowSound() {
				
		NoiseManager.getInstance().addListener(goon);
		NoiseManager.getInstance().dispatchMessage(noise);
		NoiseManager.getInstance().update();
		
		Assert.assertTrue(noise.getDispatched() == true);
	}

	
	@Test
	public void NoListener() {
		
		noisemanager.clearAllListeners();
		noisemanager.addListener(goon);
		noisemanager.dispatchMessage(noise);
		
		noisemanager.removeListener(goon);
		noisemanager.update();
		
		Assert.assertTrue(noise.getDispatched() == false);
	}
	
	@Test
	public void NullListener() {
		noisemanager.clearAllListeners();
		noisemanager.addListener(null);
		noisemanager.dispatchMessage(noise);
		noisemanager.update();
	}
	
	@Test
	public void NullMessage() {
		noisemanager.clearAllListeners();
		noisemanager.addListener(goon);
		noisemanager.dispatchMessage(null);
		noisemanager.update();
	}
	
	
	
}

class NoiseDispChecker extends Noise{
	
	private static final long serialVersionUID = 1L;
	private boolean dispatched=false;
	
	public NoiseDispChecker(Vector2 source, double effectiveRange, NoiseType type) {
		super(source, effectiveRange, type);
	}
	
	public void notify(NoiseListener l){
		dispatched = true;
	}
	
	public boolean getDispatched(){
		return dispatched;
	}
}



	

