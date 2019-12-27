package net.tbs.cmd.main;

import java.awt.Dimension;

import javax.swing.JFrame;

//main JFrame class

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private String title = "Remnants of the Outage"; 
	private final Dimension size = new Dimension(1280, 720);
	
	public Window() {
		setTitle(title);
		setSize(size);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		this.add(new Engine(this));
	}
}