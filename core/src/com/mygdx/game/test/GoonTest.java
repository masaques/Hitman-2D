package com.mygdx.game.test;

import java.io.File;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.LevelMap;
import com.mygdx.game.model.character.AStarPathFinder;
import com.mygdx.game.model.character.Goon;
import com.mygdx.game.model.character.LinearPathFinder;
import com.mygdx.game.model.character.NPCState;
import com.mygdx.game.model.message.Noise;
import com.mygdx.game.model.message.NoiseType;
import com.mygdx.game.model.util.RandList;
import com.mygdx.game.serialization.Level;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GoonTest {

	List<Vector2> randArray = new RandList<Vector2>();
	TiledMap tiled_map;
	LevelMap map;
	Level level;
	Goon goon;
	AStarPathFinder aStarPathFinder;
	LinearPathFinder linearPathFinder;
	
	//l = (Level) unmarshaller.unmarshal(new File("assets/Level2.xml"));

	@Before
	public void init() throws JAXBException {
		randArray.add(new Vector2(200, 150));
		randArray.add(new Vector2(700, 700));
		randArray.add(new Vector2(73, 792));
		randArray.add(new Vector2(817, 48));
		goon = new Goon(new Rectangle(5, 5, 18, 13), map, randArray);
		
		
		JAXBContext context = JAXBContext.newInstance(Level.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		level = (Level) unmarshaller.unmarshal(new File("assets/Level1.xml"));
		
		//ACA SE PRODUCE EL NULLPOINTEREXCEPTION!!!
		tiled_map = new TmxMapLoader().load(level.getPath());
		
		map = new LevelMap(864, 864, 32, tiled_map);
		
		aStarPathFinder = new AStarPathFinder(map, 100);
		linearPathFinder = new LinearPathFinder(map);
		
		goon.setAStarPathFinder(aStarPathFinder);
		goon.setLinearPathFinder(linearPathFinder);
	}

	@Test
	public void HearSound() {
		//antes teniamos
		//Goon goon2 = new Goon(new Rectangle(10, 10, 18, 13), null, randArray);
		
		Goon goon2 = new Goon(new Rectangle(10, 10, 18, 13), map, randArray);
		goon2.addNoise(new Noise(goon2.getPosition(), 40, NoiseType.SHOOT));
		goon2.setAStarPathFinder(aStarPathFinder);
		goon2.setLinearPathFinder(linearPathFinder);

		//si lo dejabamos sin el null, no hacia el update
		goon.update();
		
		Assert.assertTrue(goon.getState() == NPCState.SUSPICIOUS);

	}

	
	@Test
	public void NoHearSound() {
		Goon goon3 = new Goon(new Rectangle(100, 100, 18, 13), map, randArray);
		goon3.addNoise(new Noise(goon3.getPosition(), 2, NoiseType.SHOOT));

		goon.update();

		Assert.assertTrue(goon.getState() == NPCState.CALM);

	}
	

	//Dañado si le disparan
	@Test
	public void DamageByBullet(){
		Goon goon4= new Goon(new Rectangle(10, 10, 18, 13), map, randArray);
		//ok, aca como se que el goon4 lo esta viendo para saber si le dispara??
		goon4.shoot();
		goon.dealDamage(1f);
		Assert.assertTrue(goon.isHurt()==true);
	}
	
	@Test
	public void NoDamage(){
		Goon goon5 = new Goon(new Rectangle(10, 10, 18, 13), map, randArray);
		//este goon tendria que estar disparando en otra direccion! Este "goon5" no 
		//tendria que ver a "goon"!
		goon5.shoot();
		Assert.assertFalse(goon.isHurt()==false);
	}
	
	//Si lo ve, tendria que estar alerta
	//Esta deprecated!!!
	@Test
	public void CanWatchGoon(){
		Goon goon6 = new Goon(new Rectangle(6, 12, 18, 13), map, randArray);
		Assert.assertTrue(goon6.canSee(goon.getPosition())==true);
	}

}
