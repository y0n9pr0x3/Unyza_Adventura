package rects_intera;

import main.PlayingCanvas;

public class Interact_trunk extends InteractiveRect{
	
	PlayingCanvas pc;
	
	public Interact_trunk(PlayingCanvas pc,int col , int row) {
		super(pc,col ,row);
		this.pc=pc;
		
		this.worldX = pc.rectSize * col;
		this.worldY = pc.rectSize * row;
		
		down1 = setup("/rects_interact/trunk",pc.rectSize,pc.rectSize);
		destruct= true;
		
		solidRect.x=0;
		solidRect.y=0;
		solidRect.width=0;
		solidRect.height=0;
		solidRectDefaultX=solidRect.x;
		solidRectDefaultY=solidRect.y;
	}
}
