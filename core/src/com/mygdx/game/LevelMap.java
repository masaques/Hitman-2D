package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class LevelMap {
	
	private int width;
	private int height;
	private int widthInTiles;
	private int heightInTiles;
	private int tileWidth;
	private Rectangle[][] modelMap;
	
	public LevelMap(int width, int height,int tileWidth, TiledMap tiledMap){
		this.width = width;
		this.height = height;
		this.tileWidth = tileWidth;
		this.widthInTiles = width / tileWidth;
		this.heightInTiles = height / tileWidth;
		TiledMapTileLayer foreground =  (TiledMapTileLayer)tiledMap.getLayers().get(1);
		this.modelMap = new Rectangle[widthInTiles][heightInTiles];
		
		for (int x = 0; x < this.widthInTiles; x++) {
			for (int y = 0; y < this.heightInTiles ; y++) {
				if (foreground.getCell(x, y) == null) {
					modelMap[x][y] = null;
				}
				else {
					modelMap[x][y] = new Rectangle(x * tileWidth, y * tileWidth, tileWidth,tileWidth);
				}
			}
		}
		
		//level_array[5][10] = true;
	}
	public int getTileWidth(){
		return tileWidth;
	}
	
	public int getWidthInTiles() {
		return widthInTiles;
	}

	
	public int getHeightInTiles() {
		return heightInTiles;
	}

	
	public boolean blocked( Vector2 position) {
		float x,y;
		x = position.x;
		y = position.y;
		
		if (x < 0 || (y < 0) || x >= width || y >= height){
			return true;
		}
		int xp = (int)x / tileWidth;
		int yp = (int)y / tileWidth;
		return modelMap[xp][yp] != null;
	}

	
	public float getCost(int sx, int sy, int tx, int ty) {
		if (sx != tx && sy != ty){
			return 1.41f;
		}
		return 1f;
	}
	/*
	 * El metodo recorre todo el array del mapa a ver si encuentra un rectangulo
	 * con el que el hitBox colisiona.
	 */
	public boolean isValid(Rectangle hitBox) {
		for (int x = 0; x < widthInTiles; x++ ) {
			for (int y = 0; y < heightInTiles; y++) {
				if (modelMap[x][y] != null && modelMap[x][y].overlaps(hitBox)) {
					return false;
				}
			}
		}
		return true;
	}
	/*
	 * Lo mismo que lo anterior para un segmento.
	 */
	public boolean isValid(Vector2 startPosition, Vector2 finalPosition ) {
		for (int x = 0; x < widthInTiles; x++ ) {
			for (int y = 0; y < heightInTiles; y++) {
				if (modelMap[x][y] != null && rectangleSegmentIntersects(modelMap[x][y], startPosition, finalPosition)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean rectangleSegmentIntersects(Rectangle hitBox, Vector2 startPos, Vector2 finalPos) {
		Vector2[] vortexs = new Vector2[4];
		vortexs[0] = hitBox.getPosition(new Vector2());
		vortexs[1] = hitBox.getPosition(new Vector2()).add(0,hitBox.width);
		vortexs[2] = hitBox.getPosition(new Vector2()).add(hitBox.height, hitBox.width);
		vortexs[3] = hitBox.getPosition(new Vector2()).add(hitBox.height, 0);
		
		for (int i=0; i<3; i++) {
			if (Intersector.intersectSegments(startPos, finalPos, vortexs[i], vortexs[i+1],null)) {
				return true;
			}
		}
		if (Intersector.intersectSegments(startPos, finalPos, vortexs[3], vortexs[0],null)) {
			return true;
		}
		return false;
		
	}
	
}
