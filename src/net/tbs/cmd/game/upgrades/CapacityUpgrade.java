package net.tbs.cmd.game.upgrades;

import net.tbs.cmd.main.Manager;

public class CapacityUpgrade extends Upgrade {
	public CapacityUpgrade(Manager manager) {
		super(true, manager);
	}

	public void init() {
		initGameClasses();
		
		cost = 1;
		tier = 0;
		maxTier = 95;
	}

	public void aquire() {
		stage.getBattery().setMaxQuantity(100);
		
		stage.getScrap().setQuantity(-cost);
		stage.getBPack().setQuantity(-1);
		
		tier++;
		cost = (tier / 10) + 1;
	}
}

//increase the max battery capacity of the SEV