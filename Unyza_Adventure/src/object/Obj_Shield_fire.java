package object;

import character.Characters;
import main.PlayingCanvas;

public class Obj_Shield_fire extends Characters{

	public Obj_Shield_fire(PlayingCanvas pc) {
		super(pc);
		
		type = type_shield;
		name= "Ochrana II. Stupňa";
		down1= setup("/objects/shield_fire",pc.rectSize, pc.rectSize);
		defenseValue = 2;
		description = "[" + name +"]\nBlbuvzdorný štít I.\ngenerácie.";
		price=6;
	}

}
