package com.mygdx.game.model;

import java.io.Serializable;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.message.Bullet;
import com.mygdx.game.model.util.RandList;

/**
 * Representa el mapa del modelo, es usado para chequear las colisiones. Se
 * inicializa a partir de un {@link TileMap} en la cual en el Layer 1 se
 * encuentran las paredes.
 * 
 * @see TiledMap
 */
public class LevelMap implements Serializable {

	private int width;
	private int height;
	private int widthInTiles;
	private int heightInTiles;
	private int tileWidth;
	private Rectangle[][] modelMap;

	public LevelMap(int width, int height, int tileWidth, TiledMap tiledMap) {
		this.width = width;
		this.height = height;
		this.tileWidth = tileWidth;
		this.widthInTiles = width / tileWidth;
		this.heightInTiles = height / tileWidth;
		TiledMapTileLayer foreground = (TiledMapTileLayer) tiledMap.getLayers().get(1);
		this.modelMap = new Rectangle[widthInTiles][heightInTiles];

		for (int x = 0; x < this.widthInTiles; x++) {
			for (int y = 0; y < this.heightInTiles; y++) {
				if (foreground.getCell(x, y) == null) {
					modelMap[x][y] = null;
				} else {
					modelMap[x][y] = new Rectangle(x * tileWidth, y * tileWidth, tileWidth, tileWidth);
				}
			}
		}
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public int getWidthInTiles() {
		return widthInTiles;
	}

	public int getHeightInTiles() {
		return heightInTiles;
	}

	/**
	 * Revisa que un posición no se encuentre adentro de una pared
	 * 
	 * @param position
	 *            - Posición a chequear
	 */
	public boolean blocked(Vector2 position) {
		float x, y;
		x = position.x;
		y = position.y;

		if (x < 0 || (y < 0) || x >= width || y >= height) {
			return true;
		}
		int xp = (int) x / tileWidth;
		int yp = (int) y / tileWidth;
		return modelMap[xp][yp] != null;
	}

	public float getCost(int sx, int sy, int tx, int ty) {
		if (sx != tx && sy != ty) {
			return 1.41f;
		}
		return 1f;
	}

	/**
	 * El metodo que recorre el mapa a ver si encuentra un rectangulo con el que
	 * el hitBox colisiona.
	 * 
	 * @param hitbox
	 *            - Rectangulo al que se le va a chequear las colisiones
	 */
	public boolean isValid(Rectangle hitBox) {
		for (int x = 0; x < widthInTiles; x++) {
			for (int y = 0; y < heightInTiles; y++) {
				if (modelMap[x][y] != null && modelMap[x][y].overlaps(hitBox)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * El metodo que recorre el mapa a ver si el segmento colisiona.
	 * 
	 * @param startPosition
	 *            - Posicion inicial del vector
	 * @param finalPosition
	 *            - Posicion final del vector
	 */
	public boolean isValid(Vector2 startPosition, Vector2 finalPosition) {
		for (int x = 0; x < widthInTiles; x++) {
			for (int y = 0; y < heightInTiles; y++) {
				if (modelMap[x][y] != null
						&& rectangleSegmentIntersects(modelMap[x][y], startPosition, finalPosition)) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean rectangleSegmentIntersects(Rectangle hitBox, Vector2 startPos, Vector2 finalPos) {
		Vector2[] vortexs = new Vector2[4];
		vortexs[0] = hitBox.getPosition(new Vector2());
		vortexs[1] = hitBox.getPosition(new Vector2()).add(0, hitBox.width);
		vortexs[2] = hitBox.getPosition(new Vector2()).add(hitBox.height, hitBox.width);
		vortexs[3] = hitBox.getPosition(new Vector2()).add(hitBox.height, 0);

		for (int i = 0; i < 3; i++) {
			if (Intersector.intersectSegments(startPos, finalPos, vortexs[i], vortexs[i + 1], null)) {
				return true;
			}
		}
		if (Intersector.intersectSegments(startPos, finalPos, vortexs[3], vortexs[0], null)) {
			return true;
		}
		return false;
	}

	public List<Vector2> findRandomValidPositions(Vector2 centerPosition, int tileRadius) {
		List<Vector2> randList = new RandList<Vector2>();

		int xp = (int) centerPosition.x / tileWidth;
		int yp = (int) centerPosition.y / tileWidth;

		for (int i = xp - tileRadius; i < xp + tileRadius && i < getWidthInTiles(); i++) {
			if (i < 0) {
				continue;
			}
			for (int j = yp - tileRadius; j < yp + tileRadius && j < getHeightInTiles(); j++) {
				if (j < 0) {
					continue;
				}
				if (modelMap[i][j] == null) {
					randList.add(new Vector2(i * tileWidth, j * tileWidth));
				}
			}
		}

		return randList;
	}

	public Rectangle avoidanceDetection(Rectangle hitBox, Vector2 ahead, Vector2 ahead2) {
		Rectangle maxObstacle = null;
		float maxDistance = 0f;
		Vector2 position = hitBox.getCenter(new Vector2());
		for (int i = 0; i < getWidthInTiles(); i++) {
			for (int j = 0; j < getHeightInTiles(); j++) {
				Rectangle obstacle = modelMap[i][j];
				if (obstacle == null) {
					continue;
				}
				if (obstacle.overlaps(hitBox) || obstacle.contains(ahead) || obstacle.contains(ahead2)) {
					float distance = obstacle.getCenter(new Vector2()).dst(position);
					if (maxDistance < distance) {
						maxDistance = distance;
						maxObstacle = obstacle;
					}
				}
			}
		}
		return maxObstacle;
	}

	public Vector2 getBulletCollision(Bullet bullet) {

		float minDistance = bullet.getRange();
		Vector2 collision = new Vector2();
		for (int i = 0; i < getWidthInTiles(); i++) {
			for (int j = 0; j < getHeightInTiles(); j++) {
				if (modelMap[i][j] == null) {
					continue;
				}

				Vector2 intersection = bullet.intersects(modelMap[i][j]);
				float distance = intersection.dst(bullet.getPosition());
				if (!intersection.isZero() && distance < minDistance) {
					minDistance = distance;
					collision = intersection;
				}
			}
		}
		return collision;

	}

}
