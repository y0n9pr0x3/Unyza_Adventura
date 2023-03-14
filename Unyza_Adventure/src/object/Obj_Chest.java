package object;


import character.Characters;
import main.PlayingCanvas;

public class Obj_Chest extends Characters{
	
	public Obj_Chest(PlayingCanvas pc){
		super(pc);
		
		name = "Truhlos";
		down1 = setup("/objects/chest",pc.rectSize,pc.rectSize);
	}
}
