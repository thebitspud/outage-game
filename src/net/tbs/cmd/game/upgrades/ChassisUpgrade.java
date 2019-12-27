package net.tbs.cmd.game.upgrades;

import net.tbs.cmd.main.Manager;

public class ChassisUpgrade extends Upgrade {
	public ChassisUpgrade(Manager manager) {
		super(true, manager);
	}

	public void init() {
		initGameClasses();
		
		cost = 1;
		tier = 0;
		maxTier = 90;
	}

	public void aquire() {
		stage.getSEV().setMaxHP(1);
		
		stage.getScrap().setQuantity(-cost);
		stage.getUKit().setQuantity(-1);
		
		tier++;
		cost = (tier / 5) + 1;
	}
}

//increase the max HP of the SEV