package net.tbs.cmd.states;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import net.tbs.cmd.assets.ui.TextButton;
import net.tbs.cmd.game.Game;
import net.tbs.cmd.game.map.Stage;
import net.tbs.cmd.main.Manager;

//the main state in which you play the game

public class GameState extends State {
	//only need game and stage after sorting imports
	
	private Game game;
	private Stage stage;
	
	private NumberFormat noDecimal, oneDecimal;
	
	public GameState(Manager manager) {
		super(manager);
		
		noDecimal = new DecimalFormat("#0");
		oneDecimal = new DecimalFormat("#0.0");
	}
	
	private TextButton pause, end, upgrades;
	
	public void init() {
		pause = new TextButton(850, 620, 240, 60, "Pause", "large", manager);
		end = new TextButton(190, 620, 240, 60, "End Game", "large", manager);
		upgrades = new TextButton(520, 620, 240, 60, "Upgrades", "large", manager);
		
		game = manager.getGame();
		stage = game.getStage();
	}

	public void update() {
		if(pause.clicked() || manager.getKeyboard().getKey(1, 27)) State.setState(manager.getPauseState());
		if(end.clicked()) {
			game.endGame();
			game.setDeathCause("Killed to death.");
		}if(upgrades.clicked() || manager.getKeyboard().getKey(1, 85)) State.setState(manager.getUpgradeMenu());
		
		game.tick();		
	}

	public void render(Graphics g) {
		//background
		
		g.setColor(new Color(191, 191, 200));
		g.fillRect(0, 0, 1280, 720);
		
		//rendering in a simple 'computer'
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(270, 50, 740, 535);
		
		g.setColor(Color.BLACK);
		g.fillRect(280, 60, 720, 515);
		
		g.setColor(new Color(15, 15, 15));
		g.fillRect(795, 61, 204, 513);
		
		//display code
		
		g.setFont(manager.getFont(1));
		g.setColor(Color.RED);
		
		g.drawString("Stage " + stage.getDifficulty(), 805, 80);
		
		g.setColor(new Color(0, 200, 0));
		g.drawString("Score: " + game.getScore().getPoints(), 805, 120);
		
		g.setColor(new Color(200, 200, 0));
		g.drawString("To Next Stage: " + stage.toNextStage(), 805, 140);
		
		g.setColor(new Color(0, 200, 0));
		g.drawString("HP: " + stage.getSEV().getHP() + " / " + stage.getSEV().getMaxHP(), 805, 180);
		g.drawString("Battery: " + oneDecimal.format(stage.getBattery().getQuantity())
		+ " / " + oneDecimal.format(stage.getBattery().getMaxQuantity()), 805, 200);
		
		g.drawString("Scrap Material: " + noDecimal.format(stage.getScrap().getQuantity())
		+ " / " + noDecimal.format(stage.getScrap().getMaxQuantity()), 805, 240);
		
		g.setColor(new Color(50, 50, 255));
		g.drawString("Upgrade Kits: " + noDecimal.format(stage.getUKit().getQuantity())
		+ " / " + noDecimal.format(stage.getUKit().getMaxQuantity()), 805, 260);
		g.drawString("Battery Packs: " + noDecimal.format(stage.getBPack().getQuantity())
		+ " / " + noDecimal.format(stage.getBPack().getMaxQuantity()), 805, 280);
		
		g.setColor(new Color(0, 200, 0));
		g.drawString("x: " + noDecimal.format(stage.getSEV().getPosition().getX())
		+ " y: " + noDecimal.format(stage.getSEV().getPosition().getY()), 805, 320);
		
		if(Settings.isShowingFPS()) g.drawString("FPS = " + manager.getFPS(), 907, 564);
		
		game.render(g);
		
		pause.render(g);
		end.render(g);
		upgrades.render(g);
	}
}