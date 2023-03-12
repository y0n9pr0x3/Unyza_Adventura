package object;


import character.Characters;
import main.PlayingCanvas;

public class Obj_Coin extends Characters{
	PlayingCanvas pc;
	public Obj_Coin(PlayingCanvas pc){
		super(pc);
		this.pc=pc;
		
		name="Uniza dolár";
		type = type_pickupONLY;
		down1= setup ("/objects/coin_uniza",pc.rectSize,pc.rectSize);
		value=10;
	}
	
	public boolean use(Characters character) {
		pc.playSE(1);
		pc.ui.addMess("Lovákos +" +value);
		pc.player.coin +=value;
		return true;
	}
}
