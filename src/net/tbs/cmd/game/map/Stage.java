package net.tbs.cmd.game.map;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import net.tbs.cmd.game.Game;
import net.tbs.cmd.game.entities.SEV;
import net.tbs.cmd.game.entities.items.*;
import net.tbs.cmd.game.entities.mobs.*;
import net.tbs.cmd.game.resources.*;
import net.tbs.cmd.game.upgrades.*;
import net.tbs.cmd.main.Manager;

//the tier based progression system that controls spawns and game difficulty

public class Stage {
	private Manager manager;
	private Game game;
	private Level level;
	
	private int difficulty;
	private Random r;
	
	//uKit = upgrade kit / bPack = battery pack
	
	private Resource battery, scrap, uKit, bPack;
	
	private SEV SEV;
	private ArrayList<Item> items;
	private ArrayList<Mob> mobs;
	
	private Upgrade engineUpgrade, storageUpgrade, batteryUpgrade, armorUpgrade, capacityUpgrade, chassisUpgrade,
		repairPurchase;
	
	public Stage(Manager manager, int difficulty) {
		this.difficulty = difficulty;
		this.manager = manager;
		
		r = new Random();
		
		reset();
	}
	
	public void reset() {
		battery = new Battery(100, 500, manager);
		scrap = new Consumable(0, 5, manager);
		uKit = new Consumable(0, 1, manager);
		bPack  = new Consumable(0, 1, manager);
		
		SEV = new SEV(2, 2, manager);
		items = new ArrayList<Item>();
		mobs = new ArrayList<Mob>();
		
		engineUpgrade = new EngineUpgrade(manager);
		storageUpgrade = new StorageUpgrade(manager);
		batteryUpgrade = new BatteryUpgrade(manager);
		armorUpgrade = new ArmorUpgrade(manager);
		capacityUpgrade = new CapacityUpgrade(manager);
		chassisUpgrade = new ChassisUpgrade(manager);
		repairPurchase = new RepairPurchase(manager);
	}
	
	public void init() {
		game = manager.getGame();
		level = game.getLevel();
		
		battery.init();
		
		SEV.init();
		
		engineUpgrade.init();
		storageUpgrade.init();
		batteryUpgrade.init();
		armorUpgrade.init();
		capacityUpgrade.init();
		chassisUpgrade.init();
		repairPurchase.init();
		
		manager.getUpgradeMenu().init();
	}
	
	private int[] nextStage = {0, 10, 50, 120, 270, 500, 840, 1290, 1850, 2340, 3150, 4100, 5200, 100000};
	//points to next stage
	//0, 10, 40, 70, 150, 230, 340, 450, 560, 690, 810, 950, 1100 / last 4 stages are pretty useless for now
	
	public void tick() {
		//increasing difficulty based on score
		
		if(difficulty < nextStage.length)
			if(game.getScore().getPoints() >= nextStage[difficulty]) setDifficulty(difficulty + 1);
		
		//updating entities
		
		for(int n = 0; n < items.size(); n++) {
			Item i = items.get(n);
			if(!i.isActive()) items.remove(i);
		}
		
		for(int i = 0; i < mobs.size(); i++) {
			Mob m = mobs.get(i);
			m.tick();
			if(!m.isActive()) mobs.remove(m);
		}
		
		SEV.tick();
		
		battery.tick();
		scrap.tick();
		uKit.tick();
		bPack.tick();
	}
	
	//spawning and initializing entities
	
