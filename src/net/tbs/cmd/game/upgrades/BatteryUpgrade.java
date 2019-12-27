package net.tbs.cmd.game.upgrades;

import net.tbs.cmd.main.Manager;

public class BatteryUpgrade extends Upgrade {
	public BatteryUpgrade(Manager manager) {
		super(true, manager);
	}

	public void init() {
		initGameClasses();
		
		cost = 5;
		tier = 0;
		maxTier = 5;
	}

	public void aquire() {
		stage.getSEV().setEfficiency((tier + 1) * 2);
		stage.getScrap().setQuantity(-cost);
		
		tier++;
		cost += 5;
	}
}

//decrease the effects of battery drain on the SEV