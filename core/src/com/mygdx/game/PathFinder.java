package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/*
 * Interfaz para un pathFinder. en particular, implementamos AStarPathFinder y
 * linearPathFinder.
 */
public interface PathFinder {
	public Path findPath(Movable movable ,Vector2 startPosition, Vector2 finalPosition);
}
