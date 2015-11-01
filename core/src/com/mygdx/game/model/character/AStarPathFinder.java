package com.mygdx.game.model.character;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.LevelMap;
import com.mygdx.game.model.util.Movable;

/**
 * Pathfinder que usa el algoritmo de A*. Implementa {@link PathFinder}
 * @author traies
 */

public class AStarPathFinder implements PathFinder {
	private ArrayList<Node> closed = new ArrayList<Node>();
	private ArrayList<Node> open = new ArrayList<Node>();
	private Node[][] nodes;
	private LevelMap map;
	private int maxSearchDistance;
	
	/**
	 * @param map - {@link LevelMap} que incluye las paredes
	 * @param maxSearchDistance - Distancia máxima a buscar
	 * <p></p>
	 * @see LevelMap
	 */
	
	public AStarPathFinder(LevelMap map, int maxSearchDistance){
		this.map = map;
		this.maxSearchDistance = maxSearchDistance;
		nodes = new Node[map.getWidthInTiles()][map.getHeightInTiles()];
		for (int x=0; x < map.getWidthInTiles();x++){
			for (int y = 0; y< map.getHeightInTiles();y++){
				nodes[x][y] = new Node(x,y);
			}
		}
		
	}
	
	/**
	 * 
	 * @param movable - Objeto a mover a través del {@link Path}
	 * @param startPosition - Posición inicial
	 * @param finalPosition - Posición final
	 * 
	 * @return {@link Path}
	 */
	@Override
	public Path findPath(Movable movable, Vector2 startPosition, Vector2 finalPosition) {
		
		int sx = Math.round(startPosition.x) / map.getTileWidth();
	    int sy = Math.round(startPosition.y) / map.getTileWidth();
	    int tx = Math.round(finalPosition.x) / map.getTileWidth();
	    int ty = Math.round(finalPosition.y) / map.getTileWidth();
	    
	    if (map.blocked( finalPosition) || (sx == tx && sy == ty)) {
		      return null;
	    }
	    
	    cleanNodes();
	    
	    nodes[sx][sy].cost = 0 + manhattanDistance(sx,sy,tx,ty);
	    closed.clear();
	    open.clear();
	    open.add(nodes[sx][sy]);
	    int maxDepth = 0;

	    while ( maxDepth < maxSearchDistance && open.size() != 0){
	    	Collections.sort(open);
	    	Node current = open.get(0);
	        
	        if (current == nodes[tx][ty]){
	          break;
	        }
	        open.remove(current);
	        closed.add(current);
	        
	        for (int x=-1; x<2;x++) {
	          for (int y = -1; y<2; y++) {
	            if (x==0 && y==0) {
	              continue;
	            }
	            if (x!=0 && y!=0){
	            	continue;
	            }
	            int xp = x+current.x;
	            int yp = y+current.y;
	            
	            Vector2 currPosition = new Vector2(xp * map.getTileWidth(),yp * map.getTileWidth());
	            
	            if (!map.blocked(currPosition)) {
	              float nextStepCost = current.cost + map.getCost(current.x, current.y, xp,yp) + manhattanDistance(current.x,current.y,xp,yp);
	              Node neighbour = nodes[xp][yp];
	              if (nextStepCost < neighbour.cost) {
	                if (open.contains(neighbour)) {
	                  open.remove(neighbour);
	                }
	                if (closed.contains(neighbour)) {
	                  closed.remove(neighbour); 
	                }
	              }
	              if (!open.contains(neighbour) && !(closed.contains(neighbour))){
	                neighbour.cost = nextStepCost;
	                maxDepth = Math.max(maxDepth, neighbour.setParent(current));
	                open.add(neighbour);
	                Collections.sort(open);
	              }
	            }
	          }
	        }
	    }
	    if (nodes[tx][ty].parent == null) {
	    	return null;
	    }
	    Path path = new Path();
	    
	    Node target = nodes[tx][ty];
	    Vector2 stepPosition = new Vector2();
	    
	    while (target != nodes[sx][sy]){
	    	stepPosition = getStepCorrection(movable,target);
	    	path.prependStep(stepPosition);
	    	target = target.getParent(); 	
	    }
	    return path;  
	}
	
	/**
	 *  Metodo privado que sirve para corregir la posición de los nodos. Necesita una 
	 *  referencia al objeto que se quiere mover.
	 *  
	 *  @param movable - Objeto a mover
	 *  @param node - Nodo a corregir
	 */
	
	private Vector2 getStepCorrection(Movable movable, Node node) {
		float tileWidth = map.getTileWidth();
		float xPosition = node.getX() * tileWidth + tileWidth / 2f - movable.getWidth() / 2;
		float yPosition = node.getY() * tileWidth + tileWidth / 2f - movable.getHeight() / 2; 
		return new Vector2( xPosition, yPosition );
	}
	
	/**
	 * Método que calcula la distancia entre 2 puntos.
	 * 
	 * 	@param x - x origen
	 * 	@param y - y origen
	 * 	@param xp - x destino
	 * 	@param yp - y destino
	*/
	private int manhattanDistance(int x, int y, int xp, int yp){
		return Math.abs(xp - x) + Math.abs(yp -y);
	}
	
	/**
	 * Limpia los nodos para que no genere conflictos con la ejecucion pasada.
	 * Conviene eliminar el metodo en una futura revision y que el algoritmo mismo
	 * los vaya eliminando a medida que no los necesita mas, para no tener que recorrer
	 * el array entero.
	 */
	private void cleanNodes(){
		for(Node[] arr:nodes){
			for(Node n: arr){
				n.looseParent();
			}
		}
	}
	/**
	 * 	Clase privada. Modela los nodos posibles que representan el mapa navegable. El compareTo
	 * 	funciona con el costo.
	 */
	private class Node implements Comparable <Node>{
		private int x;
		private int y;
		private float cost;
		private Node parent = null;
		private int depth;
		
		public Node(int x,int y){
			this.x = x;
			this.y = y;
		}
		public int setParent(Node parent) {
			depth = parent.depth + 1;
			this.parent = parent;
			return depth;
		}
		public Node getParent(){
			return this.parent;
		}
		public void looseParent(){
			this.parent = null;
			this.depth = 0;
		}
		public int compareTo(Node other) {
			if (cost < other.cost){
				return -1;
			} else if ( cost > other.cost){
				return 1;
			} else {
				return 0;
			}
		}
		public boolean equals(Object o){
			if (o.getClass() != Node.class){
				return false;
			}
			Node other = (Node) o;
			if (other.x != x || other.y != y){
				return false;
			}
			return true;
		}
		public int hashCode(){
			return x ^ y;
		}
		public int getX(){
			return x;
		}
		public int getY(){
			return y;
		}
		public String toString(){
			return "x :" + x + " y: " + y;
		}
	}
	
}
