package net.tbs.cmd.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//the class that allows interactions with the game through the keyboard

public class KeyManager implements KeyListener {
	private boolean[][] key;
	
	public KeyManager() {
		key = new boolean[2][256];
		//[0] if mouse is being held / [1] if mouse was just released
	}
	
	public void update() {
		for(int i = 0; i < key[0].length; i++) key[1][i] = false;
	}
	
	public void keyPressed(KeyEvent e) {
		key[0][e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		key[0][e.getKeyCode()] = false;
		key[1][e.getKeyCode()] = true;
	}
	
	//detecting keyboard activity
	
	public void keyTyped(KeyEvent e) {}

	//getters and setters
	
	public boolean getKey(int type, int value) {
		return key[type][value];
	}
}