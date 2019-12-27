package net.tbs.cmd.game.entities.mobs;

import net.tbs.cmd.assets.gfx.Animation;
import net.tbs.cmd.main.Manager;

//Roach mob - ID 1 - Fast moving enemy that deals moderate damage

public class Roach extends Mob {
	public Roach(int x, int y, Manager manager) {
		super(x, y, 32, 16, manager);
	}

	public void setStats() {
		ID = 1;
		
		texture = manager.getIAsset().getMob()[ID][0];
		vOffset = 8;
		walk = new Animation(4, 3);
		
		maxHP = 5; HP = maxHP;
		attack = 2;
		speed = 3.0;
		
		points = 5;
		scrap = 1;
		
		setRenderPos();
	}

	public void tickAI() {
		action = r.nextInt(150);

		defaultMoveAI();
	}
}