package object;


import character.Characters;
import main.PlayingCanvas;

public class Obj_Key extends Characters{
	PlayingCanvas pc;
	public Obj_Key(PlayingCanvas pc){
		super(pc);
		this.pc= pc;
		type = type_consumable;
		name = "Kľúčik";
		down1 = setup("/objects/key",pc.rectSize,pc.rectSize);
		description = "[" + name +"]\nAj tajomnú komnatu \nsním odemkneš!";
		price=10;
	}
	
	
	public boolean use(Characters character) {
		pc.gameState = pc.dialogState;
		
		int objIndex= getDetected(character, pc.obj,"Dvere");
		
		if(objIndex != 999) {
			pc.ui.currentDialogue = "Odemkol si dvere, vyzuj \nse a pokračuj dál.";
			pc.playSE(3);
			pc.obj[pc.currentMap][objIndex] = null;
			return true;
		}else {
			pc.ui.currentDialogue= "Vidíš dvere? Ne? tak čo \nskúšaš!";
			return false;
		}
	}
	
}