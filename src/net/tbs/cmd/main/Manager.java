package net.tbs.cmd.main;

import java.awt.Font;

import net.tbs.cmd.assets.gfx.ImageAsset;
import net.tbs.cmd.assets.ui.StringManager;
import net.tbs.cmd.game.Game;
import net.tbs.cmd.input.*;
import net.tbs.cmd.states.*;

//universally accessible class that controls the game class, input, and updating

public class Manager {
	//importing the main classes
	
	private Engine engine;
	private Game game;
	
	//input
	
	private MouseManager mouse;
	private KeyManager keyboard;
	private final String version = "Alpha Prerelease 0.4", versionAdv = "Alpha Prerelease 0.4/80322 [Mar. 2018]";
	
	//states
	
	private State menuState, gameState, pauseState, settings, playerManual, endState, upgradeMenu;
	
	//assets

	private Font[] f;
	private ImageAsset iAsset;
	private StringManager sm;
	
	//declaring variables and classes
	
	public Manager(Engine engine) {
		this.engine = engine;
		
		mouse = new MouseManager();
		keyboard = new KeyManager();
		
		f = new Font[5];
		iAsset = new ImageAsset(engine.getResourceLoader());
		sm = new StringManager();
		
		gameState = new GameState(this);
		menuState = new MenuState(this);
		pauseState = new PauseState(this);
		settings = new Settings(this);
		playerManual = new PlayerManual(this);
		endState = new EndState(this);
		upgradeMenu = new UpgradeMenu(this);
		
		game = new Game(this);
		
		this.engine.getWindow().addKeyListener(keyboard);
		this.engine.getWindow().addMouseListener(mouse);
		this.engine.getWindow().addMouseMotionListener(mouse);
	}
	
	public void init() {
		//font types increases in size with index
		
		f[0] = new Font("Verdana", Font.PLAIN, 12);
		f[1] = new Font("Verdana", Font.PLAIN, 14);
		f[2] = new Font("Verdana", Font.PLAIN, 20);
		f[3] = new Font("Verdana", Font.PLAIN, 28);
		f[4] = new Font("Verdana", Font.BOLD, 48);
		
		game.init();
		
		gameState.init();
		menuState.init();
		pauseState.init();
		settings.init();
		playerManual.init();
		endState.init();
		upgradeMenu.init();
	}
	
	public void update() {
		mouse.update();
		keyboard.update();
	}
	
	//getters and setters
	
	public Font getFont(int value) {
		return f[value];
	}
	
	public double getFPS() {
		return engine.getFPS();
	}

	public double getMaxFPS() {
		return engine.getMaxFPS();
	}

	public String getVersion() {
		return version;
	}
	
	public String getVersionAdv() {
		return versionAdv;
	}

	public Game getGame() {
		return game;
	}

	public MouseManager getMouse() {
		return mouse;
	}

	public KeyManager getKeyboard() {
		return keyboard;
	}

	public State getMenuState() {
		return menuState;
	}

	public State getGameState() {
		return gameState;
	}

	public State getPauseState() {
		return pauseState;
	}

	public State getSettings() {
		return settings;
	}

	public State getPlayerManual() {
		return playerManual;
	}

	public State getEndState() {
		return endState;
	}

	public State getUpgradeMenu() {
		return upgradeMenu;
	}

	public ImageAsset getIAsset() {
		return iAsset;
	}
	
	public StringManager getSM() {
		return sm;
	}
}