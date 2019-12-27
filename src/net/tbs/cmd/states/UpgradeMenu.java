package net.tbs.cmd.states;

import java.awt.Graphics;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import net.tbs.cmd.assets.ui.TextButton;
import net.tbs.cmd.assets.ui.UpgradeDisplay;
import net.tbs.cmd.game.map.Stage;
import net.tbs.cmd.main.Manager;

////the interface you will be interacting with when purchasing upgrades

public class UpgradeMenu extends State {
	private Stage stage;
	private NumberFormat noDecimals, oneDecimal;
	
	public UpgradeMenu(Manager manager) {
		super(manager);
		
		noDecimals = new DecimalFormat("#0");
		oneDecimal = new DecimalFormat("#0.0");
	}
	
	private UpgradeDisplay engine, storage, battery, armor, capacity, chassis, repair;
	private TextButton done;

	public void init() {
		stage = manager.getGame().getStage();
		
		done = new TextButton(520, 600, 240, 60, "Done", "large", manager);
		
		engine = new UpgradeDisplay(80, 150, "Engine [" + stage.getEngineUpgrade().getCost() + "]", manager);
		storage = new UpgradeDisplay(80, 210, "Storage [" + stage.getStorageUpgrade().getCost() + "]", manager);
		battery = new UpgradeDisplay(80, 270, "Battery [" + stage.getBatteryUpgrade().getCost() + "]", manager);
		armor = new UpgradeDisplay(80, 330, "Armor [" + stage.getArmorUpgrade().getCost() + "]", manager);
		capacity = new UpgradeDisplay(640, 150, "Capacity [" + stage.getCapacityUpgrade().getCost() + "]", manager);
		chassis = new UpgradeDisplay(640, 210, "Chassis [" + stage.getChassisUpgrade().getCost() + "]", manager);
		repair = new UpgradeDisplay(640, 270, "Repair SEV [" + stage.getRepairPurchase().getCost() + "]", manager);
		
		setDisplayText();
	}
	
	public void setDisplayText() {
		engine.addLine("Decrease delays between");
		engine.addLine("actions and increase the");
		engine.addLine("SEV's movement speed");
		engine.addSpace();
		engine.addLine("Current level: " + stage.getEngineUpgrade().getTier() + " / " + stage.getEngineUpgrade().getMaxTier());
		
		storage.addLine("Expand the SEV's storage");
		storage.addLine("to increase resource capacity");
		storage.addLine("and unlock new upgrades");
		storage.addSpace();
		storage.addLine("Current level: " + stage.getStorageUpgrade().getTier() + " / " + stage.getStorageUpgrade().getMaxTier());
		
		battery.addLine("Reduce the increasingly");
		battery.addLine("prevalent effect of battery");
		battery.addLine("drainage on the SEV");
		battery.addSpace();
		battery.addLine("Current level: " + stage.getBatteryUpgrade().getTier() + " / " + stage.getBatteryUpgrade().getMaxTier());
		
		armor.addLine("Reinforce the SEV's armor");
		armor.addLine("to reduce all damage taken");
		armor.addLine("from mobs by 1");
		armor.addSpace();
		armor.addLine("Status: Not Yet Purchased");
		
		capacity.addLine("Expand the power capacity");
		capacity.addLine("of the SEV's battery with");
		capacity.addLine("scrap and a battery pack");
		capacity.addSpace();
		capacity.addLine("Current level: " + stage.getCapacityUpgrade().getTier());
		
		chassis.addLine("Use scrap and a repair kit");
		chassis.addLine("to improve the chassis and");
		chassis.addLine("increase Max HP by 1");
		chassis.addSpace();
		chassis.addLine("Current level: " + stage.getChassisUpgrade().getTier());
		
		repair.addLine("Expend some scrap to mend");
		repair.addLine("damage done and increase");
		repair.addLine("the SEV's current HP by 1");
		repair.addLine("");
		repair.addLine("Times used: " + stage.getRepairPurchase().getTier());
	}
	