	public void spawnEntities(int difficulty) {
		switch(difficulty) {
		case 1:
			spawnItem("PowerCell_Default", 8);
			spawnItem("RepairKit_Default", 5);
			spawnItem("ScrapMaterial_Metal", 1);
			
			spawnMob("LargeRat", 5);
				
			break;
		case 2:
			spawnItem("PowerCell_Enhanced", 1);
			spawnItem("PowerCell_PowerBank", 1);
			spawnItem("RepairKit_Default", 3);
			spawnItem("RepairKit_Enhanced", 1);
			spawnItem("RepairKit_Upgraded", 1);
			spawnItem("ScrapMaterial_Metal", 1);
			
			spawnMob("LargeRat", 3);
			spawnMob("Roach", 3);
				
			break;
		case 3:
			spawnItem("PowerCell_Default", 2);
			spawnItem("PowerCell_Enhanced", 1);
			spawnItem("RepairKit_Enhanced", 1);
			spawnItem("ScrapMaterial_Metal", 2);
			
			spawnMob("LargeRat", 2);
			spawnMob("Roach", 2);
			spawnMob("MutantRat", 1);
			
			break;
		case 4:
			spawnItem("PowerCell_PowerBank", 1);
			spawnItem("RepairKit_Default", 2);
			spawnItem("RepairKit_Upgraded", 1);
			spawnItem("ScrapMaterial_Metal", 1);

			spawnMob("LargeRat", 2);
			spawnMob("Roach", 2);
			spawnMob("MutantRat", 2);
			
			break;
		case 5:
			spawnItem("RepairKit_Default", 2);
			spawnItem("RepairKit_Enhanced", 2);
			spawnItem("ScrapMaterial_Electronic", 1);
			
			spawnMob("Roach", 2);
			spawnMob("MutantRat", 2);
			spawnMob("Crawler", 1);
			
			break;
		case 6:
			spawnItem("PowerCell_Enhanced", 1);
			spawnItem("PowerCell_PowerBank", 1);
			spawnItem("ScrapMaterial_Metal", 1);
			
			spawnMob("Roach", 3);
			spawnMob("MutantRat", 2);
			spawnMob("Crawler", 2);
			
			break;
		case 7:
			spawnItem("RepairKit_Upgraded", 1);
			spawnItem("ScrapMaterial_Electronic", 1);
			
			spawnMob("MutantRat", 1);
			spawnMob("Crawler", 2);
			spawnMob("Feeder", 1);
			
			break;
		case 8:
			spawnItem("RepairKit_Enhanced", 1);
			spawnItem("ScrapMaterial_Electronic", 1);
			
			spawnMob("MutantRat", 2);
			spawnMob("Crawler", 2);
			spawnMob("Feeder", 2);
			
			break;
		case 9:
			spawnItem("PowerCell_Enhanced", 1);
			spawnItem("ScrapMaterial_Engine", 1);
			
			spawnMob("Crawler", 3);
			spawnMob("Feeder", 2);
			spawnMob("ApexFeeder", 1);
			
			break;
		case 10:
			spawnItem("RepairKit_Enhanced", 1);
			
			spawnMob("Feeder", 2);
			spawnMob("ApexFeeder", 1);

			break; //GG WP LUL
		case 11:
			spawnItem("ScrapMaterial_Engine", 1);
			spawnMob("Feeder", 2);
			spawnMob("ApexFeeder", 2);
			
			break;
		case 12:
			spawnMob("ApexFeeder", 3);
			
			break;
		default: break; }
		
		for(Item i : items) i.init();
		for(Mob m : mobs) m.init();
	}
	
	public void spawnMob(String type, int quantity) {
		switch(type) {
		case "LargeRat": for(int n = 0; n < quantity; n++)
			mobs.add(new LargeRat(r.nextInt(16), r.nextInt(16), manager));
			break;
		case "Roach": for(int n = 0; n < quantity; n++)
			mobs.add(new Roach(r.nextInt(16), r.nextInt(16), manager));
			break;
		case "MutantRat": for(int n = 0; n < quantity; n++)
			mobs.add(new MutantRat(r.nextInt(16), r.nextInt(16), manager));
			break;
		case "Crawler": for(int n = 0; n < quantity; n++)
			mobs.add(new Crawler(r.nextInt(16), r.nextInt(16), manager));
			break;
		case "Feeder": for(int n = 0; n < quantity; n++)
			mobs.add(new Feeder(r.nextInt(16), r.nextInt(16), manager));
			break;
		case "ApexFeeder": for(int n = 0; n < quantity; n++)
			mobs.add(new ApexFeeder(r.nextInt(16), r.nextInt(16), manager));
			break;
		default: break; }
	}
	
