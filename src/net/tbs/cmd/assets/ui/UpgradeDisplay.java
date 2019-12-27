package net.tbs.cmd.assets.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import net.tbs.cmd.main.Manager;

//the class that contains the bulk of the reusable code for upgrades

public class UpgradeDisplay {
	private Rectangle bounds;
	
	private TextButton purchase;
	private InfoDisplay textbox;
	
	public UpgradeDisplay(int x, int y, String title, Manager manager) {
		bounds = new Rectangle(x, y, 200, 40);
		
		purchase = new TextButton(x, y, bounds.width, bounds.height, title, "medium", manager);
		textbox = new InfoDisplay(bounds.x - 20, bounds.y - 83, bounds.width + 40, 85, 20, 18, 2, manager);
		textbox.setCentred(true);
	}
	
	public void render(Graphics g) {
		purchase.render(g);
		
		g.setColor(Color.DARK_GRAY);
		if(purchase.covered()) textbox.render(g);
	}
	
	private int tierDispWidth, barWidth;
	
	//multi-stage tier indicator in the upgrades menu
	
	public void renderTierDisplay(int tier, int maxTier, int width, Graphics g) {
		tierDispWidth = width;
		barWidth = tierDispWidth / maxTier;
		
		g.setColor(new Color(30, 30, 30));
		
		g.fillRect(bounds.x + bounds.width + 20, bounds.y, tierDispWidth + 2, bounds.height);
		
		for(int i = 0; i < maxTier; i++) {
			if(i < tier) g.setColor(Color.GREEN);
			else g.setColor(Color.RED);
			g.fillRect(bounds.x + bounds.width + 22 + (i * barWidth), bounds.y + 2, barWidth - 2, bounds.height - 4);
		}
	}
	
	//getters and setters
	
	public void setTitle(String s) {
		purchase.setTitle(s);
	}
	
	public void addLine(String s) {
		textbox.addLine(s);
	}
	
	public void addSpace() {
		textbox.addSpace();
	}
	
	public void setLine(int index, String s) {
		textbox.setLine(index, s);
	}
	
	public boolean buy() {
		return purchase.clicked();
	}
}