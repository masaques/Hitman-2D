package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

public class Inbox<M> {
	private List<M> inboxList;
	
	public Inbox() {
		inboxList = new ArrayList<M>();
	}
	public void add(M m) {
		if (m == null) {
			System.out.println("algo");
		}
		inboxList.add(m);
	}
	public List<M> get() {
		List<M> aux = inboxList;
		inboxList = new ArrayList<M>();
		return aux;
	}
	
}
