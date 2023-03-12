package object;


import character.Characters;
import main.PlayingCanvas;

public class Obj_Heart extends Characters{
	PlayingCanvas pc;
	public Obj_Heart(PlayingCanvas pc){
		super(pc);
		this.pc=pc;
		type = type_pickupONLY;
		name = "Srdco";
		value= 2;
		down1 = setup("/objects/heart_full",pc.rectSize,pc.rectSize);
		image = setup("/objects/heart_full",pc.rectSize,pc.rectSize);
		image2= setup("/objects/heart_half",pc.rectSize,pc.rectSize);
		image3= setup("/objects/heart_blank",pc.rectSize,pc.rectSize);
	}
	
	public boolean use(Characters character) {
		pc.playSE(2);
		pc.ui.addMess("Srdcia +" + value );
		character.life += value;
		return true;
	}
}
