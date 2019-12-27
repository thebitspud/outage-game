package net.tbs.cmd.game.entities.mobs;

import net.tbs.cmd.assets.gfx.Animation;
import net.tbs.cmd.main.Manager;

//ApexFeeder mob - ID 5 - Stronger and more aggressive version of the default Feeder

public class ApexFeeder extends Mob {
	private boolean nearPlayer;
	
	public ApexFeeder(int x, int y, Manager manager) {
		super(x, y, 32, 32, manager);
	}
	
	public void setStats() {
		ID = 5;
		
		texture = manager.getIAsset().getMob()[ID][0];
		walk = new Animation(4, 10);
		nearPlayer = false;
		
		maxHP = 12; HP = maxHP;
		attack = 7;
		speed = 2.5;
		
		points = 17;
		scrap = 4;
		
		setRenderPos();
	}
	
	public void tickAI() {
		action = r.nextInt(200);
		
		//checking the SEV's location
		
		if(position.x >= stage.getSEV().getPosition().x - 7 && position.x <= stage.getSEV().getPosition().x + 7 &&
		position.y >= stage.getSEV().getPosition().y - 7 && position.y <= stage.getSEV().getPosition().y + 7)
			nearPlayer = true;
		else nearPlayer = false;
		
		//very basic pathfinding sequence
		
		if(nearPlayer) {
			speed = 3.0;
			
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