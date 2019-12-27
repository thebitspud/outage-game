package net.tbs.cmd.states;

import java.awt.Graphics;

import net.tbs.cmd.assets.ui.TextButton;
import net.tbs.cmd.game.Game;
import net.tbs.cmd.main.Manager;

//the state you enter when you win/lose

public class EndState extends State {
	private Game game;
	
	public EndState(Manager manager) {
		super(manager);
	}
	
	private TextButton play, menu;

	public void init() {
		play = new TextButton(520, 510, 240, 60, "Play Again", "large", manager);
		menu = new TextButton(520, 600, 240, 60, "Menu", "large", manager);
		
		game = manager.getGame();
	}

	public void update() {
		if(menu.clicked()) {
			game.init();
			
			State.setState(manager.getMenuState());
		}
		
		if(play.clicked()) {
			game.init();
			
			State.setState(manager.getGameState());
		}
	}
	
	//experimental centre-align text

	public void render(Graphics g) {
		g.setFont(manager.getFont(2));
		manager.getSM().centre(game.getDeathCause(), 640, 180, g);
		
		g.setFont(manager.getFont(3));
		
		manager.getSM().centre("Game Over", 640, 320, g);
		manager.getSM().centre("Your Score: " + game.getScore().getLastScore(), 640, 360, g);
		
		menu.render(g);
		play.render(g);
	}
}