package object;

import character.Characters;
import main.PlayingCanvas;

public class OBJ_Potion_Red extends Characters{
	PlayingCanvas pc;
	public OBJ_Potion_Red(PlayingCanvas pc) {
		super(pc);
		this.pc = pc;
		
		type= type_consumable;
		name="Gumidžús";
		value=2;
		price=10;
		down1= setup("/objects/potion_red",pc.rectSize,pc.rectSize);
		description = "[" + name +"]\nŠe, napi a budeš \nskákac jak gumkáč!";
	}
	
	public boolean use(Characters character) {
		pc.gameState = pc.dialogState;
		pc.ui.currentDialogue= "Piješ  "+name+"!\n" + "Tvoje živoci boli\nobnovené.";
		character.life = character.maxLife;
		pc.playSE(2);
		return true;
	}
}
