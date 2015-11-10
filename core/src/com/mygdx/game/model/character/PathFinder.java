package com.mygdx.game.model.character;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.util.Movable;

/*
 * Interfaz para un pathFinder. en particular, implementamos AStarPathFinder y
 * linearPathFinder.
 */
public interface PathFinder {
	public Path findPath(Movable movable, Vector2 startPosition, Vector2 finalPosition);
}
