package net.tbs.cmd.states;

import java.awt.Graphics;

import net.tbs.cmd.assets.ui.TextButton;
import net.tbs.cmd.main.Manager;

//the state where you adjust the in-game settings

public class Settings extends State {
	public Settings(Manager manager) {
		super(manager);
	}
	
	private TextButton done, FPS, actionDelay, tileGrid, eOverlay;
	private static boolean showFPS, showActionDelay, showTileGrid, showEOverlay;
	
	//eOverlay = entity overlay
	
	public static String lastState;
	
	public void init() {
		FPS = new TextButton(100, 100, 240, 40, "FPS Counter: ON", "medium", manager);
		actionDelay = new TextButton(100, 160, 240, 40, "Action Delay: ON", "medium", manager);
		tileGrid = new TextButton(100, 220, 240, 40, "Tile Grid: OFF", "medium", manager);
		eOverlay = new TextButton(100, 280, 240, 40, "Entity Overlay: ON", "medium", manager);
		
		done = new TextButton(520, 600, 240, 60, "Done", "large", manager);
		
		showFPS = true;
		showActionDelay = true;
		showTileGrid = false;
		showEOverlay = true;
	}

	public void update() {
		//detecting the last active state
		
		if(done.clicked()) {
			switch(lastState) {
				case "paused":
					State.setState(manager.getGameState());
					
					break;
				case "menu":
					State.setState(manager.getMenuState());
					
					break;
				default:
					State.setState(manager.getMenuState());
					
					break;
			}
		}
		
		//FPS toggle
		
		if(FPS.clicked()) {
			if(!isShowingFPS()) {
				showFPS  = true;
				FPS.setTitle("FPS Counter: ON");
			}else{
				showFPS = false;
				FPS.setTitle("FPS Counter: OFF");
			}
		}
		
		//Action Delay toggle
		
		if(actionDelay.clicked()) {
			if(!isShowingActionDelay()) {
				showActionDelay  = true;
				actionDelay.setTitle("Action Delay: ON");
			}else{
				showActionDelay = false;
				actionDelay.setTitle("Action Delay: OFF");
			}
		}
		
		//Coordinate Grid toggle
		
		if(tileGrid.clicked()) {
			if(!isShowingTileGrid()) {
				showTileGrid  = true;
				tileGrid.setTitle("Tile Grid: ON");
			}else{
				showTileGrid = false;
				tileGrid.setTitle("Tile Grid: OFF");
			}
		}
		
		//Entity Overlay toggle
		
		if(eOverlay.clicked()) {
			if(!isShowingEOverlay()) {
				showEOverlay = true;
				eOverlay.setTitle("Entity Overlay: ON");
			}else{
				showEOverlay = false;
				eOverlay.setTitle("Entity Overlay: OFF");
			}
		}
	}

	public void render(Graphics g) {
		done.render(g);
		
		FPS.render(g);
		actionDelay.render(g);
		tileGrid.render(g);
		eOverlay.render(g);
	}
	
	//getters and setters

	public static boolean isShowingFPS() {
		return showFPS;
	}
	
	public static boolean isShowingActionDelay() {
		return showActionDelay;
	}
	
	public static boolean isShowingTileGrid() {
		return showTileGrid;
	}
	
	public static boolean isShowingEOverlay() {
		return showEOverlay;
	}
}