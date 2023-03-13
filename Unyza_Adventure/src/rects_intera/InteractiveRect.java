package rects_intera;

import character.Characters;
import main.PlayingCanvas;

public class InteractiveRect extends Characters{
	
	PlayingCanvas pc;
	public boolean destruct = false;
	public InteractiveRect(PlayingCanvas pc,int col , int row) {
		super(pc);
		this.pc=pc;
		
	}
	
	public boolean isAxe(Characters character) {
		
		boolean isAxe=false;
		
		return isAxe;
	}
	
	public void playSE() {
		
	}
	
	public InteractiveRect getDownTree() {
		InteractiveRect rect= null;
		return rect;
	}
	
	public void update() {
		
		if(invincibl == true) {
			inviCount++;
			if(inviCount >20) {
				invincibl= false;
				inviCount = 0;
			}
		}
		
	}
	

}
