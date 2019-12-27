package net.tbs.cmd.game.entities.items;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import net.tbs.cmd.game.entities.Entity;
import net.tbs.cmd.main.Manager;

//pickups that you can obtain from the world

public abstract class Item extends Entity {
	protected boolean inited;
	
	public Item(int x, int y, int width, int height, Manager manager) {
		super(x, y, width, height, manager);
		
		inited = false;
	}
	
	public void init() {
		if(!inited) {
			initGameClasses();
		
			setStats();
			spawn();
			
			inited = true;
		}
	}
	
	//spawning
	
	public boolean canSpawn(Point p) {
		if(level.getTile(p.x, p.y).getContainsItem() || p.equals(stage.getSEV().getPosition()) ||
				level.getTile(p.x, p.y).isSolid()) return false;
		else return true;
	}
	
	public void spawn() {
		do position.setLocation(getRandomLocation());
		while(!canSpawn(position));
		
		level.getTile(position.x, position.y).setContainsItem(true);
		setRenderPos();
	}
	
	public void checkCollisions() {
		if(getPosition().equals(stage.getSEV().getPosition())) {
			obtainItem();
			level.getTile(position.x, position.y).setContainsItem(false);
			
			spawn();
		}
		
	}
	
	public abstract void obtainItem();
	
	public void render(Graphics g, Graphics2D g2d) {
		g.drawImage(texture, (int) renderPos.getX(), (int) renderPos.getY(), size.width, size.height, null);
	}
}