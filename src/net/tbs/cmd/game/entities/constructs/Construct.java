package net.tbs.cmd.game.entities.constructs;

import java.awt.Graphics;
import java.awt.Graphics2D;

import net.tbs.cmd.game.entities.Entity;
import net.tbs.cmd.main.Manager;

//static world objects that you can interact with

public abstract class Construct extends Entity {
	public Construct(int x, int y, int width, int height, Manager manager) {
		super(x, y, width, height, manager);
	}
	
	public void init() {
		setStats();
	}
	
	public void render(Graphics g, Graphics2D g2d) {

	}
}