package net.tbs.cmd.game.resources;

import net.tbs.cmd.game.map.Stage;
import net.tbs.cmd.main.Manager;

//any supply of material that must be managed

public abstract class Resource {
	protected Manager manager;
	protected Stage stage;
	
	protected double quantity, maxQuantity;
	
	public Resource(double quantity, double maxQuantity, Manager manager) {
		this.quantity = quantity;
		this.maxQuantity = maxQuantity;
		this.manager = manager;
	}
	
	//constructors
	
	public abstract void init();
	
	public abstract void tick();
	
	//getters and setters
	
	public double getQuantity() {
		return quantity;
	}
	
	public void setQuantity(double quantity) {
		this.quantity += quantity;
	}

	public double getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(double quantity) {
		this.maxQuantity += quantity;
	}
}