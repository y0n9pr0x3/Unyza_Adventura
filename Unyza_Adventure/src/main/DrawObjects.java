package main;

import object.OBJ_Flasa_Borovicky;
import object.OBJ_Potion_Red;
import object.Obj_Axe;
import object.Obj_BeerAmmo;
import object.Obj_Chest;
import object.Obj_Coin;
import object.Obj_Door;
import object.Obj_Heart;
import object.Obj_Key;
import object.Obj_Shield_fire;
import rects_intera.Interact_tree;
import character.Npc_Trader;
import character.Npc_profesor1;
import monster.Mon_slime_1;

public class DrawObjects {
	
	PlayingCanvas pc;
	
	public DrawObjects(PlayingCanvas pc) {
		this.pc = pc;
	}
	
	public void setObject() {
		int i = 0;
		int mapNum = 0;
		pc.obj[mapNum][i] = new Obj_Key(pc);
		pc.obj[mapNum][i].worldX = pc.rectSize * 16;
		pc.obj[mapNum][i].worldY = pc.rectSize * 24;
		i++;
		pc.obj[mapNum][i] = new Obj_Door(pc);
		pc.obj[mapNum][i].worldX = pc.rectSize * 13;
		pc.obj[mapNum][i].worldY = pc.rectSize * 21;
		i++;
		pc.obj[mapNum][i] = new Obj_Door(pc);
		pc.obj[mapNum][i].worldX = pc.rectSize * 19;
		pc.obj[mapNum][i].worldY = pc.rectSize * 28;
		i++;
		pc.obj[mapNum][i] = new Obj_Door(pc);
		pc.obj[mapNum][i].worldX = pc.rectSize * 19;
		pc.obj[mapNum][i].worldY = pc.rectSize * 21;
		i++;
		pc.obj[mapNum][i] = new Obj_Axe(pc);
		pc.obj[mapNum][i].worldX = pc.rectSize * 30;
		pc.obj[mapNum][i].worldY = pc.rectSize * 29;
		i++;
		pc.obj[mapNum][i] = new Obj_Shield_fire(pc);
		pc.obj[mapNum][i].worldX = pc.rectSize * 30;
		pc.obj[mapNum][i].worldY = pc.rectSize * 30;
		i++;
		pc.obj[mapNum][i] = new OBJ_Potion_Red(pc);
		pc.obj[mapNum][i].worldX = pc.rectSize * 32;
		pc.obj[mapNum][i].worldY = pc.rectSize * 33;
		i++;
		pc.obj[mapNum][i] = new OBJ_Potion_Red(pc);
		pc.obj[mapNum][i].worldX = pc.rectSize * 32;
		pc.obj[mapNum][i].worldY = pc.rectSize * 32;
		i++;
		pc.obj[mapNum][i] = new Obj_Coin(pc);
		pc.obj[mapNum][i].worldX = pc.rectSize * 34;
		pc.obj[mapNum][i].worldY = pc.rectSize * 32;
		i++;
		pc.obj[mapNum][i] = new Obj_Heart(pc);
		pc.obj[mapNum][i].worldX = pc.rectSize * 35;
		pc.obj[mapNum][i].worldY = pc.rectSize * 32;
		i++;
		pc.obj[mapNum][i] = new Obj_BeerAmmo(pc);
		pc.obj[mapNum][i].worldX = pc.rectSize * 36;
		pc.obj[mapNum][i].worldY = pc.rectSize * 32;
		
		
		mapNum = 3;
		pc.obj[mapNum][i] = new Obj_Door(pc);
		pc.obj[mapNum][i].worldX = pc.rectSize * 26;
		pc.obj[mapNum][i].worldY = pc.rectSize * 14;
		i++;
		pc.obj[mapNum][i] = new Obj_Door(pc);
		pc.obj[mapNum][i].worldX = pc.rectSize * 21;
		pc.obj[mapNum][i].worldY = pc.rectSize * 14;
		i++;
		pc.obj[mapNum][i] = new Obj_Door(pc);
		pc.obj[mapNum][i].worldX = pc.rectSize * 13;
		pc.obj[mapNum][i].worldY = pc.rectSize * 10;
		i++;
		pc.obj[mapNum][i] = new Obj_Door(pc);
		pc.obj[mapNum][i].worldX = pc.rectSize * 8;
		pc.obj[mapNum][i].worldY = pc.rectSize * 10;
		i++;
		pc.obj[mapNum][i] = new Obj_Chest(pc);
		pc.obj[mapNum][i].worldX = pc.rectSize * 26;
		pc.obj[mapNum][i].worldY = pc.rectSize * 9;
		i++;
		pc.obj[mapNum][i] = new Obj_Chest(pc);
		pc.obj[mapNum][i].worldX = pc.rectSize * 21;
		pc.obj[mapNum][i].worldY = pc.rectSize * 9;
		i++;
		pc.obj[mapNum][i] = new Obj_Chest(pc);
		pc.obj[mapNum][i].worldX = pc.rectSize * 16;
		pc.obj[mapNum][i].worldY = pc.rectSize * 9;
		i++;
	}
	
