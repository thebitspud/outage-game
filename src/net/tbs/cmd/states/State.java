package net.tbs.cmd.states;

import java.awt.Graphics;

import net.tbs.cmd.main.Manager;

//initializes and indirectly manages the in-game states

public abstract class State {
	protected Manager manager;
	
	private static State active = null;
	
	public State(Manager manager) {
		this.manager = manager;
	}
	
	public static void setState(State state) {
		active = state;
	}
	
	//setting the state
	
	public static State getState() {
		return active;
	}
	
	//getting the state
	
	//constructors
	
	public abstract void init();
	public abstract void update();
	public abstract void render(Graphics g);
}