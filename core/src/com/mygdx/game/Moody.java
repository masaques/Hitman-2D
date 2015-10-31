package com.mygdx.game;

public interface Moody {
	public void alarm(Context context);
	public void suspicious(Context context);
	public void calm(Context context);
	public void surprised(Context context);
	public void stop();
	public void setState(NPCState state);
	public NPCState getState();
}
