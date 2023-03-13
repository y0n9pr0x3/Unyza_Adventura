package rects_intera;

import character.Characters;
import main.PlayingCanvas;

public class Interact_tree extends InteractiveRect{
	PlayingCanvas pc;
	
	public Interact_tree(PlayingCanvas pc,int col , int row) {
		super(pc,col ,row);
		this.pc=pc;
		
		this.worldX = pc.rectSize * col;
		this.worldY = pc.rectSize * row;
		
		down1 = setup("/rects_interact/drytree1",pc.rectSize,pc.rectSize);
		destruct= true;
		life = 3;
	}
	
	public boolean isAxe(Characters character) {
		
		boolean isAxe=false;
		
		if(character.currentWeapon.type == type_axe) {
			isAxe=true;
		}
		
		return isAxe;
	}
	
	public void playSE() {
		pc.playSE(11);
	}
	
	public InteractiveRect getDownTree() {
		InteractiveRect rect= new Interact_trunk(pc, worldX/pc.rectSize, worldY/pc.rectSize);
		return rect;
	}

}
