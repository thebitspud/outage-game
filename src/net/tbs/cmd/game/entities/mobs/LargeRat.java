package net.tbs.cmd.game.entities.mobs;

import net.tbs.cmd.assets.gfx.Animation;
import net.tbs.cmd.main.Manager;

//LargeRat mob - ID 0 - First mob added

public class LargeRat extends Mob {
	public LargeRat(int x, int y, Manager manager) {
		super(x, y, 32, 16, manager);
	}

	public void setStats() {
		ID = 0;
		
		texture = manager.getIAsset().getMob()[ID][0];
		vOffset = 8;
		walk = new Animation(4, 20);
		
		maxHP = 3; HP = maxHP;
		attack = 1;
		speed = 1.5;
		
		points = 2;
		scrap = 1;
		
		setRenderPos();
	}

	public void tickAI() {
		action = r.nextInt(400); //movement at random intervals
		
		defaultMoveAI();
	}
}