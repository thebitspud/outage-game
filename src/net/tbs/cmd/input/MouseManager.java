package net.tbs.cmd.input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

//the class that allows interaction with the game through the mouse

public class MouseManager implements MouseListener, MouseMotionListener {
	private boolean[] left, right;
	private Point location;
	
	public MouseManager() {
		left = new boolean[2];
		right = new boolean[2];
		
		location = new Point();
		
		//[0] = if mouse is being held : [1] = if mouse was just released
	}
	
	public void update() {
		left[1] = false;
		right[1] = false;
		
		//resetting the mouse buttons
	}
	
	public void mouseDragged(MouseEvent e) {
		location = new Point(e.getX(), e.getY());
	}

	public void mouseMoved(MouseEvent e) {
		location = new Point(e.getX(), e.getY());
	}
	
	//tracking motion
	

	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) left[0] = true;
		if(e.getButton() == MouseEvent.BUTTON3) right[0] = true;
	}

	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			left[0] = false;
			left[1] = true;
		}
		
		if(e.getButton() == MouseEvent.BUTTON3) {
			right[0] = false;
			right[1] = true;
		}
	}
	
	//detecting mouse interactions
	
	//getters and setters
	
	public boolean getLeft(int type) {
		return left[type];
	}

	public boolean getRight(int type) {
		return right[type];
	}

	public Point getLocation() {
		return location;
	}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}