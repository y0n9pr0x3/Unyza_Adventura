package main;

import object.Obj_Chest;
import object.Obj_Coin;
import object.Obj_Door;
import object.Obj_Key;
import object.Obj_Boots;

public class DrawObjects {
	
	PlayingCanvas pc;
	
	public DrawObjects(PlayingCanvas pc) {
		this.pc = pc;
	}
	
	public void setObject() {
		
		pc.obj[0] = new Obj_Key();
		pc.obj[0].worldX = 23 * pc.rectSize;
		pc.obj[0].worldY = 7 * pc.rectSize;
		
		pc.obj[1] = new Obj_Key();
		pc.obj[1].worldX = 23 * pc.rectSize;
		pc.obj[1].worldY = 40 * pc.rectSize;
		
		pc.obj[2] = new Obj_Key();
		pc.obj[2].worldX = 37 * pc.rectSize;
		pc.obj[2].worldY = 7 * pc.rectSize;
		
		pc.obj[3] = new Obj_Door();
		pc.obj[3].worldX = 10 * pc.rectSize;
		pc.obj[3].worldY = 11 * pc.rectSize;
		
		pc.obj[4] = new Obj_Door();
		pc.obj[4].worldX = 8 * pc.rectSize;
		pc.obj[4].worldY = 28 * pc.rectSize;
		
		pc.obj[5] = new Obj_Door();
		pc.obj[5].worldX = 12 * pc.rectSize;
		pc.obj[5].worldY = 22 * pc.rectSize;
		
		pc.obj[6] = new Obj_Chest();
		pc.obj[6].worldX = 10 * pc.rectSize;
		pc.obj[6].worldY = 7 * pc.rectSize;
		
		pc.obj[7] = new Obj_Boots();
		pc.obj[7].worldX = 37 * pc.rectSize;
		pc.obj[7].worldY = 42 * pc.rectSize;
		
	}
}
