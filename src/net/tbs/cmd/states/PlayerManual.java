package net.tbs.cmd.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import net.tbs.cmd.assets.gfx.Animation;
import net.tbs.cmd.assets.gfx.ImageAsset;
import net.tbs.cmd.assets.ui.*;
import net.tbs.cmd.main.Manager;

//the interactive in-game guide and reference

public class PlayerManual extends State {
	private ImageAsset iAsset;

	private BufferedImage[] mobDisplay;
	private Animation[] mob_walk; //move / walk animations
	
	public PlayerManual(Manager manager) {
		super(manager);
		
		iAsset = manager.getIAsset();
	}
	
	private boolean showAbout;
	private int pageNum, pageCount, aboutPN, aboutPC, guidePN, guidePC;
	private String pageTitle;
	private GlossaryDisplay glossDisp;
	
	private TextButton menu, previous, next, about, itemInfo[], mobInfo[];
	private ImageButton aboutPrev, aboutNext, guidePrev, guideNext;

	public void init() {
		menu = new TextButton(520, 600, 240, 60, "Done", "large", manager);
		previous = new TextButton(100, 600, 200, 40, "Previous", "medium", manager);
		next = new TextButton(1000, 600, 200, 40, "Next", "medium", manager);
		
		about = new TextButton(683, 123, 170, 40, "Show About", "medium", manager);
		aboutPrev = new ImageButton(811, 179, 40, 40, iAsset.getUI()[0], manager);
		aboutNext = new ImageButton(811, 471, 40, 40, iAsset.getUI()[1], manager);
		
		guidePrev = new ImageButton(611, 179, 40, 40, iAsset.getUI()[0], manager);
		guideNext = new ImageButton(611, 521, 40, 40, iAsset.getUI()[1], manager);
		
		itemInfo = new TextButton[9];
		mobInfo = new TextButton[6];
		
		glossDisp = new GlossaryDisplay(manager);
		
		for(int i = 0; i < itemInfo.length; i++)
			itemInfo[i] = new TextButton(150 + (100 * i) - (700 * (int) (i / 7)),
					126 + (100 * (int) (i / 7)), 80, 80, "", "medium", manager);
		
		for(int i = 0; i < mobInfo.length; i++)
			mobInfo[i] = new TextButton(150 + (100 * i), 126, 80, 80, "", "medium", manager);
		
		showAbout = false;
		
		pageNum = 1;
		pageCount = 5;
		
		//about section text section
		
		aboutPN = 1; //PN = Page Number
		aboutPC = 2; //PC = Page Count
		
		//instructional guide text section
		
		guidePN = 1;
		guidePC = 2;
		
		//mob display supplement
		
		mobDisplay = new BufferedImage[mobInfo.length];
		mob_walk = new Animation[mobInfo.length];
		
		mob_walk[0] = new Animation(4, 20); //LargeRat
		mob_walk[1] = new Animation(4, 3); //Roach
		mob_walk[2] = new Animation(4, 15); //MutantRat
		mob_walk[3] = new Animation(4, 8); //Crawler
		mob_walk[4] = new Animation(4, 12); //Feeder
		mob_walk[5] = new Animation(4, 10); //ApexFeeder
	}

