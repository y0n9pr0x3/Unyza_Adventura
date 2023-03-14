package main;

import character.Characters;

public class CollisionManager {
	PlayingCanvas pc;
	
	public CollisionManager(PlayingCanvas pc) {
		this.pc = pc;
	}
	
	public void checkRect(Characters character) {
		
		// find row and col
		int characterLeftWorldX = character.worldX + character.solidRect.x;
		int characterRightWorldX = character.worldX + character.solidRect.x + character.solidRect.width;
		int characterTopWorldY = character.worldY + character.solidRect.y;
		int characterBottomWorldY = character.worldY + character.solidRect.y + character.solidRect.height;
		
		int characterLeftCol = characterLeftWorldX / pc.rectSize;
		int characterRightCol = characterRightWorldX / pc.rectSize;
		int characterTopRow = characterTopWorldY / pc.rectSize;
		int characterBottomRow =characterBottomWorldY / pc.rectSize;
		
		int rectNum1 , rectNum2;
		
		switch(character.direction) {
		case "up":
			characterTopRow = (characterTopWorldY - character.speed)/pc.rectSize;
			rectNum1 = pc.rectM.mapRectNum[pc.currentMap][characterLeftCol][characterTopRow];
			rectNum2= pc.rectM.mapRectNum[pc.currentMap][characterRightCol][characterTopRow];
			if(pc.rectM.rect[rectNum1].collision == true || pc.rectM.rect[rectNum2].collision == true) {
				character.collisionOn = true;
			}
			break;
		case "down":
			characterBottomRow = (characterBottomWorldY + character.speed)/pc.rectSize;
			rectNum1 = pc.rectM.mapRectNum[pc.currentMap][characterLeftCol][characterBottomRow];
			rectNum2= pc.rectM.mapRectNum[pc.currentMap][characterRightCol][characterBottomRow];
			if(pc.rectM.rect[rectNum1].collision == true || pc.rectM.rect[rectNum2].collision == true) {
				character.collisionOn = true;
			}
			break;
		case "left":
			characterLeftCol = (characterLeftWorldX - character.speed)/pc.rectSize;
			rectNum1 = pc.rectM.mapRectNum[pc.currentMap][characterLeftCol][characterTopRow];
			rectNum2= pc.rectM.mapRectNum[pc.currentMap][characterLeftCol][characterBottomRow];
			if(pc.rectM.rect[rectNum1].collision == true || pc.rectM.rect[rectNum2].collision == true) {
				character.collisionOn = true;
			}
			break;
		case "right":
			characterRightCol = (characterRightWorldX + character.speed)/pc.rectSize;
			rectNum1 = pc.rectM.mapRectNum[pc.currentMap][characterRightCol][characterTopRow];
			rectNum2= pc.rectM.mapRectNum[pc.currentMap][characterRightCol][characterBottomRow];
			if(pc.rectM.rect[rectNum1].collision == true || pc.rectM.rect[rectNum2].collision == true) {
				character.collisionOn = true;
			}
			break;
		}
	}
	
	
	public int checkObject(Characters character, boolean player) {
		int index = 999;
		
		for(int i = 0; i < pc.obj[1].length; i++) {
			if(pc.obj[pc.currentMap][i] !=null) {
				
				//get characters solid rect position
				character.solidRect.x = character.worldX + character.solidRect.x;
				character.solidRect.y = character.worldY + character.solidRect.y;
				
				//get the objects solid rect position
				pc.obj[pc.currentMap][i].solidRect.x = pc.obj[pc.currentMap][i].worldX + pc.obj[pc.currentMap][i].solidRect.x;
				pc.obj[pc.currentMap][i].solidRect.y = pc.obj[pc.currentMap][i].worldY + pc.obj[pc.currentMap][i].solidRect.y;
				
				switch(character.direction) {
				case "up":
					character.solidRect.y -= character.speed; 	//intersects = checking two rectangles if it touching
					break;
				case "down":
					character.solidRect.y += character.speed;
					break;
				case "left":
					character.solidRect.x -= character.speed;
					break;
				case "right":
					character.solidRect.x += character.speed;
					break;
				}
				
				if(character.solidRect.intersects(pc.obj[pc.currentMap][i].solidRect)) {
					if(pc.obj[pc.currentMap][i].collision == true) {
						character.collisionOn=true;
					}
					if(player == true) {
						index = i;
					}
				}
				
				character.solidRect.x = character.solidRectDefaultX;
				character.solidRect.y = character.solidRectDefaultY;
				pc.obj[pc.currentMap][i].solidRect.x = pc.obj[pc.currentMap][i].solidRectDefaultX;
				pc.obj[pc.currentMap][i].solidRect.y = pc.obj[pc.currentMap][i].solidRectDefaultY;
			}
		}
		
		return index;
	}
	
	public int checkCharacters(Characters character , Characters[][] target) {
		int index =999;
		
		for(int i = 0; i < target[1].length; i++) {
			if(target[pc.currentMap][i] !=null) {
				
				//get characters solid rect position
				character.solidRect.x = character.worldX + character.solidRect.x;
				character.solidRect.y = character.worldY + character.solidRect.y;
				
				//get the objects solid rect position
				target[pc.currentMap][i].solidRect.x = target[pc.currentMap][i].worldX + target[pc.currentMap][i].solidRect.x;
				target[pc.currentMap][i].solidRect.y = target[pc.currentMap][i].worldY + target[pc.currentMap][i].solidRect.y;
				
				switch(character.direction) {
				case "up":
					character.solidRect.y -= character.speed; 	//intersects = checking two rectangles if it touching
					break;
				case "down":
					character.solidRect.y += character.speed;
					break;
				case "left":
					character.solidRect.x -= character.speed;
					break;
				case "right":
					character.solidRect.x += character.speed;
					break;
				}
				if(character.solidRect.intersects(target[pc.currentMap][i].solidRect)) {
					if(target[pc.currentMap][i] != character) {
						character.collisionOn=true;
						index = i;
					}
				}
				character.solidRect.x = character.solidRectDefaultX;
				character.solidRect.y = character.solidRectDefaultY;
				target[pc.currentMap][i].solidRect.x = target[pc.currentMap][i].solidRectDefaultX;
				target[pc.currentMap][i].solidRect.y = target[pc.currentMap][i].solidRectDefaultY;
			}
		}
		
		return index;
	}
	
	public boolean checkPlayer(Characters character ) {
		boolean contactPlayer = false;
		//get characters solid rect position
		character.solidRect.x = character.worldX + character.solidRect.x;
		character.solidRect.y = character.worldY + character.solidRect.y;
		
		//get the objects solid rect position
		pc.player.solidRect.x = pc.player.worldX + pc.player.solidRect.x;
		pc.player.solidRect.y = pc.player.worldY + pc.player.solidRect.y;
		
		switch(character.direction) {
		case "up":
			character.solidRect.y -= character.speed; 	//intersects = checking two rectangles if it touching
			break;
		case "down":
			character.solidRect.y += character.speed;
			break;
		case "left":
			character.solidRect.x -= character.speed;
			break;
		case "right":
			character.solidRect.x += character.speed;
			break;
		}

		if(character.solidRect.intersects(pc.player.solidRect)) {
			character.collisionOn=true;
			contactPlayer = true;
		}
		
		character.solidRect.x = character.solidRectDefaultX;
		character.solidRect.y = character.solidRectDefaultY;
		pc.player.solidRect.x = pc.player.solidRectDefaultX;
		pc.player.solidRect.y = pc.player.solidRectDefaultY;
		
		return contactPlayer;
	}
}
