package object;

import character.Characters;
import main.PlayingCanvas;

public class Obj_Shield_basic extends Characters{

	public Obj_Shield_basic(PlayingCanvas pc) {
		super(pc);
		
		type = type_shield;
		name= "Ochrana I. Stupňa";
		down1= setup("/objects/shield_wood",pc.rectSize, pc.rectSize);
		defenseValue = 1;
		description = "[" + name +"]\nBlbuvzdorný štít I.\ngenerácie.";
		price=6;
	}
	

}
