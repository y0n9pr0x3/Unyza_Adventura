package object;

import character.Characters;
import main.PlayingCanvas;

public class Obj_Sword_basic extends Characters{

	public Obj_Sword_basic(PlayingCanvas pc) {
		super(pc);
		
		type = type_sword;
		name= "Vreckový nožíček";
		down1= setup("/objects/sword_normal",pc.rectSize,pc.rectSize);
		attackValue = 1;
		attackArea.width= 36;
		attackArea.height=36;
		description = "[" + name +"]\nPozor, \nostrí jak čert!";
		price=6;
		knockBackPower = 2;
	}

}
