package net.tbs.cmd.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

import net.tbs.cmd.assets.Timer;
import net.tbs.cmd.main.Manager;
import net.tbs.cmd.states.Settings;

//the Space Exploration Vehicle that you pilot

public class SEV extends Entity {	
	private boolean doingAction, movedPos, actionQueued, armor;
	private int HP, maxHP, efficiency, direction; //efficiency: battery depletion
	private double speed, actDelBar;
	private Point posDiff; //position difference: L + R = stand still
	
	private Timer actionDelay, navigate, mBuffer; //movement buffer
	
	public SEV(int x, int y, Manager manager) {
		super(x, y, 32, 32, manager);
		
		doingAction = false;
		actDelBar = 0;
		armor = false;
		
		posDiff = new Point(0, 0);
	}
	
	public void init() {
		initGameClasses();
		setStats();
		setRenderPos();
	}
	
	public void setStats() {
		texture = manager.getIAsset().getSEV();
		
		maxHP = 10; HP = maxHP;
		speed = 1.0;
		efficiency = 0;
		
		actionDelay = new Timer((int) (60 - speed));
		navigate = new Timer(32);
		mBuffer = new Timer(10);
	}

	public void tick() {
		//movement
		
		if(doingAction) {
			if(!navigate.isActivated()) move(renderPos, 1);
			else if(!movedPos) {
				move(position, 1);
				stage.checkCollisions();
				setRenderPos();
				
				movedPos = true;
			}
			
			if(actionDelay.isActivated()) {
				doingAction = false;
				movedPos = false;
				
				actionDelay.reset();
				navigate.reset();
			}
		}else getInput();
		
		actDelBar = (double) 100 / actionDelay.getDelay() * actionDelay.getFrame();
		
		HP = Math.min(HP, maxHP); HP = Math.max(HP, 0);
		maxHP = Math.min(maxHP, 100); maxHP = Math.max(maxHP, 1); //setting bounds

		if(HP == 0) {
			game.setDeathCause("Decommissioned by hostiles.");
			game.endGame();
		}
	}
	
	public void getInput() { //movement and action controls
		posDiff.setLocation(0, 0);
		actionQueued = false;

		if(manager.getKeyboard().getKey(0, 37) || manager.getKeyboard().getKey(0, 65)) {
			posDiff.x--;
			actionQueued = true;
		}

		if(manager.getKeyboard().getKey(0, 38) || manager.getKeyboard().getKey(0, 87)) {
			posDiff.y--;
			actionQueued = true;
		}

		if(manager.getKeyboard().getKey(0, 39) || manager.getKeyboard().getKey(0, 68)) {
			posDiff.x++;
			actionQueued = true;
		}

		if(manager.getKeyboard().getKey(0, 40) || manager.getKeyboard().getKey(0, 83)) {
			posDiff.y++;
			actionQueued = true;
		}

		if(!actionQueued) mBuffer.reset();

		if(mBuffer.isActivated()) {
			if(posDiff.equals(new Point(1, -1))) {
				direction = 1; //up + right
				if(position.x < level.getSize().getWidth() - 1 && position.y > 0 &&
						!level.getTile(position.x + 1, position.y - 1).isSolid()) initMovSeq(2);

			}else if(posDiff.equals(new Point(1, 1))) {
				direction = 3; //down + right
				if(position.x < level.getSize().getWidth() - 1
						&& position.y < level.getSize().getHeight() - 1 &&
						!level.getTile(position.x + 1, position.y + 1).isSolid()) initMovSeq(2);

			}else if(posDiff.equals(new Point(-1, 1))) {
				direction = 5; //down + left
				if(position.x > 0 && position.y < level.getSize().getHeight() - 1 &&
						!level.getTile(position.x - 1, position.y + 1).isSolid()) initMovSeq(2);

			}else if(posDiff.equals(new Point(-1, -1))) {
				direction = 7; //up + left
				if(position.x > 0 && position.y > 0 &&
						!level.getTile(position.x - 1, position.y - 1).isSolid()) initMovSeq(2);

			}else if(posDiff.equals(new Point(0, -1))) {
				direction = 0; //up
				if(position.y > 0 && !level.getTile(position.x, position.y - 1).isSolid()) initMovSeq(1);

			}else if(posDiff.equals(new Point(1, 0))) {
				direction = 2; //right
				if(position.x < level.getSize().getWidth() - 1 &&
						!level.getTile(position.x + 1, position.y).isSolid()) initMovSeq(1);

			}else if(posDiff.equals(new Point(0, 1))) {
				direction = 4; //down
				if(position.y < level.getSize().getHeight() - 1 &&
						!level.getTile(position.x, position.y + 1).isSolid()) initMovSeq(1);

			}else if(posDiff.equals(new Point(-1, 0))) {
				direction = 6; //left
				if(position.x > 0 && !level.getTile(position.x - 1, position.y).isSolid()) initMovSeq(1);
			}
		}

		//movement code
		//left arrow = 37; up arrow = 38; right arrow = 39; down arrow = 40
	}
	
