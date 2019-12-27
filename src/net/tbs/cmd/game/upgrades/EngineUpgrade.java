package net.tbs.cmd.game.upgrades;

import net.tbs.cmd.main.Manager;

public class EngineUpgrade extends Upgrade {
	public EngineUpgrade(Manager manager) {
		super(true, manager);
	}

	public void init() {
		initGameClasses();
		
		cost = 3;
		tier = 0;
		maxTier = 5;
	}
	
	public void aquire() {
		tier++;
		
		stage.getSEV().setSpeed(1 + tier);
		stage.getScrap().setQuantity(-cost);
		stage.getSEV().resetTimers();
		
		cost += 1 + (tier * 2);
	}
}