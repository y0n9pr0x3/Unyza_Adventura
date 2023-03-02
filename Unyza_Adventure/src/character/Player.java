package character;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.KeyInputs;
import main.PlayingCanvas;
import main.Utility;
import rects.Rect;

public class Player extends Characters	{
	PlayingCanvas pc;
	KeyInputs keyI;
	
	public final int screenX;
	public final int screenY;
	//public int hasKey = 0;
	
	public Player(PlayingCanvas pc, KeyInputs keyI) {
		super(pc);
		this.pc = pc;
		this.keyI = keyI;
		
		screenX = pc.screenWidth/2 - (pc.rectSize /2);
		screenY = pc.screenHeight/2- (pc.rectSize /2);
		
		solidRect = new Rectangle();
		solidRect.x = 8;
		solidRect.y = 16;
		solidRectDefaultX = solidRect.x;
		solidRectDefaultY = solidRect.y;
		solidRect.width = 32;
		solidRect.height = 32;
		
		setDefaultVal();
		getViewPlayer();
	}
	public void setDefaultVal() {
		worldX= pc.rectSize * 23;
		worldY= pc.rectSize * 21;
		speed=4;
		direction = "down";
	}
	
	public void getViewPlayer() {
		up1 = setup("/player/boy_up_1");
		up2 = setup("/player/boy_up_2");
		down1 = setup("/player/boy_down_1");
		down2 = setup("/player/boy_down_2");
		left1 = setup("/player/boy_left_1");
		left2 = setup("/player/boy_left_2");
		right1 = setup("/player/boy_right_1");
		right2 = setup("/player/boy_right_2");
		
	}

	public void update() {
		
		if(keyI.upPress == true || keyI.downPress == true || keyI.leftPress == true || keyI.rightPress == true) {
			
			if(keyI.upPress == true) {
				direction = "up";
			}
			else if(keyI.downPress == true) {
				direction = "down";
			}
			else if(keyI.leftPress == true) {
				direction = "left";
			}
			else if(keyI.rightPress == true) {
				direction = "right";
			}
			
			//check rect coll
			collisionOn = false;
			pc.collisionM.checkRect(this);
			
			//check object collision
			int objIndex = pc.collisionM.checkObject(this, true);
			pickUpObj(objIndex);
			
			//check npc collision
			int npcIndex = pc.collisionM.checkCharacters(this, pc.npc);
			interactNpc(npcIndex);
			
			if(collisionOn == false) {
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter=0;
			}
		}
	}
	
	public void pickUpObj(int i) {
		if(i != 999) {
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image= up1;
			}
			if(spriteNum == 2) {
				image= up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image= down1;
			}
			if(spriteNum == 2) {
				image= down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image= left1;
			}
			if(spriteNum == 2) {
				image= left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image= right1;
			}
			if(spriteNum == 2) {
				image= right2;
			}
			break;
			
		}
		g2.drawImage(image, screenX, screenY, null);
	}
	
	public void interactNpc(int i) {
		if(i != 999) {
			if(pc.keyI.enterPress == true) {
				pc.gameState = pc.dialogState;
				pc.npc[i].speak();
			}
		}
		pc.keyI.enterPress = false;
	}
	
}
