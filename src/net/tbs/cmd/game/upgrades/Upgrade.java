package net.tbs.cmd.game.upgrades;

import net.tbs.cmd.game.map.Stage;
import net.tbs.cmd.main.Manager;

//purchases that can be made to augment the SEV

public abstract class Upgrade {
	protected Manager manager;
	protected Stage stage;
	
	protected int cost, tier, maxTier;
	protected boolean purchased, stackable;
	
	public Upgrade(boolean stackable, Manager manager) {
		this.manager = manager;
		this.stackable = stackable;
		
		purchased = false;
	}
	
	public abstract void init();
	
	public void initGameClasses() {
		stage = manager.getGame().getStage();
	}
	
	public void purchase() {
		if(!purchased) {
			if(tier < maxTier)
				if(stage.getScrap().getQuantity() >= cost) aquire();
		
			if(tier >= maxTier) setPurchased(true);
		}
	}
	
	public abstract void aquire();
	
	//getters and setters

	public boolean isPurchased() {
		return purchased;
	}

	public void setPurchased(boolean purchased) {
		this.purchased = purchased;
	}

	public int getCost() {
		return cost;
	}

	public int getTier() {
		return tier;
	}
	
	public int getMaxTier() {
		return maxTier;
	}
}