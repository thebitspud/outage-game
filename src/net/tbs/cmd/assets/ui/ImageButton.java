package net.tbs.cmd.assets.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import net.tbs.cmd.main.Manager;

//a user interface button with multiple easily accessed functions

public class ImageButton {
	private Manager manager;
	
	private Rectangle bounds;
	private BufferedImage sprite[] = new BufferedImage[3];
	
	//bounds = button dimensions
	//different sprites for button states

	//initializing the button
	
	public ImageButton(int x, int y, int width, int height, BufferedImage[] sprite, Manager manager) {
		bounds = new Rectangle(x, y, width, height);
		
		for(int i = 0; i < 3; i++) this.sprite[i] = sprite[i];
		this.manager = manager;
	}
	
	//checking for when the button is clicked
	
	public boolean clicked() {	
		if(bounds.contains(manager.getMouse().getLocation()))
			if(manager.getMouse().getLeft(1)) return true;
			
		return false;
	}
	
	public boolean pressed() {	
		if(bounds.contains(manager.getMouse().getLocation()))
			if(manager.getMouse().getLeft(0)) return true;
			
		return false;
	}
	
	//checking if the button is being hovered over
	
	public boolean covered() {
		if(bounds.contains(manager.getMouse().getLocation())) return true;
		
		return false;
	}
	
	//drawing out the button according to the x, y, width, and height variables
	
	public void render(Graphics g) {
		if (pressed()) g.drawImage(sprite[2], bounds.x, bounds.y, bounds.width, bounds.height, null);
		else if(covered()) g.drawImage(sprite[1], bounds.x, bounds.y, bounds.width, bounds.height, null);
		else g.drawImage(sprite[0], bounds.x, bounds.y, bounds.width, bounds.height, null);
	}
	
	//getters and setters

	public Rectangle getBounds() {
		return bounds;
	}
}