	public void spawnItem(String type, int quantity) {
		switch(type) {
		case "PowerCell_Default": for(int n = 0; n < quantity; n++)
			items.add(new PowerCell(r.nextInt(16), r.nextInt(16), manager, "default"));
			break;
		case "PowerCell_Enhanced": for(int n = 0; n < quantity; n++)
			items.add(new PowerCell(r.nextInt(16), r.nextInt(16), manager, "enhanced"));
			break;
		case "PowerCell_PowerBank": for(int n = 0; n < quantity; n++)
			items.add(new PowerCell(r.nextInt(16), r.nextInt(16), manager, "powerbank"));
			break;
		case "RepairKit_Default": for(int n = 0; n < quantity; n++)
			items.add(new RepairKit(r.nextInt(16), r.nextInt(16), manager, "default"));
			break;
		case "RepairKit_Enhanced": for(int n = 0; n < quantity; n++)
			items.add(new RepairKit(r.nextInt(16), r.nextInt(16), manager, "enhanced"));
			break;
		case "RepairKit_Upgraded": for(int n = 0; n < quantity; n++)
			items.add(new RepairKit(r.nextInt(16), r.nextInt(16), manager, "upgraded"));
			break;
		case "ScrapMaterial_Metal": for(int n = 0; n < quantity; n++)
			items.add(new ScrapMaterial(r.nextInt(16), r.nextInt(16), manager, "metal"));
			break;
		case "ScrapMaterial_Electronic": for(int n = 0; n < quantity; n++)
			items.add(new ScrapMaterial(r.nextInt(16), r.nextInt(16), manager, "electronic"));
			break;
		case "ScrapMaterial_Engine": for(int n = 0; n < quantity; n++)
			items.add(new ScrapMaterial(r.nextInt(16), r.nextInt(16), manager, "engine"));
			break;
		default: break; }
	}
	
	public void checkCollisions() {
		for(Mob m : mobs) m.checkCollisions();
		for(Item i : items) i.checkCollisions();
	}
	
	//rendering entities
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		for(Item i : items) i.render(g, g2d);
		for(Mob m : mobs) m.render(g, g2d);
		
		SEV.render(g, g2d);
	}
	
	//getters and setters

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
		
		if(difficulty > 9) level.contaminate(13 - difficulty + 1, 2);
		else if(difficulty > 6) level.contaminate(10 - difficulty, 1);
		else if(difficulty > 3) level.contaminate(7 - difficulty, 0);
		
		spawnEntities(difficulty);
	}
	
	public int toNextStage() {
		return nextStage[difficulty];
	}

	public Resource getBattery() {
		return battery;
	}

	public Resource getScrap() {
		return scrap;
	}

	public Resource getUKit() {
		return uKit;
	}

	public Resource getBPack() {
		return bPack;
	}

	public SEV getSEV() {
		return SEV;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public ArrayList<Mob> getMobs() {
		return mobs;
	}

	public Upgrade getEngineUpgrade() {
		return engineUpgrade;
	}

	public Upgrade getStorageUpgrade() {
		return storageUpgrade;
	}

	public Upgrade getBatteryUpgrade() {
		return batteryUpgrade;
	}

	public Upgrade getArmorUpgrade() {
		return armorUpgrade;
	}

	public Upgrade getCapacityUpgrade() {
		return capacityUpgrade;
	}

	public Upgrade getChassisUpgrade() {
		return chassisUpgrade;
	}

	public Upgrade getRepairPurchase() {
		return repairPurchase;
	}
}