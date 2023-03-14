package object;

import character.Characters;
import main.PlayingCanvas;

public class Obj_Axe extends Characters{

	public Obj_Axe(PlayingCanvas pc) {
		super(pc);
		
		type = type_axe;
		name = "Sekeru";
		down1= setup("/objects/axe",pc.rectSize,pc.rectSize);
		attackValue = 2;
		attackArea.width= 30;
		attackArea.height=30;
		description = "[" + name +"]\nVeľmi dobrá keď, \nti strom zavadzá.";
		price=20;
		knockBackPower = 8;
	}

}
