package net.tbs.cmd.game.entities.mobs;

import net.tbs.cmd.assets.gfx.Animation;
import net.tbs.cmd.main.Manager;

//Crawler mob - ID 3 - Damage-heavy mutant spider

public class Crawler extends Mob {
	private boolean justMoved;
	
	public Crawler(int x, int y, Manager manager) {
		super(x, y, 32, 32, manager);
	}
	
	public void setStats() {
		ID = 3;
		
		texture = manager.getIAsset().getMob()[ID][0];
		walk = new Animation(4, 8);
		
		maxHP = 5; HP = maxHP;
		attack = 5;
		speed = 2.5;
		
		points = 12;
		scrap = 3;
		
		setRenderPos();
	}

	public void tickAI() {
		if(justMoved) { //increased chances of moving in uninterrupted sequences
			action = r.nextInt(5); justMoved = false;
		}else action = r.nextInt(600);
		
		if(moving) {
			if(moveDelay.isActivated()) {
				level.getTile(position.x, position.y).setContainsMob(false);
				position.setLocation(nextPos);
				
				setRenderPos();
				checkCollisions();
				
				moving = false;
				justMoved = true; //continue moving
				moveDelay.reset();
			}else{
				move(renderPos, speed);
			}
		}else if(action <= 3) checkMovement();
	}
}