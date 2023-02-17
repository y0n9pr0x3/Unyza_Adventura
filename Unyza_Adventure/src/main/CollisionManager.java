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
			rectNum1 = pc.rectM.mapRectNum[characterLeftCol][characterTopRow];
			rectNum2= pc.rectM.mapRectNum[characterRightCol][characterTopRow];
			if(pc.rectM.rect[rectNum1].collision == true || pc.rectM.rect[rectNum2].collision == true) {
				character.collisionOn = true;
			}
			break;
		case "down":
			characterBottomRow = (characterBottomWorldY + character.speed)/pc.rectSize;
			rectNum1 = pc.rectM.mapRectNum[characterLeftCol][characterBottomRow];
			rectNum2= pc.rectM.mapRectNum[characterRightCol][characterBottomRow];
			if(pc.rectM.rect[rectNum1].collision == true || pc.rectM.rect[rectNum2].collision == true) {
				character.collisionOn = true;
			}
			break;
		case "left":
			characterLeftCol = (characterLeftWorldX - character.speed)/pc.rectSize;
			rectNum1 = pc.rectM.mapRectNum[characterLeftCol][characterTopRow];
			rectNum2= pc.rectM.mapRectNum[characterLeftCol][characterBottomRow];
			if(pc.rectM.rect[rectNum1].collision == true || pc.rectM.rect[rectNum2].collision == true) {
				character.collisionOn = true;
			}
			break;
		case "right":
			characterRightCol = (characterRightWorldX + character.speed)/pc.rectSize;
			rectNum1 = pc.rectM.mapRectNum[characterRightCol][characterTopRow];
			rectNum2= pc.rectM.mapRectNum[characterRightCol][characterBottomRow];
			if(pc.rectM.rect[rectNum1].collision == true || pc.rectM.rect[rectNum2].collision == true) {
				character.collisionOn = true;
			}
			break;
		}
	}
	
	
	public int checkObject(Characters character, boolean player) {
		int index = 999;
		
		for(int i = 0; i < pc.obj.length; i++) {
			if(pc.obj[i] !=null) {
				
				//get characters solid rect position
				character.solidRect.x = character.worldX + character.solidRect.x;
				character.solidRect.y = character.worldY + character.solidRect.y;
				
				//get the objects solid rect position
				pc.obj[i].solidRect.x = pc.obj[i].worldX + pc.obj[i].solidRect.x;
				pc.obj[i].solidRect.y = pc.obj[i].worldY + pc.obj[i].solidRect.y;
				
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
				
				if(character.solidRect.intersects(pc.obj[i].solidRect)) {
					if(pc.obj[i].collision == true) {
						character.collisionOn=true;
					}
					if(player == true) {
						index = i;
					}
				}
				
				character.solidRect.x = character.solidRectDefaultX;
				character.solidRect.y = character.solidRectDefaultY;
				pc.obj[i].solidRect.x = pc.obj[i].solidRectDefaultX;
				pc.obj[i].solidRect.y = pc.obj[i].solidRectDefaultY;
			}
		}
		
		return index;
	}
}
