package com.mygdx.game;

public interface TileBasedMap {
	public int getWidthInTiles();
    public int getHeightInTiles();	
    public boolean blocked( int x, int y);	
    public float getCost( int sx, int sy, int tx, int ty);
    public void pathFinderVisited(int x, int y);
}