	public void setNpc() {
		int i = 0;
		int mapNum = 0;
		pc.npc[mapNum][i] = new Npc_profesor1(pc);
		pc.npc[mapNum][i].worldX = pc.rectSize * 29;
		pc.npc[mapNum][i].worldY = pc.rectSize * 21;
		
		mapNum = 1;
		pc.npc[mapNum][i] = new Npc_Trader(pc);
		pc.npc[mapNum][i].worldX = pc.rectSize * 19;
		pc.npc[mapNum][i].worldY = pc.rectSize * 8;
	}
	
	public void setInterRect() {
		int i = 0;
		int mapNum = 0;
		
		pc.iRect[mapNum][i] = new Interact_tree(pc,13,24);
		pc.iRect[mapNum][i].worldX = pc.rectSize *13;
		pc.iRect[mapNum][i].worldY = pc.rectSize *24;
		i++;
		
		pc.iRect[mapNum][i] = new Interact_tree(pc,14,24);
		pc.iRect[mapNum][i].worldX = pc.rectSize *14;
		pc.iRect[mapNum][i].worldY = pc.rectSize *24;
		i++;
		pc.iRect[mapNum][i] = new Interact_tree(pc,15,24);
		pc.iRect[mapNum][i].worldX = pc.rectSize *15;
		pc.iRect[mapNum][i].worldY = pc.rectSize *24;
		i++;
		pc.iRect[mapNum][i] = new Interact_tree(pc,17,24);
		pc.iRect[mapNum][i].worldX = pc.rectSize *17;
		pc.iRect[mapNum][i].worldY = pc.rectSize *24;
		i++;
		pc.iRect[mapNum][i] = new Interact_tree(pc,18,24);
		pc.iRect[mapNum][i].worldX = pc.rectSize *18;
		pc.iRect[mapNum][i].worldY = pc.rectSize *24;
		i++;
		pc.iRect[mapNum][i] = new Interact_tree(pc,19,24);
		pc.iRect[mapNum][i].worldX = pc.rectSize *19;
		pc.iRect[mapNum][i].worldY = pc.rectSize *24;
		i++;
		pc.iRect[mapNum][i] = new Interact_tree(pc,20,22);
		pc.iRect[mapNum][i].worldX = pc.rectSize *20;
		pc.iRect[mapNum][i].worldY = pc.rectSize *22;
		i++;
		pc.iRect[mapNum][i] = new Interact_tree(pc,20,23);
		pc.iRect[mapNum][i].worldX = pc.rectSize *20;
		pc.iRect[mapNum][i].worldY = pc.rectSize *23;
		i++;
		pc.iRect[mapNum][i] = new Interact_tree(pc,20,24);
		pc.iRect[mapNum][i].worldX = pc.rectSize *20;
		pc.iRect[mapNum][i].worldY = pc.rectSize *24;
		i++;
		pc.iRect[mapNum][i] = new Interact_tree(pc,20,25);
		pc.iRect[mapNum][i].worldX = pc.rectSize *20;
		pc.iRect[mapNum][i].worldY = pc.rectSize *25;
		i++;
		pc.iRect[mapNum][i] = new Interact_tree(pc,20,26);
		pc.iRect[mapNum][i].worldX = pc.rectSize *20;
		pc.iRect[mapNum][i].worldY = pc.rectSize *26;
		i++;
		pc.iRect[mapNum][i] = new Interact_tree(pc,20,27);
		pc.iRect[mapNum][i].worldX = pc.rectSize *20;
		pc.iRect[mapNum][i].worldY = pc.rectSize *27;
		i++;
	}
	
	public void setMon() {
		int i = 0;
		int mapNum = 0;
		pc.mon[mapNum][i] = new Mon_slime_1(pc);
		pc.mon[mapNum][i].worldX = pc.rectSize * 25;
		pc.mon[mapNum][i].worldY = pc.rectSize * 25;
		i++;
		pc.mon[mapNum][i] = new Mon_slime_1(pc);
		pc.mon[mapNum][i].worldX = pc.rectSize * 26;
		pc.mon[mapNum][i].worldY = pc.rectSize * 26;
		i++;
		pc.mon[mapNum][i] = new Mon_slime_1(pc);
		pc.mon[mapNum][i].worldX = pc.rectSize * 27;
		pc.mon[mapNum][i].worldY = pc.rectSize * 27;
	}
}
