package net.tbs.cmd.game.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import net.tbs.cmd.game.map.tiles.Tile;
import net.tbs.cmd.main.Manager;

//the class that creates and manages the game map

public class Level {
	private Manager manager;
	
	private static Tile[][] tile;
	private Dimension size;
	private Random r;
	
	private final int tileSize = 32;
	
	public Level(int width, int height, Manager manager) {
		this.manager = manager;
		
		size = new Dimension(width, height);
		
		r = new Random();
		
		reset();
	}
	
	public void init() {	
		generate();
		
		for(int x = 0; x < size.width; x++)	{
			for(int y = 0; y < size.height; y++)	{	
				if(tile[x][y].getID() != -1) tile[x][y].init();
			}
		}
	}
	
	public void reset() {
		tile = new Tile[size.width][size.height];
		
		for(int x = 0; x < size.width; x++) {
			for(int y = 0; y < size.height; y++) {
				tile[x][y] = new Tile(new Rectangle(x, y, tileSize, tileSize), 0, manager);
			}
		}
	}
	
	private int rNum; //random number / band-aid fix for random generation
	
	public void generate() {
		for(int x = 0; x < size.width; x++)	{
			for(int y = 0; y < size.height; y++) {
				if(x == 4 && (y <= 11 && y >= 4) || x == 11 && (y <= 11 && y >= 4) ||
					y == 4 && (x <= 11 && x >= 4 && (x != 7 && x != 8)) || y == 11 && (x <= 11 && x >= 4)) {
					
					tile[x][y].setID(-1);
				}else{
					rNum = r.nextInt(4);
					tile[x][y].setSubType(rNum);
				}
			}
		}
	}
	
	public void contaminate(int chance, int stage) {
		for(int x = 0; x < size.width; x++)	{
			for(int y = 0; y < size.height; y++) {
				if(stage == 0 && tile[x][y].getID() == 0) { //band-aid fix
					rNum = r.nextInt(chance);
					
					if(rNum == 0) tile[x][y].setID(1);
				}else if(stage == 1 && tile[x][y].getID() == 1) {
					rNum = r.nextInt(chance);
					
					if(rNum == 0) tile[x][y].setID(2);
				}else if(stage == 2 && tile[x][y].getID() == 2) {
					rNum = r.nextInt(chance);
					
					if(rNum == 0) tile[x][y].setID(3);
				}
			}
		}
	}
	
	public void render(Graphics g) {
		for(int x = 0; x < size.width; x++)	{
			for(int y = 0; y < size.height; y++)	{
				g.setColor(Color.DARK_GRAY);
				tile[x][y].render(g);
			}
		}
	}
	
	//getters and setters

	public Tile getTile(int x, int y) {
		return tile[x][y];
	}

	public Dimension getSize() {
		return size;
	}
	
	public int getTileSize() {
		return tileSize;
	}
}