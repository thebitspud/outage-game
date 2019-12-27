package net.tbs.cmd.game.upgrades;

import net.tbs.cmd.main.Manager;

public class StorageUpgrade extends Upgrade {
	public StorageUpgrade(Manager manager) {
		super(true, manager);
	}

	public void init() {
		initGameClasses();
		
		cost = 3;
		tier = 0;
		maxTier = 5;
	}

	public void aquire() {
		stage.getScrap().setMaxQuantity((tier * 2) + 3);
		stage.getUKit().setMaxQuantity((tier / 2) + 1);
		stage.getBPack().setMaxQuantity((tier / 2) + 1);
		
		stage.getScrap().setQuantity(-cost);
		
		tier++;
		cost += 4;
	}
}