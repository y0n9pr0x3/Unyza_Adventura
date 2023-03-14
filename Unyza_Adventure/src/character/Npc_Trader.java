package character;

import java.util.Random;

import main.PlayingCanvas;
import object.OBJ_Flasa_Borovicky;
import object.OBJ_Potion_Red;
import object.Obj_Axe;
import object.Obj_Key;
import object.Obj_Shield_fire;
import object.Obj_Sword_basic;

public class Npc_Trader extends Characters{
	
	public Npc_Trader(PlayingCanvas pc) {
		super(pc);
		direction= "down";
		//speed = 1;
		getViewNpc();
		setDialog();
		setItems();
	}
	
	public void getViewNpc() {
		up1 = setup("/npc/merchant_down_1",pc.rectSize,pc.rectSize);
		up2 = setup("/npc/merchant_down_2",pc.rectSize,pc.rectSize);
		down1 = setup("/npc/merchant_down_1",pc.rectSize,pc.rectSize);
		down2 = setup("/npc/merchant_down_2",pc.rectSize,pc.rectSize);
		left1 = setup("/npc/merchant_down_1",pc.rectSize,pc.rectSize);
		left2 = setup("/npc/merchant_down_2",pc.rectSize,pc.rectSize);
		right1 = setup("/npc/merchant_down_1",pc.rectSize,pc.rectSize);
		right2 = setup("/npc/merchant_down_2",pc.rectSize,pc.rectSize);
		
	}
	
	
	public void setAction() {
		actionCount++;
		
		if(actionCount > 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1;
			if(i<=25) {
				direction="up";
			}
			if(i>25 && i <= 50) {
				direction="down";
			}
			if(i>50 && i <= 75) {
				direction="left";
			}
			if(i>75 && i <= 100) {
				direction="right";
			}
			actionCount =0;
		}
	}
	
	
	public void setDialog() {
		dialogues[0] = "Szia bro, mám super \nmatroš keby chceš vybavím \nhop-šup prášek!";
	}
	
	public void setItems() {
		inventory.add(new OBJ_Potion_Red(pc));
		inventory.add(new Obj_Shield_fire(pc));
		inventory.add(new Obj_Sword_basic(pc));
		inventory.add(new Obj_Key(pc));
		inventory.add(new Obj_Axe(pc));
		//inventory.add(new OBJ_Lantern(gs));
		//inventory.add(new OBJ_Tent(gs));
		//inventory.add(new OBJ_ManaBEER(gs));
		//inventory.add(new Obj_Flasa_Vodky(gs));
		inventory.add(new OBJ_Flasa_Borovicky(pc));
		
	}
	
	public void speak() {
		super.speak();
		pc.gameState = pc.tradingState;
		pc.ui.npc= this;
	}
}
