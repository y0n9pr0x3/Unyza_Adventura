package main;


public class Events {
	PlayingCanvas pc;
	EventRect eventRect[][];
	int previoEveX, previoEveY;
	boolean canTouchEve = true;
	
	public Events(PlayingCanvas pc) {
		this.pc=pc;
		eventRect = new EventRect[pc.maxWorldCol][pc.maxWorldRow];
		
		int col=0;
		int row=0;
		
		while(col < pc.maxWorldCol && row < pc.maxWorldRow) {
			eventRect[col][row] = new EventRect();
			eventRect[col][row].x = 23;
			eventRect[col][row].y = 23;
			eventRect[col][row].width =2;
			eventRect[col][row].height=2;
			eventRect[col][row].eventRectDefX = eventRect[col][row].x;
			eventRect[col][row].eventRectDefY = eventRect[col][row].y;
			
			col++;
			if(col == pc.maxWorldCol) {
				col =0;
				row++;
			}
		}
	}
	
	public void checkEvents() {
		//checking player and events
		int xDis = Math.abs(pc.player.worldX - previoEveX);
		int yDis = Math.abs(pc.player.worldY - previoEveY);
		int dis = Math.max(xDis, yDis);
		if(dis > pc.rectSize) {
			canTouchEve = true;
		}
		
		if(canTouchEve == true) {
			
			if(hit(23,18,"any") == true) {
				damagePit(23,18,pc.dialogState);
			}
			if(hit(19,18,"any") == true) {
				teleport(pc.dialogState);
			}
			if(hit(20,18,"any") == true) {
				healingPool(20,18,pc.dialogState);
			}
			
		}
	}
	
	public boolean hit(int col,int row,String reqDirection) {
		boolean hit = false;
		
		pc.player.solidRect.x= pc.player.worldX+pc.player.solidRect.x;
		pc.player.solidRect.y= pc.player.worldY+pc.player.solidRect.y;
		
		eventRect[col][row].x = col*pc.rectSize + eventRect[col][row].x;
		eventRect[col][row].y = row*pc.rectSize + eventRect[col][row].y;
		
		if(pc.player.solidRect.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
			if(pc.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;
				
				previoEveX = pc.player.worldX;
				previoEveY = pc.player.worldY;
				
			}
		}
		pc.player.solidRect.x= pc.player.solidRectDefaultX;
		pc.player.solidRect.y= pc.player.solidRectDefaultY;
		eventRect[col][row].x = eventRect[col][row].eventRectDefX;
		eventRect[col][row].y = eventRect[col][row].eventRectDefY;
		
		return hit;
	}
	
	public void damagePit(int col,int row, int gameState) {
		pc.gameState = gameState;
		pc.ui.currentDialogue = "Padol si do jamy!";
		pc.playSE(6);
		pc.player.life -=1;
		//eventRect[col][row].eventDone =true;
		canTouchEve = false;
	}
	
	public void healingPool(int col,int row, int gamestate) {
		if(pc.keyI.enterPress == true) {
			pc.gameState = gamestate;
			pc.player.attackCancel = true;
			pc.playSE(2);
			pc.ui.currentDialogue = "Doplnil si si životy!";
			pc.player.life = pc.player.maxLife;
		}
	}
	
	public void teleport(int gamestate) {
		pc.gameState = gamestate;
		pc.ui.currentDialogue = "Teleport!";
		pc.player.worldX = pc.rectSize * 25;
		pc.player.worldY = pc.rectSize * 18;
		pc.dObject.setMon();
	}
	
}
