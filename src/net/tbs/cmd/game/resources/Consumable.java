package net.tbs.cmd.game.resources;

import net.tbs.cmd.main.Manager;

//the class that acts as a basis for collected special resources

public class Consumable extends Resource {
	public Consumable(double quantity, double maxQuantity, Manager manager) {
		super(quantity, maxQuantity, manager);
	}
	
	public void init() {}

	public void tick() {
		quantity = Math.min(quantity, maxQuantity); quantity = Math.max(quantity, 0);
		maxQuantity = Math.max(maxQuantity, 1); //setting bounds
	}
}
