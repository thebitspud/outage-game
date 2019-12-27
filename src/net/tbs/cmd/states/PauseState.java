package net.tbs.cmd.states;

import java.awt.Color;
import java.awt.Graphics;

import net.tbs.cmd.assets.ui.TextButton;
import net.tbs.cmd.main.Manager;

//the state you enter to pause the game

public class PauseState extends State {
	public PauseState(Manager manager) {
		super(manager);
	}

	private TextButton resume, menu, settings;
	
	public void init() {
		resume = new TextButton(520, 240, 240, 60, "Back to Game", "large", manager);
		settings = new TextButton(520, 330, 240, 60,"Settings", "large", manager);
		menu = new TextButton(520, 420, 240, 60, "Menu", "large", manager);
	}

	public void update() {
		if(resume.clicked()) State.setState(manager.getGameState());
		if(menu.clicked()) State.setState(manager.getMenuState());
		if(settings.clicked()) {
			State.setState(manager.getSettings());
			Settings.lastState = "paused";
		}
	}

	public void render(Graphics g) {
		g.setFont(manager.getFont(4));
		g.setColor(new Color(0, 0, 0));
		manager.getSM().centre("Paused", 640, 140, g);
		
		g.setFont(manager.getFont(0));
		g.setColor(Color.DARK_GRAY);
		g.drawString("TheBitspud™ 2018", 30, 690);
		
		resume.render(g);
		menu.render(g);
		settings.render(g);
	}
}