	public void update() {
		if(done.clicked() || manager.getKeyboard().getKey(1, 27) || manager.getKeyboard().getKey(1, 85))
			State.setState(manager.getGameState());
		
		if(engine.buy()) {
			stage.getEngineUpgrade().purchase();
			
			if(stage.getEngineUpgrade().isPurchased()) engine.setTitle("Engine: MAX"); 
			else engine.setTitle("Engine [" + stage.getEngineUpgrade().getCost() + "]");
			
			engine.setLine(4, "Current level: " + stage.getEngineUpgrade().getTier()
					+ " / "+ stage.getEngineUpgrade().getMaxTier());
		}
		
		if(storage.buy()) {
			stage.getStorageUpgrade().purchase();
			
			if(stage.getStorageUpgrade().isPurchased()) storage.setTitle("Storage: MAX"); 
			else storage.setTitle("Storage [" + stage.getStorageUpgrade().getCost() + "]");
			
			storage.setLine(4, "Current level: " + stage.getStorageUpgrade().getTier()
					+ " / " + stage.getStorageUpgrade().getMaxTier());
		}
		
		if(battery.buy()) {
			stage.getBatteryUpgrade().purchase();
			
			if(stage.getBatteryUpgrade().isPurchased()) battery.setTitle("Battery: MAX"); 
			else battery.setTitle("Battery [" + stage.getBatteryUpgrade().getCost() + "]");
			
			battery.setLine(4, "Current level: " + stage.getBatteryUpgrade().getTier()
					+ " / " + stage.getBatteryUpgrade().getMaxTier());
		}
		
		if(armor.buy()) {
			stage.getArmorUpgrade().purchase();
			
			if(stage.getArmorUpgrade().isPurchased()) {
				armor.setTitle("Armor: Purchased");
				armor.setLine(4, "Status: Active");
			}
		}
		
		if(capacity.buy()) {
			if(stage.getBPack().getQuantity() >= 1) stage.getCapacityUpgrade().purchase();
			
			if(stage.getCapacityUpgrade().isPurchased()) capacity.setTitle("Capacity: MAX"); 
			else capacity.setTitle("Capacity [" + stage.getCapacityUpgrade().getCost() + "]");
			
			capacity.setLine(4, "Current level: " + stage.getCapacityUpgrade().getTier());
		}
		
		if(chassis.buy()) {
			if(stage.getUKit().getQuantity() >= 1) stage.getChassisUpgrade().purchase();
			
			if(stage.getChassisUpgrade().isPurchased()) chassis.setTitle("Chassis: MAX"); 
			else chassis.setTitle("Chassis [" + stage.getChassisUpgrade().getCost() + "]");
			
			chassis.setLine(4, "Current level: " + stage.getChassisUpgrade().getTier());
		}
		
		if(repair.buy()) {
			if(stage.getSEV().getHP() < stage.getSEV().getMaxHP()) stage.getRepairPurchase().purchase();
			
			repair.setLine(4, "Times used: " + stage.getRepairPurchase().getTier());
		}
	}

	public void render(Graphics g) {
		g.setFont(manager.getFont(3));
		manager.getSM().centre("Upgrades", 640, 100, g);
		
		g.setFont(manager.getFont(2));
		manager.getSM().centre("Scrap Material: " + noDecimals.format(stage.getScrap().getQuantity()) + " / "
			+ noDecimals.format(stage.getScrap().getMaxQuantity()), 640, 580, g);
		g.drawString("Battery Packs: " + noDecimals.format(stage.getBPack().getQuantity()) + " / "
			+ noDecimals.format(stage.getBPack().getMaxQuantity()), 860, 178);
		g.drawString("Upgrade Kits: " + noDecimals.format(stage.getUKit().getQuantity()) + " / "
				+ noDecimals.format(stage.getUKit().getMaxQuantity()), 860, 238);
		g.drawString("HP: " + noDecimals.format(stage.getSEV().getHP()) + " / "
				+ noDecimals.format(stage.getSEV().getMaxHP()), 860, 298);
		g.drawString("Battery: " + oneDecimal.format(stage.getBattery().getQuantity()) + " / "
				+ oneDecimal.format(stage.getBattery().getMaxQuantity()), 860, 358);
		
		//multi-stage purchase tier indicators
		
		engine.renderTierDisplay(stage.getEngineUpgrade().getTier(), stage.getEngineUpgrade().getMaxTier(), 240, g);
		storage.renderTierDisplay(stage.getStorageUpgrade().getTier(), stage.getStorageUpgrade().getMaxTier(), 240, g);
		battery.renderTierDisplay(stage.getBatteryUpgrade().getTier(), stage.getBatteryUpgrade().getMaxTier(), 240, g);
		armor.renderTierDisplay(stage.getArmorUpgrade().getTier(), stage.getArmorUpgrade().getMaxTier(), 240, g);
		
		//render the upgrade buttons and descriptions
		
		engine.render(g);
		storage.render(g);
		battery.render(g);
		armor.render(g);
		
		capacity.render(g);
		chassis.render(g);
		repair.render(g);
		
		done.render(g);
	}
}