	public void update() {
		//player manual button controls
		
		if(menu.clicked()) State.setState(manager.getMenuState());
		if(previous.clicked()) {
			pageNum--;
			if(pageNum < 1) pageNum = pageCount;
		}
		
		if(next.clicked()) {
			pageNum++;
			if(pageNum > pageCount) pageNum = 1;
		}
		
		//unique page contents / assets
		
		switch(pageNum) {
		case 1:
			if(guidePrev.clicked()) {
				guidePN--;
				if(guidePN < 1) guidePN = guidePC;
			}
			
			if(guideNext.clicked()) {
				guidePN++;
				if(guidePN > guidePC) guidePN = 1;
			}
			
			break;
		case 2:
			for(int i = 0; i < itemInfo.length; i++)
				if(itemInfo[i].clicked()) glossDisp.setItemDisplay(i);
			
			break;
		case 3:
			for(int i = 0; i < mobDisplay.length; i++)
				if(mob_walk[i].isActivated())
					mobDisplay[i] = manager.getIAsset().getMob()[i][mob_walk[i].getFrame()];
			for(int i = 0; i < mobInfo.length; i++) 
				if(mobInfo[i].clicked()) glossDisp.setMobDisplay(i);
			
			break;
		case 5:
			if(about.clicked()) {
				if(!showAbout) {
					showAbout = true;
					about.setTitle("Hide About");
				}else{
					showAbout = false;
					about.setTitle("Show About");
				}
			}
			
			//about section toggle
			
			if(showAbout) {
				if(aboutPrev.clicked()) {
					aboutPN--;
					if(aboutPN < 1) aboutPN = aboutPC;
				}
				
				if(aboutNext.clicked()) {
					aboutPN++;
					if(aboutPN > aboutPC) aboutPN = 1;
				}
			}
			
			break;
		default: break; }
	}

	public void render(Graphics g) {
		//backdrop
		
		g.setColor(new Color(30, 30, 30));
		g.fillRect(0, 0, 1280, 720);
		
		g.setFont(manager.getFont(2));
		g.setColor(new Color(200, 200, 200));
		
		//page contents
		
		renderPageContents(g);
		
		g.setFont(manager.getFont(2));
		g.setColor(new Color(0, 200, 0));
		manager.getSM().centre("Page " + pageNum + " / " + pageCount + "  -  " + pageTitle, 640, 80, g);
		
		//universal manual buttons
		
		menu.render(g);
		previous.render(g);
		next.render(g);
	}
	
