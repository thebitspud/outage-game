package net.tbs.cmd.game.entities.items;

import net.tbs.cmd.main.Manager;

//item that repairs SEV damage and may grant other bonuses

public class RepairKit extends Item {
	private String type;
	
	public RepairKit(int x, int y, Manager manager, String type) {
		super(x, y, 32, 16, manager);
		
		this.type = type;
	}
	
	public void setStats() {
		ID = 101;
		
		vOffset = 8;
		
		if(type.equals("enhanced")) texture = manager.getIAsset().getItem()[ID -100][1];
		else if(type.equals("upgraded")) texture = manager.getIAsset().getItem()[ID -100][2];
		else texture = manager.getIAsset().getItem()[ID -100][0];
		
		setRenderPos();
	}

	public void obtainItem() {
		if(type.equals("upgraded")) {
			stage.getSEV().setHP(2);
			stage.getUKit().setQuantity(1);
			game.getScore().setPoints(5);
		}else if(type.equals("enhanced")) {
			stage.getSEV().setHP(3);
			game.getScore().setPoints(3);
		}else{
			stage.getSEV().setHP(1);
			game.getScore().setPoints(1);
		}
	}
}