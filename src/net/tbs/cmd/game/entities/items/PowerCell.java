package net.tbs.cmd.game.entities.items;

import net.tbs.cmd.main.Manager;

//item that restores battery life upon usage

public class PowerCell extends Item {
	private String type;
	
	public PowerCell(int x, int y, Manager manager, String type) {
		super(x, y, 32, 16, manager);
		
		this.type = type;
	}
	
	public void setStats() {
		ID = 100;
		
		vOffset = 8;
		
		if(type.equals("enhanced")) texture = manager.getIAsset().getItem()[ID -100][1];
		else if(type.equals("powerbank")) texture = manager.getIAsset().getItem()[ID -100][2];
		else texture = manager.getIAsset().getItem()[ID - 100][0];
		
		setRenderPos();
	}
	
	public void obtainItem() {
		if(type.equals("enhanced")) {
			stage.getBattery().setQuantity(100);
			game.getScore().setPoints(4);
		}else if(type.equals("powerbank")) {
			stage.getBattery().setQuantity(50);
			stage.getBPack().setQuantity(1);
			game.getScore().setPoints(5);
		}else{
			stage.getBattery().setQuantity(25);
			game.getScore().setPoints(1);
		}
	}
}