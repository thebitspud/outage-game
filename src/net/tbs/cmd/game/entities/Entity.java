package net.tbs.cmd.game.entities;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import net.tbs.cmd.game.Game;
import net.tbs.cmd.game.map.Level;
import net.tbs.cmd.game.map.Stage;
import net.tbs.cmd.main.Manager;

//any non-terrain object in the game world that can be interacted with

public abstract class Entity {
	protected Manager manager;
	protected Game game;
	protected Level level;
	protected Stage stage;
	
	protected Point position, randomLocation; //tile location / render position
	protected Point2D renderPos;
	protected Dimension size;
	
	protected boolean active;
	
	protected int ID, vOffset;
	protected BufferedImage texture;
	
	protected Random r;
	
	//giving all entities separate IDs and sprites
	//IDs - 0 - 99: mobs; 100 - 199: items; 200 - 299: constructs
	
	public Entity(int x, int y, int width, int height, Manager manager) {
		this.manager = manager;
		
		this.position = new Point(x, y);
		this.size = new Dimension(width, height);
		
		active = true;
		
		r = new Random();
		randomLocation = new Point(0, 0);
		renderPos = new Point2D.Double(0, 0);
	}
	
	public void initGameClasses() {
		game = manager.getGame();
		
		level = game.getLevel();
		stage = game.getStage();
	}
	
	//constructors
	
	public abstract void init();
	public abstract void setStats();
	public abstract void render(Graphics g, Graphics2D g2d);
	
	public void despawn() {
		active = false;
	}
	
	public void changeLocation(Point2D p, double x, double y) {
		p.setLocation(p.getX() + x, p.getY() + y);
	}

	//getters and setters
	
	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
	public void setRenderPos() {
		renderPos.setLocation((position.getX() * level.getTileSize()) + game.getOffset().getX(),
				(position.getY() * level.getTileSize()) + game.getOffset().getY() + vOffset);
	}

	public Dimension getSize() {
		return size;
	}
	
	public void setSize(Dimension size) {
		this.size = size;
	}
	
	public Point getRandomLocation() {
		randomLocation.x = (r.nextInt((int) level.getSize().getWidth()));
		randomLocation.y = (r.nextInt((int) level.getSize().getHeight()));
		
		return randomLocation;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}