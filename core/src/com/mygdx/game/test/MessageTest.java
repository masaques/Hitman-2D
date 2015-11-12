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
import com.mygdx.game.model.message.Noise;
import com.mygdx.game.model.message.NoiseListener;
import com.mygdx.game.model.message.NoiseManager;
import com.mygdx.game.model.message.NoiseType;
import com.mygdx.game.model.util.RandList;

public class MessageTest {
	
	private static List<Vector2> randArray = new RandList<Vector2>();
	private static TiledMap tiled_map;
	private static LevelMap map;
	private static Goon goon;
	private static AStarPathFinder aStarPathFinder;
	private static LinearPathFinder linearPathFinder;

	
	
	@BeforeClass
	public static void init() {
		
		randArray.add(new Vector2(200, 150));
		randArray.add(new Vector2(700, 700));
		randArray.add(new Vector2(73, 792));
		randArray.add(new Vector2(817, 48));
		//tiled_map = (new TmxMapLoader()).load("assets/test5.tmx");
		//map = new LevelMap(864, 864, 32, tiled_map);
		//map = new LevelMap(864, 864, 32, null);
		map = null;
		//goon = new Goon(new Rectangle(5, 5, 18, 13), map, randArray);
		goon = new Goon(new Rectangle(5, 5, 18, 13), map, randArray);
		//aStarPathFinder = new AStarPathFinder(map, 100);
		//linearPathFinder = new LinearPathFinder(map);
		
		//goon.setAStarPathFinder(aStarPathFinder);
		//goon.setLinearPathFinder(linearPathFinder);
	}
	
	@Test
	public void ThrowSound() {
		
		Goon goon= new Goon(new Rectangle(10, 10, 18, 13), map, randArray);
		NoiseManager.getInstance().addListener(goon);
		Noise2 noise = new Noise2(goon.getPosition(),100,NoiseType.SHOOT);
		NoiseManager.getInstance().dispatchMessage(noise);
		NoiseManager.getInstance().update();
		Assert.assertTrue(noise.getDispatched() == true);
	}

	class Noise2 extends Noise{
		
		private static final long serialVersionUID = 1L;
		private boolean DISPATCHED=false;
		
		public Noise2(Vector2 source, double effectiveRange, NoiseType type) {
			super(source, effectiveRange, type);
		}
		
		public void notify(NoiseListener l){
			DISPATCHED = true;
		}
		
		public boolean getDispatched(){
			return DISPATCHED;
		}
		
		
	}
}
