package net.tbs.cmd.game.upgrades;

import net.tbs.cmd.main.Manager;

public class RepairPurchase extends Upgrade {
	public RepairPurchase(Manager manager) {
		super(true, manager);
	}

	public void init() {
		initGameClasses();
		
		cost = 2;
		tier = 0;
		maxTier = 100;
	}

	public void aquire() {
		stage.getSEV().setHP(1);
		stage.getScrap().setQuantity(-cost);
		
		tier++; maxTier++;
	}
}

//increase the current HP of the SEV by 1