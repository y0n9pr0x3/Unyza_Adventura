package object;

import character.Characters;
import main.PlayingCanvas;

public class Obj_Boots extends Characters{
	
	public Obj_Boots(PlayingCanvas pc){
		super(pc);
		
		name = "Jordany";
		down1 = setup("/objects/boot.png",pc.rectSize,pc.rectSize);
		description = "[" + name +"]\nBro, nové jordany!";
		
	}
}
