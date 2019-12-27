package net.tbs.cmd.game.entities.items;

import net.tbs.cmd.main.Manager;

//item that gives scrap without requiring combat

public class ScrapMaterial extends Item {
	private String type;
	
	public ScrapMaterial(int x, int y, Manager manager, String type) {
		super(x, y, 32, 32, manager);
		
		this.type = type;
	}

	public void setStats() {
		ID = 102;
		
		if(type.equals("electronic")) texture = manager.getIAsset().getItem()[ID -100][1];
		else if(type.equals("engine")) texture = manager.getIAsset().getItem()[ID -100][2];
		else texture = manager.getIAsset().getItem()[ID -100][0];
		
		setRenderPos();
	}
	
	public void obtainItem() {
		if(type.equals("electronic")) {
			stage.getScrap().setQuantity(3);
			game.getScore().setPoints(7);
		}else if(type.equals("engine")){
			stage.getScrap().setQuantity(9);
			game.getScore().setPoints(20);
		}else{
			stage.getScrap().setQuantity(1);
			game.getScore().setPoints(2);
		}
	}
}