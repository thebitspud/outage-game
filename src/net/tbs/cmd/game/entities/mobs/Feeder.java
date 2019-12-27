package net.tbs.cmd.game.entities.mobs;

import net.tbs.cmd.assets.gfx.Animation;
import net.tbs.cmd.main.Manager;

//Feeder mob - ID 4 - First alien mob encountered

public class Feeder extends Mob {
	private boolean nearPlayer;
	
	public Feeder(int x, int y, Manager manager) {
		super(x, y, 32, 32, manager);
	}
	
	public void setStats() {
		ID = 4;
		
		texture = manager.getIAsset().getMob()[ID][0];
		walk = new Animation(4, 12);
		nearPlayer = false;
		
		maxHP = 8; HP = maxHP;
		attack = 4;
		speed = 2.0;
		
		points = 10;
		scrap = 2;
		
		setRenderPos();
	}
	
	public void tickAI() {
		action = r.nextInt(250);
		
		//checking the SEV's location
		
		if(position.x >= stage.getSEV().getPosition().x - 4 && position.x <= stage.getSEV().getPosition().x + 4 &&
		position.y >= stage.getSEV().getPosition().y - 4 && position.y <= stage.getSEV().getPosition().y + 4)
			nearPlayer = true;
		else nearPlayer = false;
		
		//very basic pathfinding sequence
		
		if(nearPlayer && action < 20) {
			speed = 2.5;
			
			if(position.y > stage.getSEV().getPosition().y &&
			!level.getTile(position.x, position.y - 1).getContainsMob() &&
			!level.getTile(position.x, position.y - 1).isSolid())
				action = 0;
			else if(position.x < stage.getSEV().getPosition().x &&
			!level.getTile(position.x + 1, position.y).getContainsMob() &&
			!level.getTile(position.x + 1, position.y).isSolid())
				action = 1;
			else if(position.y < stage.getSEV().getPosition().y &&
			!level.getTile(position.x, position.y + 1).getContainsMob() &&
			!level.getTile(position.x, position.y + 1).isSolid())
				action = 2;
			else if(position.x > stage.getSEV().getPosition().x &&
			!level.getTile(position.x - 1, position.y).getContainsMob() &&
			!level.getTile(position.x - 1, position.y).isSolid())
				action = 3;
		}else speed = 1.2;
		
		defaultMoveAI();
	}
}