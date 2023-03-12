package character;

import java.util.Random;

import main.PlayingCanvas;

public class Npc_profesor1 extends Characters{
	
	public Npc_profesor1(PlayingCanvas pc) {
		super(pc);
		direction= "down";
		speed = 1;
		getViewNpc();
		setDialog();
	}
	
	public void getViewNpc() {
		up1 = setup("/npc/oldman_up_1",pc.rectSize,pc.rectSize);
		up2 = setup("/npc/oldman_up_2",pc.rectSize,pc.rectSize);
		down1 = setup("/npc/oldman_down_1",pc.rectSize,pc.rectSize);
		down2 = setup("/npc/oldman_down_2",pc.rectSize,pc.rectSize);
		left1 = setup("/npc/oldman_left_1",pc.rectSize,pc.rectSize);
		left2 = setup("/npc/oldman_left_2",pc.rectSize,pc.rectSize);
		right1 = setup("/npc/oldman_right_1",pc.rectSize,pc.rectSize);
		right2 = setup("/npc/oldman_right_2",pc.rectSize,pc.rectSize);
		
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
		dialogues[0] = "Zdravíčko, ako sa darí!";
		dialogues[1] = "Dneska to máme ale \nkrásny deň!";
		dialogues[2] = "Potrebujete s niečim \npomôcť?";
		dialogues[3] = "Pokračujte ďalej \nv hľadaní!";
	}
	
	public void speak() {
		super.speak();
	}
}
