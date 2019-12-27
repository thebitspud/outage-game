package net.tbs.cmd.game;

import java.awt.Graphics;
import java.awt.Point;

import net.tbs.cmd.game.map.Level;
import net.tbs.cmd.game.map.Stage;
import net.tbs.cmd.main.Manager;
import net.tbs.cmd.states.State;

//the class that manages the all game assets

public class Game {
	private Manager manager;
	
	private Level level;
	private Stage stage;
	
	private Score score;
	
	private final Point offset = new Point(281, 61);; //the point on the screen where the level renders
	
	public Game(Manager manager) {
		this.manager = manager;
		score = new Score(0);
		
		level = new Level(16, 16, manager);
		stage = new Stage(manager, 0);
	}
	
	public void init() {
		score.reset();
		
		level.init();
		stage.init();
	}
	
	public void tick() {
		stage.tick();
	}
	
	private String deathCause;
	
	public void endGame() {
		score.setLastScore();
		score.save("history");
		
		level.reset();
		stage.reset();
		stage.setDifficulty(0);
		
		init();
		
		State.setState(manager.getEndState());
	}
	
	public void render(Graphics g) {
		level.render(g);
		stage.render(g);
	}
	
	//getters and setters
	
	public Manager getManager() {
		return manager;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Score getScore() {
		return score;
	}

	public Point getOffset() {
		return offset;
	}

	public String getDeathCause() {
		return deathCause;
	}

	public void setDeathCause(String deathCause) {
		this.deathCause = deathCause;
	}
}