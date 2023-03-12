package object;


import character.Characters;
import main.PlayingCanvas;

public class Obj_Key extends Characters{
	
	public Obj_Key(PlayingCanvas pc){
		super(pc);
		name = "Kľúčik";
		down1 = setup("/objects/key",pc.rectSize,pc.rectSize);
		description = "[" + name +"]\nAj tajomnú komnatu \nsním odemkneš!";
	}
}