package net.tbs.cmd.states;

import java.awt.Color;
import java.awt.Graphics;

import net.tbs.cmd.assets.ui.TextButton;
import net.tbs.cmd.game.Game;
import net.tbs.cmd.main.Manager;

//the menu selection state that allows access to the game, player manual, and settings

public class MenuState extends State {
	private Game game;
	
	private boolean viewStats = false;
	
	public MenuState(Manager manager) {
		super(manager);
	}
	
	private TextButton play, exit, settings, pManual, vStats;
	
	public void init() {
		play = new TextButton(520, 240, 240, 60, "Play", "large", manager);
		pManual = new TextButton(520, 320, 240, 60, "Player's Manual", "large", manager);
		vStats = new TextButton(520, 400, 240, 60, "View Stats", "large", manager);
		settings = new TextButton(520, 480, 240, 60,"Settings", "large", manager);
		exit = new TextButton(520, 560, 240, 60, "Exit", "large", manager);
		
		game = manager.getGame();
	}

	public void update() {
		//detecting button interactions
		
		if(play.clicked()) setState(manager.getGameState());
		if(exit.clicked()) System.exit(1);
		if(settings.clicked()) {
			Settings.lastState = "menu";
			setState(manager.getSettings());
		}
		
		//toggle for showing stats on menu screen
		
		if(pManual.clicked()) State.setState(manager.getPlayerManual());
		if(vStats.clicked()) {
			if(viewStats) viewStats = false;
			else viewStats = true;
		}
	}

	public void render(Graphics g) {
		play.render(g);
		exit.render(g);
		settings.render(g);
		pManual.render(g);
		vStats.render(g);
		
		//displaying stats on the menu screen
		
		g.setColor(Color.BLACK);
		g.setFont(manager.getFont(2));
		
		if(viewStats) {
			g.drawString("High Score: " + game.getScore().getHighScore("history"), 100, 250);
			g.drawString("Last Score: " + game.getScore().getLastScore(), 100, 280);
		}
		
		//setting the title text
		
		g.setFont(manager.getFont(4));
		g.setColor(new Color(127, 0, 0));
		manager.getSM().centre("Remnants of the Outage", 640, 140, g);
		
		g.setFont(manager.getFont(1));
		g.setColor(Color.DARK_GRAY);
		manager.getSM().centre(manager.getVersion(), 640, 200, g);
		
		g.setFont(manager.getFont(0));
		g.drawString("TheBitspud™ 2018", 30, 690);
	}
}