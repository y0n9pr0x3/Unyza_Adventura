package character;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.PlayingCanvas;
import main.Utility;

public class Characters {
	public int worldX,worldY;
	public int speed;
	
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	public String direction;
	PlayingCanvas pc;
	
	public int spriteCounter= 0;
	public int spriteNum = 1;
	public Rectangle solidRect = new Rectangle(0,0,48,48);
	public int solidRectDefaultX , solidRectDefaultY;
	public boolean collisionOn = false;
	public int actionCount =0;
	public String dialogues[] = new String[20];
	public int dialogInd = 0;
	
	
	public Characters(PlayingCanvas pc) {
		this.pc = pc ;
	}
	
	public BufferedImage setup(String imagePath) {
		Utility utility = new Utility();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath+ ".png"));
			image = utility.scaleImage(image, pc.rectSize, pc.rectSize);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public void setAction() {
		
	}
	
	public void speak() {
		if(dialogues[dialogInd] == null) {
			dialogInd =0;
		}
		pc.ui.currentDialogue = dialogues[dialogInd];
		dialogInd++;
		
		switch(direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "down";
			break;
		case "left":
			direction = "down";
			break;
		case "right":
			direction = "down";
			break;
		}
	}
	
	public void update() {
		setAction();
		
		collisionOn = false;
		pc.collisionM.checkRect(this);
		pc.collisionM.checkObject(this, false);
		pc.collisionM.checkPlayer(this);
		
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
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - pc.player.worldX + pc.player.screenX;
		int screenY = worldY - pc.player.worldY + pc.player.screenY;
		
		if(worldX + pc.rectSize > pc.player.worldX - pc.player.screenX && 
		   worldX - pc.rectSize < pc.player.worldX + pc.player.screenX &&
		   worldY + pc.rectSize > pc.player.worldY - pc.player.screenY && 
		   worldY - pc.rectSize < pc.player.worldY + pc.player.screenY) {
			
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
			g2.drawImage(image, screenX, screenY ,pc.rectSize, pc.rectSize, null);
		}
	}
}
