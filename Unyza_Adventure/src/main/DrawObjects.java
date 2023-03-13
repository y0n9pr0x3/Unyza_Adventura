package main;

import object.OBJ_Flasa_Borovicky;
import object.OBJ_Potion_Red;
import object.Obj_Axe;
import object.Obj_BeerAmmo;
import object.Obj_Coin;
import object.Obj_Door;
import object.Obj_Heart;
import object.Obj_Key;
import object.Obj_Shield_fire;
import rects_intera.Interact_tree;
import character.Npc_profesor1;
import monster.Mon_slime_1;

public class DrawObjects {
	
	PlayingCanvas pc;
	
	public DrawObjects(PlayingCanvas pc) {
		this.pc = pc;
	}
	
	public void setObject() {
		int i = 0;
		pc.obj[i] = new Obj_Door(pc);
		pc.obj[i].worldX = pc.rectSize * 23;
		pc.obj[i].worldY = pc.rectSize * 24;
		i++;
		pc.obj[i] = new Obj_Key(pc);
		pc.obj[i].worldX = pc.rectSize * 28;
		pc.obj[i].worldY = pc.rectSize * 28;
		i++;
		pc.obj[i] = new Obj_Key(pc);
		pc.obj[i].worldX = pc.rectSize * 29;
		pc.obj[i].worldY = pc.rectSize * 29;
		i++;
		pc.obj[i] = new Obj_Axe(pc);
		pc.obj[i].worldX = pc.rectSize * 30;
		pc.obj[i].worldY = pc.rectSize * 29;
		i++;
		pc.obj[i] = new Obj_Shield_fire(pc);
		pc.obj[i].worldX = pc.rectSize * 30;
		pc.obj[i].worldY = pc.rectSize * 30;
		i++;
		pc.obj[i] = new OBJ_Potion_Red(pc);
		pc.obj[i].worldX = pc.rectSize * 32;
		pc.obj[i].worldY = pc.rectSize * 33;
		i++;
		pc.obj[i] = new OBJ_Potion_Red(pc);
		pc.obj[i].worldX = pc.rectSize * 32;
		pc.obj[i].worldY = pc.rectSize * 32;
		i++;
		pc.obj[i] = new Obj_Coin(pc);
		pc.obj[i].worldX = pc.rectSize * 34;
		pc.obj[i].worldY = pc.rectSize * 32;
		i++;
		pc.obj[i] = new Obj_Heart(pc);
		pc.obj[i].worldX = pc.rectSize * 35;
		pc.obj[i].worldY = pc.rectSize * 32;
		i++;
		pc.obj[i] = new Obj_BeerAmmo(pc);
		pc.obj[i].worldX = pc.rectSize * 36;
		pc.obj[i].worldY = pc.rectSize * 32;
		
		
	}
	
	public void setNpc() {
		int i = 0;
		pc.npc[i] = new Npc_profesor1(pc);
		pc.npc[i].worldX = pc.rectSize * 29;
		pc.npc[i].worldY = pc.rectSize * 21;
		
	}
	
	public void setInterRect() {
		int i = 0;
		pc.iRect[i] = new Interact_tree(pc,15,15);
		pc.iRect[i].worldX = pc.rectSize *15;
		pc.iRect[i].worldY = pc.rectSize *15;
		
	}
	
	public void setMon() {
		int i = 0;
		pc.mon[i] = new Mon_slime_1(pc);
		pc.mon[i].worldX = pc.rectSize * 25;
		pc.mon[i].worldY = pc.rectSize * 25;
		i++;
		pc.mon[i] = new Mon_slime_1(pc);
		pc.mon[i].worldX = pc.rectSize * 26;
		pc.mon[i].worldY = pc.rectSize * 26;
		i++;
		pc.mon[i] = new Mon_slime_1(pc);
		pc.mon[i].worldX = pc.rectSize * 27;
		pc.mon[i].worldY = pc.rectSize * 27;
	}
}