	public void initMovSeq(int energy) { //initialize movement sequence		
		doingAction = true;
		game.getStage().getBattery().setQuantity(-energy);
	}
	
	public void resetTimers() {
		actionDelay.setDelay((int) (60 - speed));
		navigate.setDelay(32);
	}
	
	public void move(Point2D pos, double dist) { //move location
		switch(direction) { //eighths from 0
			case 0: //forwards
				changeLocation(pos, 0, -dist);
				
				break;
			case 1: //forwards / right
				changeLocation(pos, dist, -dist);
				
				break;	
			case 2: //right
				changeLocation(pos, dist, 0);
	
				break;
			case 3: //backwards / right
				changeLocation(pos, dist, dist);
				
				break;
			case 4: //backwards
				changeLocation(pos, 0, dist);
	
				break;
			case 5: //backwards / left
				changeLocation(pos, -dist, dist);	
				
				break;	
			case 6: //left
				changeLocation(pos, -dist, 0);
				
				break;
			case 7: //forwards / left
				changeLocation(pos, -dist, -dist);
				
				break;
			default: break;
		}
	}

	public void render(Graphics g, Graphics2D g2d) {
		if(Settings.isShowingEOverlay()) {
			g.setColor(new Color(0, 0, 127));
			
			g.drawRect((position.x * level.getTileSize()) + game.getOffset().x,
					(position.y * level.getTileSize()) + game.getOffset().y,
					level.getTileSize() - 1, level.getTileSize() - 1);
		}
		
		g2d.rotate(Math.toRadians(direction * 45),
				renderPos.getX() + texture.getWidth(), renderPos.getY() + texture.getHeight());
	    g.drawImage(texture, (int) renderPos.getX(), (int) renderPos.getY(), size.width, size.height, null);
	    g2d.rotate(Math.toRadians(-direction * 45),
	    		renderPos.getX() + texture.getWidth(), renderPos.getY() + texture.getHeight());
		
		if(Settings.isShowingActionDelay()) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(797, 548, (int) (104), 24);
			
			g.setColor(new Color(0, 255, 0));
			
			if(doingAction) {
				g.setColor(new Color(255, 0, 0));
				g.fillRect(799, 550, 100, 20);
				
				g.setColor(new Color(0, 255, 0));
				g.fillRect(799, 550, (int) actDelBar, 20);
			}else g.fillRect(799, 550, 100, 20);
		}
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
		
		maxHP = Math.max(maxHP, 1);
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public int getEfficiency() {
		return efficiency;
	}
	
	public void setEfficiency(int efficiency) {
		this.efficiency = efficiency;
	}
	
	public boolean hasArmor() {
		return armor;
	}
	
	public void setArmor(boolean has) {
		armor = has;
	}
}