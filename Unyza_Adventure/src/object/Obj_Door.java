package object;


import character.Characters;
import main.PlayingCanvas;

public class Obj_Door extends Characters{
	PlayingCanvas pc;
	public Obj_Door(PlayingCanvas pc){
		super(pc);
		this.pc = pc;
		
		name = "Dvere";
		down1 = setup("/objects/door",pc.rectSize,pc.rectSize);
		collision = true;
		type= type_obstacle;
		
		solidRect.x = 0;
		solidRect.y = 16;
		solidRectDefaultX = solidRect.x;
		solidRectDefaultY = solidRect.y;
		solidRect.width = 48;
		solidRect.height = 32;
	}
	
	
	public void interact() {
		pc.gameState = pc.dialogState;
		pc.ui.currentDialogue= "Šaňo, zabudel zebrac kĺúča \nnajprv ho zežeň.";
	}

}
