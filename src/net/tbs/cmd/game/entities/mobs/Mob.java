package net.tbs.cmd.game.entities.mobs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import net.tbs.cmd.assets.Timer;
import net.tbs.cmd.assets.gfx.Animation;
import net.tbs.cmd.game.entities.Entity;
import net.tbs.cmd.main.Manager;

//any mobile entity that can be interacted with

public abstract class Mob extends Entity {
	protected int HP, maxHP, attack, mobNum, action, points, scrap;
	protected double speed;
	private int direction; //split into eigths clockwise from 0
	protected Point nextPos; //the mob's designated position
	
	protected Animation walk;
	protected Timer moveDelay;
	protected boolean moving, inited;
	
	private AffineTransform at;
	
	public Mob(int x, int y, int width, int height, Manager manager) {
		super(x, y, width, height, manager);
		
		inited = false;
	}
	
	public void init() {
		if(!inited) {
			initGameClasses();
			
			nextPos = new Point(position.x, position.y);
			direction = r.nextInt(4) * 2;
			
			spawn();
			setStats();
			moveDelay = new Timer((int) (32 / speed));
			
			inited = true;
		}
	}
	
	public void tick() {
		//animated texture
		if(walk.isActivated()) texture = manager.getIAsset().getMob()[ID][walk.getFrame()];
		
		tickAI();
	}
	
	//spawning
	
	public boolean canSpawn(Point p) {
		if(level.getTile(p.x, p.y).getContainsMob() || p.equals(stage.getSEV().getPosition()) ||
				level.getTile(p.x, p.y).isSolid()) return false;
		else return true;
	}
	
	public void spawn() {
		while(!canSpawn(position)) {
			position.setLocation(getRandomLocation());
		}
		
		level.getTile(position.x, position.y).setContainsMob(true);
		setRenderPos();
	}
	
	//collision detection
	
	public void checkCollisions() {
		if(getPosition().equals(stage.getSEV().getPosition())) {
			stage.getSEV().setHP(-attack);
			if(stage.getSEV().hasArmor()) stage.getSEV().setHP(1);
			game.getScore().setPoints(points);
			
			stage.getScrap().setQuantity(scrap);
			
			level.getTile(position.x, position.y).setContainsMob(false); //resetting hitboxes before respawning
			level.getTile(nextPos.x, nextPos.y).setContainsMob(false);
			
			spawn();

			nextPos.setLocation(position.x, position.y);
			moveDelay.reset();
		}
	}
	
	public void defaultMoveAI() {
		if(moving) {
			if(moveDelay.isActivated()) {
				level.getTile(position.x, position.y).setContainsMob(false);
				position.setLocation(nextPos);
				
				setRenderPos();
				checkCollisions();
				
				moving = false;
				moveDelay.reset();
			}else{
				move(renderPos, speed);
			}
		}else if(action <= 3) checkMovement();
	}
	
	public void checkMovement() {
		if(!moving) {
			direction = action * 2;
			
			if(action == 0 && position.y > 0 &&
			!level.getTile(position.x, position.y - 1).getContainsMob() &&
			!level.getTile(position.x, position.y - 1).isSolid()) {
				
				beginMoveSequence();
			} //move up
			
			else if(action == 1 && position.x < level.getSize().getWidth() - 1 &&
			!level.getTile(position.x + 1, position.y).getContainsMob() &&
			!level.getTile(position.x + 1, position.y).isSolid()) {
				
				beginMoveSequence();
			} //move right
			
			else if(action == 2 && position.y < level.getSize().getHeight() - 1 &&
			!level.getTile(position.x, position.y + 1).getContainsMob() &&
			!level.getTile(position.x, position.y + 1).isSolid()) {
				
				beginMoveSequence();
			} //move down
			
			else if(action == 3 && position.x > 0 &&
			!level.getTile(position.x - 1, position.y).getContainsMob() &&
			!level.getTile(position.x - 1, position.y).isSolid()) {
				
				beginMoveSequence();
			} //move left
		}
	}
	
	public void beginMoveSequence() {
		move(nextPos, 1);
		level.getTile(nextPos.x, nextPos.y).setContainsMob(true);
		
		moving = true;
	}
	
	public abstract void tickAI();
	
	public void move(Point2D pos, double dist) {
		switch(direction) {
			case 0: //forwards
				changeLocation(pos, 0, -dist);
			
				break;	
			case 2: //right
				changeLocation(pos, dist, 0);

				break;	
			case 4: //backwards
				changeLocation(pos, 0, dist);
			
				break;
			case 6: //left
				changeLocation(pos, -dist, 0);
			
				break;
			default: break;
		}
	}
	
	public void render(Graphics g, Graphics2D g2d) {
		g2d.rotate(Math.toRadians(direction * 45 - 90),
				renderPos.getX() + texture.getWidth(), renderPos.getY() + texture.getHeight());
	    g.drawImage(texture, (int) renderPos.getX(), (int) renderPos.getY(), size.width,size.height, null);
	    g2d.rotate(Math.toRadians(-direction * 45 + 90),
	    		renderPos.getX() + texture.getWidth(), renderPos.getY() + texture.getHeight());
	    
	    g2d.drawImage(texture, at, null);
	}
	
	//getters and setters

	public int getHP() {
		return HP;
	}

	public void setHP(int HP) {
		this.HP += HP;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int HP) {
		this.maxHP += HP;
		this.HP += HP;

		maxHP = Math.max(maxHP, 1);
	}
}