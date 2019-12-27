package net.tbs.cmd.assets.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import net.tbs.cmd.main.Manager;

//a class used for all sorts of text displays

public class InfoDisplay {
	private Manager manager;
	
	private Rectangle bounds;
	
	private int sideWidth;
	private Point offset; //text offset
	private ArrayList<String> display;
	private boolean centred;
	
	public InfoDisplay(int x, int y, int width, int height, int xOffset, int yOffset, int sideWidth, Manager manager) {
		this.manager = manager;
		this.sideWidth = sideWidth;
		centred = false;
		
		bounds = new Rectangle(x, y, width, height);
		offset = new Point(xOffset, yOffset);

		display = new ArrayList<String>();
	}
	
	public void render(Graphics g) {
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
		
		//drawing the text region
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(bounds.x + sideWidth, bounds.y + sideWidth, bounds.width - (sideWidth * 2), bounds.height - (sideWidth * 2));
		
		g.setColor(Color.BLACK);
		g.setFont(manager.getFont(1));
		
		if(!display.isEmpty()) {
			for(int i = 0; i < display.size(); i++) {
				if(centred) manager.getSM().centre(display.get(i),
						bounds.x + (bounds.width / 2), bounds.y + offset.y + (i * 15), g);
				else g.drawString(display.get(i), bounds.x + offset.x, bounds.y + offset.y + (i * 15));
			}
		}
	}
	
	//getters and setters
	
	public void addLine(String s) {
		display.add(s);
	}
	
	public void addSpace() {
		display.add("");
	}
	
	public void setLine(int index, String s) {
		display.set(index, s);
	}
	
	public void clear() {
		display.clear();
	}

	public boolean isCentred() {
		return centred;
	}

	public void setCentred(boolean centred) {
		this.centred = centred;
	}
}