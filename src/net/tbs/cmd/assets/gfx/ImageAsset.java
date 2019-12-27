package net.tbs.cmd.assets.gfx;

import java.awt.image.BufferedImage;

//loading up sprites and images for later use

import net.tbs.cmd.assets.ResourceLoader;

//loads in all the images needed for the game

public class ImageAsset {
	private BufferedImage UI[][], SEV, item[][], mob[][], tile[][];
	private ImageSheet infcSheet, itemSheet, mobSheet, tileSheet;
	
	public ImageAsset(ResourceLoader rl) {
		//interface icons
		
		rl = new ResourceLoader();
		
		infcSheet = new ImageSheet(rl.loadImage("interface"));
		
		//up/down keys
		
		UI = new BufferedImage[2][3];
		
		UI[0][0] = infcSheet.crop(0, 0, 40, 40);
		UI[0][1] = infcSheet.crop(0, 40, 40, 40);
		UI[0][2] = infcSheet.crop(0, 80, 40, 40);
		
		UI[1][0] = infcSheet.crop(40, 0, 40, 40);
		UI[1][1] = infcSheet.crop(40, 40, 40, 40);
		UI[1][2] = infcSheet.crop(40, 80, 40, 40);
		
		//loading the SEV
		
		SEV = rl.loadImage("SEV");
		
		//items
		
		itemSheet = new ImageSheet(rl.loadImage("item"));
		item = new BufferedImage[3][3];
		
		item[0][0] = itemSheet.crop(0, 0, 16, 8); //PowerCell
		item[0][1] = itemSheet.crop(0, 8, 16, 8); //ID 100
		item[0][2] = itemSheet.crop(0, 16, 16, 8);
		
		item[1][0] = itemSheet.crop(16, 0, 16, 8); //RepairKit
		item[1][1] = itemSheet.crop(16, 8, 16, 8); //ID 101
		item[1][2] = itemSheet.crop(16, 16, 16, 8);
		
		item[2][0] = itemSheet.crop(32, 0, 16, 16); //Scrap
		item[2][1] = itemSheet.crop(32, 16, 16, 16); //ID 102
		item[2][2] = itemSheet.crop(32, 32, 16, 16);
		
		//mobs
		
		mobSheet = new ImageSheet(rl.loadImage("mob"));
		mob = new BufferedImage[6][4];
		
		mob[0][0] = mobSheet.crop(0, 0, 16, 8); //largeRat
		mob[0][1] = mobSheet.crop(0, 8, 16, 8); //ID 0
		mob[0][2] = mobSheet.crop(0, 16, 16, 8); //Width 16 Height 8
		mob[0][3] = mobSheet.crop(0, 24, 16, 8);
		
		mob[1][0] = mobSheet.crop(16, 0, 16, 8); //Roach
		mob[1][1] = mobSheet.crop(16, 8, 16, 8); //ID 1
		mob[1][2] = mobSheet.crop(16, 16, 16, 8); //Width 16 Height 8
		mob[1][3] = mobSheet.crop(16, 24, 16, 8);
		
		mob[2][0] = mobSheet.crop(0, 32, 16, 8); //MutantRat
		mob[2][1] = mobSheet.crop(0, 40, 16, 8); //ID 2
		mob[2][2] = mobSheet.crop(0, 48, 16, 8); //Width 16 Height 8
		mob[2][3] = mobSheet.crop(0, 56, 16, 8);
		
		mob[3][0] = mobSheet.crop(32, 0, 16, 16); //Crawler
		mob[3][1] = mobSheet.crop(32, 16, 16, 16); //ID 3
		mob[3][2] = mobSheet.crop(32, 32, 16, 16); //Width 16 Height 16
		mob[3][3] = mobSheet.crop(32, 48, 16, 16);
		
		mob[4][0] = mobSheet.crop(48, 0, 16, 16); //Feeder
		mob[4][1] = mobSheet.crop(48, 16, 16, 16); //ID 4
		mob[4][2] = mobSheet.crop(48, 32, 16, 16); //Width 16 Height 16
		mob[4][3] = mobSheet.crop(48, 48, 16, 16);
		
		mob[5][0] = mobSheet.crop(64, 0, 16, 16); //Apex Feeder
		mob[5][1] = mobSheet.crop(64, 16, 16, 16); //ID 5
		mob[5][2] = mobSheet.crop(64, 32, 16, 16); //Width 16 Height 16
		mob[5][3] = mobSheet.crop(64, 48, 16, 16);
		
		//tiles
		
		tileSheet = new ImageSheet(rl.loadImage("tile"));
		tile = new BufferedImage[8][8];
		
		for(int x = 0; x < tile.length; x++)
			for(int y = 0; y < tile[0].length; y++)
				tile[x][y] = tileSheet.crop(x * 16, y * 16, 16, 16);
	}
	
	//getters and setters
	
	public BufferedImage[][] getUI() {
		return UI;
	}

	public BufferedImage getSEV() {
		return SEV;
	}

	public BufferedImage[][] getItem() {
		return item;
	}

	public BufferedImage[][] getMob() {
		return mob;
	}

	public BufferedImage[][] getTile() {
		return tile;
	}
}