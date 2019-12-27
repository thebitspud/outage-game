package net.tbs.cmd.game.map.tiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import net.tbs.cmd.main.Manager;
import net.tbs.cmd.states.Settings;

//the class that acts as a basis for all in-game tiles

public class Tile extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	private Manager manager;
	
	private int ID, subtype;
	private final int size = 32;
	private Point rPos;
	
	private BufferedImage texture;
	
	private boolean containsItem, containsMob, solid;
	
	//tile variables
	
	public Tile(Rectangle bounds, int ID, Manager manager) {
		setBounds(bounds);
		
		this.ID = ID;
		this.manager = manager;
		
		containsItem = false;
		containsMob = false;
		
		solid = false;
	}
	
	public void init() {
		rPos = new Point((x * size) + manager.getGame().getOffset().x, (y * size) + manager.getGame().getOffset().y);
		
		texture = manager.getIAsset().getTile()[ID][subtype];
	}
	
	public void render(Graphics g) {
		if(ID != -1) {//if ID is -1, the tile will not attempt to render a texture
			g.drawImage(texture, rPos.x, rPos.y, width, height, null);
			
			if(Settings.isShowingTileGrid()) g.drawRect(rPos.x, rPos.y, width, height);
			
			if(Settings.isShowingEOverlay()) {
				if(containsItem && containsMob) {
					g.setColor(new Color(127, 127, 0));
					g.drawRect(rPos.x, rPos.y, width - 1, height - 1);
				}else if(containsItem) {
					g.setColor(new Color(0, 127, 0));
					g.drawRect(rPos.x, rPos.y, width - 1, height - 1);
				}else if(containsMob) {
					g.setColor(new Color(127, 0, 0));
					g.drawRect(rPos.x, rPos.y, width - 1, height - 1);
				}
			}
		}
		
		//experimental overlay code
	}
	
	//getters and setters

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
		
		if(ID == -1) solid = true;
		else {
			texture = manager.getIAsset().getTile()[ID][subtype];
			solid = false;
		}
	}

	public int getSubType() {
		return subtype;
	}

	public void setSubType(int type) {
		this.subtype = type;
	}
	
	public boolean getContainsMob() {
		return containsMob;
	}
	
	public void setContainsMob(boolean contains) {
		containsMob = contains;
	}
	
	public boolean getContainsItem() {
		return containsItem;
	}
	
	public void setContainsItem(boolean contains) {
		containsItem = contains;
	}
	
	public boolean isSolid() {
		return solid;
	}
}