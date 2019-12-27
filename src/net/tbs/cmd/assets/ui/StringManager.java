package net.tbs.cmd.assets.ui;

import java.awt.Graphics;

//utility class that helps shrink string code

public class StringManager {
	private int textOffset;
	
	public void centre(String s, int x, int y, Graphics g) {
		//x is the point where we centre the string
		//y is the y coordinate at the bottom of the string
		textOffset = g.getFontMetrics().stringWidth(s) / 2;
		g.drawString(s, x - textOffset, y);
	}
}