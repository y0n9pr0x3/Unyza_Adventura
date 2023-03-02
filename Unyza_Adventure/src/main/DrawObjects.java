package main;

import object.Obj_Chest;
import object.Obj_Coin;
import object.Obj_Door;
import object.Obj_Key;
import character.Npc_profesor1;
import object.Obj_Boots;

public class DrawObjects {
	
	PlayingCanvas pc;
	
	public DrawObjects(PlayingCanvas pc) {
		this.pc = pc;
	}
	
	public void setObject() {
		
		
	}
	
	public void setNpc() {
		pc.npc[0] = new Npc_profesor1(pc);
		pc.npc[0].worldX = pc.rectSize * 21;
		pc.npc[0].worldY = pc.rectSize * 21;
		
	}
}
