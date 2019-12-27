package net.tbs.cmd.game.entities.mobs;

import net.tbs.cmd.assets.gfx.Animation;
import net.tbs.cmd.main.Manager;

//MutantRat mob - ID 2 - A stronger LargeRat

public class MutantRat extends Mob {
	public MutantRat(int x, int y, Manager manager) {
		super(x, y, 32, 16, manager);
	}

	public void setStats() {
		ID = 2;
		
		texture = manager.getIAsset().getMob()[ID][0];
		vOffset = 8;
		walk = new Animation(4, 15);
		
		maxHP = 10; HP = maxHP;
		attack = 3;
		speed = 2.0;
		
		points = 7;
		scrap = 2;
		
		setRenderPos();
	}

	public void tickAI() {
		action = r.nextInt(200);
		
		defaultMoveAI();
	}
}