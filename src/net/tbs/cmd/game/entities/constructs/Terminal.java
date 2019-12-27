package net.tbs.cmd.game.entities.constructs;

import net.tbs.cmd.main.Manager;

//interactive computers you can find around the game world - currently unsused

public class Terminal extends Construct {
	public Terminal(int x, int y, Manager manager) {
		super(x, y, 32, 32, manager);
	}

	public void setStats() {
		ID = 200;
	}
}