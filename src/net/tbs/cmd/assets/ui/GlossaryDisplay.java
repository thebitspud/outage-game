package net.tbs.cmd.assets.ui;

import net.tbs.cmd.main.Manager;

//contains the descriptions for the side displays in the player's manual

public class GlossaryDisplay extends InfoDisplay {
	public GlossaryDisplay(Manager manager) {
		super(896, 116, 305, 448, 17, 28, 3, manager);
	}
	
	//mob descriptions
	
	public void setMobDisplay(int index) {
		switch(index) {
		case 0:
			clear();
			
			addLine("Large Rat");
			addSpace();
			
			addLine("Attack: 1");
			addLine("Agility: 1");
			addSpace();
			
			addLine("Point Value: 2");
			addLine("Scrap: +1");
			addSpace();
			
			addLine("Mob ID: 0");
			addLine("AI: Default");
			addLine("First Seen: Stage 1");
			
			break;
		case 1:
			clear();
			
			addLine("Roach");
			addSpace();

			addLine("Attack: 2");
			addLine("Agility: 5");
			addSpace();
			
			addLine("Point Value: 5");
			addLine("Scrap: +1");
			addSpace();
			
			addLine("Mob ID: 1");
			addLine("AI: Default");
			addLine("First Seen: Stage 2");
			
			break;
		case 2:
			clear();
			
			addLine("Mutant Rat");
			addSpace();

			addLine("Attack: 3");
			addLine("Agility: 2");
			addSpace();
			
			addLine("Point Value: 7");
			addLine("Scrap: +2");
			addSpace();
			
			addLine("Mob ID: 2");
			addLine("AI: Default");
			addLine("First Seen: Stage 3");
			
			break;
		case 3:
			clear();
			
			addLine("Crawler");
			addSpace();

			addLine("Attack: 5");
			addLine("Agility: 3");
			addSpace();
			
			addLine("Point Value: 12");
			addLine("Scrap: +3");
			addSpace();
			
			addLine("Mob ID: 3");
			addLine("AI: Crawler");
			addLine("First Seen: Stage 5");
			
			break;
		case 4:
			clear();
			
			addLine("Feeder");
			addSpace();

			addLine("Attack: 4");
			addLine("Agility: 2 [Passive] / 3 [Seeking]");
			addLine("Seeking Range: 4");
			addSpace();
			
			addLine("Point Value: 10");
			addLine("Scrap: +2");
			addSpace();
			
			addLine("Mob ID: 4");
			addLine("AI: Feeder");
			addLine("First Seen: Stage 7");
			
			break;
		case 5:
			clear();
			
			addLine("Apex Feeder");
			addSpace();

			addLine("Attack: 7");
			addLine("Agility: 2 [Passive] / 4 [Seeking]");
			addLine("Seeking Range: 7");
			addSpace();
			
			addLine("Point Value: 17");
			addLine("Scrap: +4");
			addSpace();
			
			addLine("Mob ID: 5");
			addLine("AI: Feeder");
			addLine("First Seen: Stage 9");
			
			break;
		default:
			clear();
			
			addLine("No information available");
			
			break;
		}
	}
	
	//item descriptions
	
	public void setItemDisplay(int index) {
		switch(index) {
		case 0:
			clear();
			
			addLine("Power Cell (Default)");
			addSpace();
			
			addLine("Point Value: 1");
			addLine("Battery: +25");
			addSpace();
			
			addLine("ItemID: 100");
			addLine("First Seen: Stage 1");
			
			break;
		case 1:
			clear();
			
			addLine("Power Cell (Enhanced)");
			addSpace();
			
			addLine("Point Value: 4");
			addLine("Battery: +100");
			addSpace();
			
			addLine("ItemID: 100.1");
			addLine("First Seen: Stage 2");
			
			break;
		case 2:
			clear();
			
			addLine("Power Bank");
			addSpace();
			
			addLine("Point Value: 5");
			addLine("Battery: +50");
			addLine("Battery Packs: +1");
			addSpace();
			
			addLine("ItemID: 100.2");
			addLine("First Seen: Stage 2");
			
			break;
		case 3:
			clear();
			
			addLine("Repair Kit (Default)");
			addSpace();
			
			addLine("Point Value: 1");
			addLine("Health: +1");
			addSpace();
			
			addLine("ItemID: 101");
			addLine("First Seen: Stage 1");
			
			break;
		case 4:
			clear();
			
			addLine("Repair Kit (Enhanced)");
			addSpace();
			
			addLine("Point Value: 3");
			addLine("Health: +3");
			addSpace();
			
			addLine("ItemID: 101.1");
			addLine("First Seen: Stage 2");
			
			break;
		case 5:
			clear();
			
			addLine("Repair Kit (Upgraded)");
			addSpace();
			
			addLine("Point Value: 5");
			addLine("Health: +2");
			addLine("Upgrade Kits: +1");
			addSpace();
			
			addLine("ItemID: 101.2");
			addLine("First Seen: Stage 2");
			
			break;
		case 6:
			clear();
			
			addLine("Metal Scrap");
			addSpace();
			
			addLine("Point Value: 2");
			addLine("Scrap: +1");
			addSpace();
			
			addLine("ItemID: 102");
			addLine("First Seen: Stage 1");
			
			break;
		case 7:
			clear();
			
			addLine("Electronic Scrap");
			addSpace();
			
			addLine("Point Value: 7");
			addLine("Scrap: +3");
			addSpace();
			
			addLine("ItemID: 102.1");
			addLine("First Seen: Stage 5");
			
			break;
		case 8:
			clear();
			
			addLine("Engine Component");
			addSpace();
			
			addLine("Point Value: 20");
			addLine("Scrap: +9");
			addSpace();
			
			addLine("ItemID: 102.2");
			addLine("First Seen: Stage 9");
			
			break;
		default:
			clear();
			
			addLine("No information available");
			
			break;
		}
	}
}