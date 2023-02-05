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
}
