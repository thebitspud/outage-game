package net.tbs.cmd.game.resources;

import net.tbs.cmd.assets.Timer;
import net.tbs.cmd.main.Manager;

//a primary depleting resource that must be managed at all times

public class Battery extends Resource {
	private Timer deplete;
	private double drain;
	
	public Battery(int quantity, int maxQuantity, Manager manager) {
		super(quantity, maxQuantity, manager);
		
		deplete = new Timer(1);
	}
	
	public void init() {
		stage = manager.getGame().getStage();
	}

	public void tick() {
		if(deplete.isActivated()) {
			//battery drainage scaling with level
			
			if(stage.getDifficulty() >= 4)
				drain = Math.max(0.15 * stage.getDifficulty() / (5 + stage.getSEV().getEfficiency()), 0.01);
			else drain = Math.max(0.1 * stage.getDifficulty() / (5 + stage.getSEV().getEfficiency()), 0.01);
			
			quantity -= drain;
			
			deplete.reset();
		}
		
		quantity = Math.min(quantity, maxQuantity); quantity = Math.max(quantity, 0);
		maxQuantity = Math.min(maxQuantity, 10000); maxQuantity = Math.max(maxQuantity, 1); //setting bounds
		
		if(quantity == 0) {
			manager.getGame().setDeathCause("Ran out of battery.");
			manager.getGame().endGame();
		}
	}
}