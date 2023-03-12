package object;

import character.Characters;
import main.PlayingCanvas;

public class Obj_BeerAmmo extends Characters{
	PlayingCanvas pc;
	public Obj_BeerAmmo(PlayingCanvas pc) {
		super(pc);
		this.pc = pc;
		
		type= type_pickupONLY;
		value = 1;
		name = "Pifko municia";
		down1 = setup("/objects/beer_full", pc.rectSize, pc.rectSize);
		image = setup("/objects/beer_full", pc.rectSize, pc.rectSize);
		image2 = setup("/objects/beer_blank", pc.rectSize, pc.rectSize);
	}
	
	
	public boolean use(Characters character) {
		pc.playSE(2);
		pc.ui.addMess("Pifko +" + value );
		character.mana += value;
		return true;
	}

}