	public void renderPageContents(Graphics g) {
		switch(pageNum) {
		case 1:
			pageTitle = "Instructional Guide";
			
			//controls and simple guide
			
			g.drawString("Gameplay Guide: ", 100, 150);
			g.drawString("In Game Controls: ", 700, 150);
			
			g.fillRect(78, 178, 574, 384);
			g.setColor(Color.BLACK);
			g.fillRect(80, 180, 570, 380);
			
			g.setFont(manager.getFont(1));
			g.setColor(new Color(200, 200, 200));
			
			//controls
			
			g.drawString("Movement - WASD / Arrow Keys", 700, 190);
			g.drawString("Pause Game - Esc", 700, 220);
			g.drawString("Upgrades Menu - U", 700, 250);
			
			//gameplay guide
			
			switch(guidePN) {
			case 1:
				g.drawString("        The goal of this game is to achieve the highest score possible", 100, 220);
				g.drawString("before your Space Exploration Vehicle is inevitably decommissioned.", 100, 240);
				
				g.drawString("        To gain points, you must direct the SEV to collect useful objects", 100, 270);
				g.drawString("from your surroundings or eradicate the endless hordes of hostiles", 100, 290);
				g.drawString("that plague the area.", 100, 310);
				
				g.drawString("        Move your rover onto a tile containing an item to collect it or", 100, 340);
				g.drawString("onto a tile occupied by a mob to attack it. Items grant points and", 100, 360);
				g.drawString("provide a number of varying resources or upgrades for your rover.", 100, 380);
				g.drawString("Upon encountering a hostile unit, it will attack your SEV. In this", 100, 400);
				g.drawString("iteration of the game, mobs will die upon coming into contact with the", 100, 420);
				g.drawString("rover. If a hostile attack brings your SEV's HP to 0, then your rover", 100, 440);
				g.drawString("will be destroyed and the game will end. Attacking mobs gives you", 100, 460);
				g.drawString("varying amounts of points and scrap, which can be used to purchase", 100, 480);
				g.drawString("upgrades for the SEV.", 100, 500);
				
				break;
			case 2:
				g.drawString("         As your score increases, the difficulty of the stage will as well.", 100, 220);
				g.drawString("After hitting a certain point threshold, you will transition into the next", 100, 240);
				g.drawString("stage. Each stage contains more mobs and items than the last and", 100, 260);
				g.drawString("every now and then, new mobs and items will be introduced.", 100, 280);
				
				g.drawString("        After collecting enough scrap from mobs or pickups, you can buy", 100, 310);
				g.drawString("upgrades for your SEV. These purchases have a variety of effects on", 100, 330);
				g.drawString("the rover, ranging from a health increase to damage reduction. One", 100, 350);
				g.drawString("upgrade that will increase in importance as the game advances is the", 100, 370);
				g.drawString("battery upgrade, which offsets the gradually increasing energy drain", 100, 390);
				g.drawString("of the game. If your battery reaches 0, you will lose contact with your", 100, 410);
				g.drawString("rover and the game will end.", 100, 430);
				
				break;
			default: break; }
			
			g.setFont(manager.getFont(2));
			manager.getSM().centre(guidePN + " / " + guidePC, 365, 540, g);
			
			guidePrev.render(g);
			guideNext.render(g);
			
			break;
		case 2:
			pageTitle = "Item Glossary";
			
			//item selection menu
			
			for(int i = 0; i < itemInfo.length; i++) itemInfo[i].render(g);
			
			g.drawImage(iAsset.getItem()[0][0], 158, 150, 64, 32, null);
			g.drawImage(iAsset.getItem()[0][1], 258, 150, 64, 32, null);
			g.drawImage(iAsset.getItem()[0][2], 358, 150, 64, 32, null);
			g.drawImage(iAsset.getItem()[1][0], 458, 150, 64, 32, null);
			g.drawImage(iAsset.getItem()[1][1], 558, 150, 64, 32, null);
			g.drawImage(iAsset.getItem()[1][2], 658, 150, 64, 32, null);
			g.drawImage(iAsset.getItem()[2][0], 758, 136, 64, 64, null);
			g.drawImage(iAsset.getItem()[2][1], 158, 236, 64, 64, null);
			g.drawImage(iAsset.getItem()[2][2], 258, 236, 64, 64, null);
			
			g.setFont(manager.getFont(1));
			
			//side display
			
			g.setColor(new Color(0, 0, 0));
			glossDisp.render(g);
			
			break;
		case 3:
			pageTitle = "Mob Glossary";
			
			//moving unit selection menu
			
			for(int i = 0; i < mobInfo.length; i++) mobInfo[i].render(g);
			
			g.drawImage(mobDisplay[0], 158, 150, 64, 32, null);
			g.drawImage(mobDisplay[1], 258, 150, 64, 32, null);
			g.drawImage(mobDisplay[2], 358, 150, 64, 32, null);
			g.drawImage(mobDisplay[3], 458, 134, 64, 64, null);
			g.drawImage(mobDisplay[4], 558, 134, 64, 64, null);
			g.drawImage(mobDisplay[5], 658, 134, 64, 64, null);
			
			//side display
			
			g.setFont(manager.getFont(1));
			
			g.setColor(new Color(0, 0, 0));
			glossDisp.render(g);
		
			break;
		case 4:
			pageTitle = "Additional Information";
			
			g.drawString("Current Version: " + manager.getVersionAdv(), 100, 150);
			g.drawString("Known Problems / Bugs", 740, 150);
			
			g.setFont(manager.getFont(1));
			g.drawString("SEV: Max HP [100] / Max Battery [10,000]", 100, 200);
			g.drawString("Upgrade costs:", 100, 240);
			g.drawString("Engine: 2/5/10/17/26", 100, 270);
			g.drawString("Storage: 3/7/11/15/19", 100, 290);
			g.drawString("Battery: 5/10/15/20/25", 100, 310);
			g.drawString("Armor: 25", 100, 330);
			g.drawString("Capacity: 1/2/3... [Increases every 10 purchases]", 100, 350);
			g.drawString("Chassis: 1/2/3... [Increases every 5 purchases]", 100, 370);
			g.drawString("Repair: 2", 100, 390);
			
			g.drawString("Pressing 3 or 4 arrow keys at once will cause the program to", 740, 200);
			g.drawString("ignore input from the UP/LEFT arrow keys [80105]", 740, 220);
			g.drawString("SEV can drift through corners when moving diagonally [80109]", 740, 250);
			g.drawString("Game appears to speed up/slow down periodically (requires", 740, 280);
			g.drawString("further investigation) [80119]", 740, 300);
			g.drawString("Some mobs glitch out when respawning [80122]", 740, 330);
			
			break;
		case 5:
			pageTitle = "Credits and About";
			
			g.drawString("Created by TheBitspud in affiliation with Quiet Classroom", 90, 150);
			
			if(showAbout) {
				g.fillRect(78, 178, 774, 334);
				g.setColor(Color.BLACK);
				g.fillRect(80, 180, 770, 330);
				
				g.setFont(manager.getFont(1));
				g.setColor(new Color(200, 200, 200));
				
				switch(aboutPN) {
				case 1:
					g.drawString("        Remnants of the Outage (originally known as Project: Command) evolved from a simple idea:", 100, 220);
					g.drawString("You [the protagonist] are stuck on an isolated vessel, with the rest of the crew having disappeared", 100, 240);
					g.drawString("under mysterious conditions. You manage to establish contact with an outside source that has the", 100, 260);
					g.drawString("resources to guide you to safety, but you can only communicate with them through a voice channel.", 100, 280);
					g.drawString("The craft isn’t necessarily safe either, with genetically augmented pests, depleting resources, and", 100, 300);
					g.drawString("potentially even abnormal occurrences or an alien threat jeopardizing your situation.", 100, 320);
					
					g.drawString("        I had been thinking about this idea for a while, considering all the possibilities such as a role", 100, 360);
					g.drawString("reversal or establishing contact with a pilotable drone instead of a rescuer. This was also around the", 100, 380);
					g.drawString("time when the Ludum Dare 39 competition was underway with the theme [Running out of Power].", 100, 400);
					g.drawString("Sure enough, this concept continued to evolve, eventually paving the way for Project: Command’s", 100, 420);
					g.drawString("official start of development on Aug. 8, 2017 [v.70808].", 100, 440);
					
					break;
				case 2:
					g.drawString("        Around the time when I began work on the project, I went on a hiking trip with a friend and a", 100, 220);
					g.drawString("few others. This acquaintance of mine assisted me in refining my ideas for this concept of a game", 100, 240);
					g.drawString("during that time, and eventually assisted in developing a starting spritesheet while I was working", 100, 260);
					g.drawString("on the Java framework for Project: Command. Although we were operating as a team at the start,", 100, 280);
					g.drawString("I ultimately decided to work on this program as a solo developer as neither of us had the initiative", 100, 300);
					g.drawString("nor the commitment to effectively cooperate on the game.", 100, 320);
					
					g.drawString("        Ever since, I have been working intermittently on the project and have logged a total of 32", 100, 360);
					g.drawString("sessions over the course of seven months. After going on a hiatus in September ended up lasting for", 100, 380);
					g.drawString("over three months, I eventually found the time and motivation to return to developing the game.", 100, 400);
					
					g.drawString("        -TheBitspud [Updated Mar. 2018]", 100, 440);
					
					break;
				default: break; }
			
				g.setFont(manager.getFont(2));
				manager.getSM().centre(aboutPN + " / " + aboutPC, 465, 490, g);
				
				aboutPrev.render(g);
				aboutNext.render(g);
			}
			
			about.render(g);
			
			break;
		default: break; }
	}
}