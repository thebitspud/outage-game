package net.tbs.cmd.assets.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import net.tbs.cmd.main.Manager;

//a user interface button with multiple easily accessed functions

public class TextButton {
	private Manager manager;
	
	private Rectangle bounds;
	private String title, type;
	
	//initializing the button

	public TextButton(int x, int y, int width, int height, String title, String type, Manager manager) {
		bounds = new Rectangle(x, y, width, height);
		
		this.title = title;
		this.type = type;
		this.manager = manager;
	}
	
	//when the button is being pressed but the mouse has not been released
	
	public boolean pressed() {	
		if(bounds.contains(manager.getMouse().getLocation()))
			if(manager.getMouse().getLeft(0)) return true;
			
		return false;
	}
	
	//checking for when the button is clicked
	
	public boolean clicked() {
		if(bounds.contains(manager.getMouse().getLocation()))
			if(manager.getMouse().getLeft(1)) return true;
			
		return false;
	}
	
	//checking if the button is being hovered over
	
	public boolean covered() {
		if(bounds.contains(manager.getMouse().getLocation())) return true;
		
		return false;
	}
	
	//drawing out the button according to various variables
	
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
		
		if(pressed()) g.setColor(new Color(110, 110, 110));
		else if(covered()) g.setColor(new Color(150, 150, 150));
		else g.setColor(Color.LIGHT_GRAY);
		
		//preparing button template - outdated (replace soon with more versatile code)
		
		switch(type) {
			case "large":
				g.fillRect(bounds.x + 4, bounds.y + 4, bounds.width - 8, bounds.height - 8);
				
				if(covered()) g.setColor(new Color(30, 30, 30));
				else g.setColor(Color.DARK_GRAY);
				
				g.setFont(manager.getFont(3));
				
				manager.getSM().centre(title, bounds.x + (bounds.width / 2), bounds.y + 39, g);
			
				break;
			case "medium":
				g.fillRect(bounds.x + 3, bounds.y + 3, bounds.width - 6, bounds.height - 6);
				
				if(covered()) g.setColor(new Color(30, 30, 30));
				else g.setColor(Color.DARK_GRAY);
				
				g.setFont(manager.getFont(2));
				
				manager.getSM().centre(title, bounds.x + (bounds.width / 2), bounds.y + 27, g);
			
				break;
			case "small":
				g.fillRect(bounds.x + 1, bounds.y + 1, bounds.width - 2, bounds.height - 2);
				
				if(covered()) g.setColor(new Color(30, 30, 30));
				else g.setColor(Color.DARK_GRAY);
				
				g.setFont(manager.getFont(0));
				
				manager.getSM().centre(title, bounds.x + (bounds.width / 2), bounds.y + 24, g);
			
				break;		
			default:
				g.setColor(Color.BLACK);
				g.drawString("error", bounds.x + 5, bounds.y + 5);
				break;
		}
		
		//draws out a button according to the preset
	}
	
	//getters and setters
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Rectangle getBounds() {
		return bounds;
	}
}