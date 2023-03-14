package main;

import character.Characters;

public class Events {
	PlayingCanvas pc;
	EventRect eventRect[][][];
	int previoEveX, previoEveY;
	boolean canTouchEve = true;
	int tempMap, tempCol , tempRow;
	
	public Events(PlayingCanvas pc) {
		this.pc=pc;
		eventRect = new EventRect[pc.maxMap][pc.maxWorldCol][pc.maxWorldRow];
		
		int map=0;
		int col=0;
		int row=0;
		
		while(map < pc.maxMap && col < pc.maxWorldCol && row < pc.maxWorldRow) {
			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = 23;
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].width =2;
			eventRect[map][col][row].height=2;
			eventRect[map][col][row].eventRectDefX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefY = eventRect[map][col][row].y;
			
			col++;
			if(col == pc.maxWorldCol) {
				col =0;
				row++;
				
				if(row == pc.maxWorldRow) {
					row =0;
					map++;
				}
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
			
			if(hit(0,23,18,"any") == true) {damagePit(pc.dialogState);}
			
			else if(hit(0,13,21,"any")== true) {teleport(1,9,12);}
			
			else if(hit(1,9,12,"any")== true) {teleport(0,13,21);}
			else if(hit(1,9,13,"any")== true) {teleport(0,13,21);}
			
			else if(hit(1,19,10,"any")== true) {speak(pc.npc[1][0]);}
			
			
			else if(hit(0,19,28,"any")== true) {teleport(2,21,12);}
			
			else if(hit(2,21,12,"any")== true) {teleport(0,19,28);}
			else if(hit(2,21,13,"any")== true) {teleport(0,19,28);}
			
			
			else if(hit(2,10,7,"any")== true) {teleport(3,27,12);}
			
			else if(hit(3,27,12,"any")== true) {teleport(2,10,7);}
			
		}
	}
	
	public boolean hit(int map, int col,int row,String reqDirection) {
		boolean hit = false;
		
		if(map == pc.currentMap) {
			pc.player.solidRect.x= pc.player.worldX+pc.player.solidRect.x;
			pc.player.solidRect.y= pc.player.worldY+pc.player.solidRect.y;
			
			eventRect[map][col][row].x = col*pc.rectSize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row*pc.rectSize + eventRect[map][col][row].y;
			
			if(pc.player.solidRect.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
				if(pc.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hit = true;
					
					previoEveX = pc.player.worldX;
					previoEveY = pc.player.worldY;
					
				}
			}
			pc.player.solidRect.x= pc.player.solidRectDefaultX;
			pc.player.solidRect.y= pc.player.solidRectDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefY;
		}
		
		return hit;
	}
	
	public void damagePit(int gameState) {
		pc.gameState = gameState;
		pc.ui.currentDialogue = "Padol si do jamy!";
		pc.playSE(6);
		pc.player.life -=1;
		//eventRect[col][row].eventDone =true;
		canTouchEve = false;
	}
	
	public void healingPool(int gamestate) {
		if(pc.keyI.enterPress == true) {
			pc.gameState = gamestate;
			pc.player.attackCancel = true;
			pc.playSE(2);
			pc.ui.currentDialogue = "Doplnil si si životy!";
			pc.player.life = pc.player.maxLife;
		}
	}
	
	/*
	public void teleport(int gamestate) {
		pc.gameState = gamestate;
		pc.ui.currentDialogue = "Teleport!";
		pc.player.worldX = pc.rectSize * 25;
		pc.player.worldY = pc.rectSize * 18;
		pc.dObject.setMon();
	}
	*/
	
	public void teleport(int map, int col , int row) {
		pc.gameState = pc.transitionState;
		tempMap= map;
		tempCol= col;
		tempRow= row;
		
		
		//pc.currentMap = map;
		//pc.player.worldX = pc.rectSize*col;
		//pc.player.worldY = pc.rectSize*row;
		//previoEveX = pc.player.worldX;
		//previoEveY= pc.player.worldY;
		canTouchEve = false;
		pc.playSE(13);
		
	}
	
	public void speak(Characters character) {
		if(pc.keyI.enterPress == true) {
			pc.gameState = pc.dialogState;
			pc.player.attackCancel = true;
			character.speak();
		}
	}
	
}
