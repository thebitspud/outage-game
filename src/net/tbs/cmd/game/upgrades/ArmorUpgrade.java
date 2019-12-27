package net.tbs.cmd.game.upgrades;

import net.tbs.cmd.main.Manager;

//mitigates damage taken from all sources by 1

public class ArmorUpgrade extends Upgrade {
	public ArmorUpgrade(Manager manager) {
		super(false, manager);
	}

	public void init() {
		initGameClasses();
		
		cost = 30;
		tier = 0;
		maxTier = 1;
	}

	public void aquire() {
		stage.getScrap().setQuantity(-cost);
		stage.getSEV().setArmor(true);
		
		tier++;
	}
}