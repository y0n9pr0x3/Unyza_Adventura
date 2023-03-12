package object;


import character.Characters;
import main.PlayingCanvas;

public class OBJ_Flasa_Borovicky extends Characters{
	
	PlayingCanvas pc;
	public OBJ_Flasa_Borovicky(PlayingCanvas pc) {
		super(pc);
		this.pc=pc;
		type= type_consumable;
		name="Borovička";
		value=4;
		price=15;
		down1= setup("/objects/flasa_boroviky",pc.rectSize,pc.rectSize);
		description = "[" + name +"]\nIba pre samovrahou,\nkonzumácia zakázaná!";
	}
	
	public boolean use(Characters character) {
		pc.gameState = pc.dialogState;
		pc.ui.currentDialogue= "Cíí, vágo mocný elixír.";
		character.speed += 10;
		pc.playSE(2);
		return true;
	}
}
