package character;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.PlayingCanvas;
import main.Utility;

public class Characters {
	public int worldX,worldY;
	public int speed;
	
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	public BufferedImage attackUP1,attackUP2 ,attackDown1, attackDown2, attackLeft1,attackLeft2,attackRight1,attackRight2;
	public String direction = "down";
	PlayingCanvas pc;
	
	public int spriteCounter= 0;
	public int spriteNum = 1;
	public ArrayList<Characters> inventory = new ArrayList<>();
	public final int inventorSize = 20;
	public Rectangle solidRect = new Rectangle(0,0,48,48);
	public Rectangle attackArea = new Rectangle(0,0,0,0);
	public int solidRectDefaultX , solidRectDefaultY;
	public boolean collisionOn = false;
	public int actionCount =0;
	public String dialogues[] = new String[20];
	public int dialogInd = 0;
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	public boolean invincibl = false;
	public boolean attacking = false;
	public int inviCount = 0;
	public boolean alive = true;
	public boolean dying = false;
	public int dyingCount = 0;
	public boolean hpBar = false;
	public int hpCount = 0;
	public int shotAvail = 0;
	public boolean stackable = false;
	public int amount=1;
	public int type;
	public boolean onPath = false;
	public boolean knockback = false;
	public int knockbackCoun = 0;
	public int knockBackPower = 0;
	public int useCost;
	
	//character status
	public int maxLife;
	public int life;
	public int level;
	public int maxMana;
	public int defaul_speed;
	public int mana;
	public int ammo;
	public int strength;
	public int attack;
	public int dexterity;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public int price;
	public Characters currentWeapon;
	public Characters currentShield;
	public Projectile projectile;
	
	public int attackValue;
	public int defenseValue;
	public String description = "";
	
	public int value;
	public final int type_player=0;
	public final int type_npc=1;
	public final int type_monster=2;
	public final int type_sword=3;
	public final int type_axe=4;
	public final int type_shield=5;
	public final int type_consumable=6; // jedlé či to je
	public final int type_pickupONLY=7;
	public final int type_door=8;
	public final int type_obstacle = 9;
	public final int type_light=10;
	
	public Characters(PlayingCanvas pc) {
		this.pc = pc ;
	}
	
	public BufferedImage setup(String imagePath, int width, int height) {
		Utility utility = new Utility();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath+ ".png"));
			image = utility.scaleImage(image,width, height);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public BufferedImage setupMon(String imagePath, int width, int height) {
		Utility utility = new Utility();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath+ ".png"));
			image = utility.scaleImage(image,width, height);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public void setAction() {
		
	}
	
	public boolean use(Characters character) {
		return false;
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
		
		
		if(knockback == true) {
			checkCollision();
			if(collisionOn == true) {
				knockbackCoun=0;
				knockback =false;
				speed= defaul_speed;
			}
			else if(collisionOn == false){
				switch(pc.player.direction) {
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
			knockbackCoun++;
			if(knockbackCoun == 20) {
				knockbackCoun = 0;
				knockback = false;
				speed = defaul_speed;
			}
			
		}else {
			setAction();
			checkCollision();
			
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
		}
		
		
		
		spriteCounter++;
		if(spriteCounter > 24) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter=0;
		}
		
		//invincible
		if(invincibl == true) {
			inviCount++;
			if(inviCount > 40) {
				invincibl = false;
				inviCount=0;
			}
		}
		
		//fire
		if(shotAvail < 30) {
			shotAvail ++;	
		}
	}
	
	public void dyingAnim(Graphics2D g2) {
		dyingCount++;
		int i = 5;
		
		if(dyingCount <= i) {
			changeAlpha(g2, 0f);
		}
		if(dyingCount > i && dyingCount <= i*2) {
			changeAlpha(g2, 1f);
		}
		if(dyingCount > i*2 && dyingCount <= i*3) {
			changeAlpha(g2, 0f);
		}
		if(dyingCount > i*3 && dyingCount <= i*4) {
			changeAlpha(g2, 1f);
		}
		if(dyingCount > i*4 && dyingCount <= i*5) {
			changeAlpha(g2, 0f);
		}
		if(dyingCount > i*5 && dyingCount <= i*6) {
			changeAlpha(g2, 1f);
		}
		if(dyingCount > i*6 && dyingCount <= i*7) {
			changeAlpha(g2, 0f);
		}
		if(dyingCount > i*7 && dyingCount <= i*8) {
			changeAlpha(g2, 1f);
		}
		if(dyingCount > i*8) {
			alive=false;
		}
	}
	
	public void interact() {
		
	}
	
	
	
	public int getDetected(Characters user, Characters target[][],String targetName) {
		int index=999;
		
		int nextWorldX= user.getLeftX();
		int nextWorldY= user.getTopY();
		
		switch(user.direction) {
		case "up": nextWorldY = user.getTopY()-1; break;
		case "down": nextWorldY= user.getBottomY()+1; break;
		case "left": nextWorldX= user.getLeftX()-1;break;
		case "right": nextWorldX= user.getRightX()+1;break;
		}
		
		int col = nextWorldX/pc.rectSize;
		int row = nextWorldY/pc.rectSize;
		;
		for(int i =0; i < target[1].length; i++) {
			if(target[pc.currentMap][i] != null) {
				if(target[pc.currentMap][i].getCol() == col && 
						target[pc.currentMap][i].getRow() == row &&
						target[pc.currentMap][i].name.equals(targetName)) {
					index=i;
					break;
				}
			}
		}
		return index;
	}
	
	
	public void checkCollision() {

		collisionOn = false;
		pc.collisionM.checkRect(this);
		pc.collisionM.checkObject(this, false);
		pc.collisionM.checkCharacters(this, pc.npc);
		pc.collisionM.checkCharacters(this, pc.mon);
		pc.collisionM.checkCharacters(this, pc.iRect);
		boolean contactPlayer = pc.collisionM.checkPlayer(this);
		
		if(this.type == type_monster && contactPlayer == true) {
			damagePlayer(attack);
		}
	}
	
	public int getLeftX() {
		return worldX + solidRect.x;
	}
	
	public int getRightX() {
		return worldX + solidRect.x + solidRect.width;
	}
	
	public int getTopY() {
		return worldY + solidRect.y;
	}
	
	public int getBottomY() {
		return worldY + solidRect.y + solidRect.height;
	}
	
	public int getCol() {
		return (worldX + solidRect.x)/pc.rectSize;
	}
	
	public int getRow() {
		return (worldY + solidRect.y)/pc.rectSize;
	}
	
	public void damageReact() {
		
	}
	
	public void changeAlpha(Graphics2D g2, float alphaValue){
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
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
			
			
			//mon hp bar
			if(type == 2 && hpBar == true) {
				double oneScale = (double)pc.rectSize / maxLife;
				double hpBarValue = oneScale * life;
				
				g2.setColor(new Color(35,35,30));
				g2.fillRect(screenX-1, screenY -16, pc.rectSize +2, 12);
				
				g2.setColor(new Color(255,0,30));
				g2.fillRect(screenX, screenY -15, (int)hpBarValue, 10);
				
				hpCount++;
				
				if(hpCount > 600) {
					hpCount = 0;
					hpBar = false;
				}
			}
			
			
			
			if(invincibl == true) {
				hpBar = true;
				hpCount = 0;
				changeAlpha(g2, 0.4f);
			}
			if(dying == true) {
				dyingAnim(g2);
			}
			
			g2.drawImage(image, screenX, screenY ,pc.rectSize, pc.rectSize, null);
			
			changeAlpha(g2, 1f);
		}
	}
	
	public void checkDrop() {
		
	}
	
	public void dropItem(Characters droppedItem) {
		for(int i = 0; i < pc.obj[1].length; i++) {
			if(pc.obj[pc.currentMap][i] == null) {
				pc.obj[pc.currentMap][i] = droppedItem;
				pc.obj[pc.currentMap][i].worldX = worldX; //dead monster worldX
				pc.obj[pc.currentMap][i].worldY = worldY;
				break;
			}
		}
	}
	
	public void damagePlayer(int attack) {
		if(pc.player.invincibl == false) {
			pc.playSE(6);
			int damage = attack - pc.player.defense;
			if (damage <0) {
				damage=0;
			}
			pc.player.life -= damage;
			pc.player.invincibl = true;
		}
	}
	
	
	public void searchPath(int goalCol, int goalRow) {
		int startCol = (worldX + solidRect.x)/pc.rectSize;
		int startRow = (worldY + solidRect.y)/pc.rectSize;
		
		pc.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);
		
		if(pc.pFinder.search() == true) {
			int nextX = pc.pFinder.pathList.get(0).col * pc.rectSize;
			int nextY = pc.pFinder.pathList.get(0).row * pc.rectSize;
			
			int enLeftX= worldX + solidRect.x;
			int enRightX= worldX + solidRect.x +solidRect.width;
			int enTopY= worldY + solidRect.y;
			int enBottomY= worldY + solidRect.y +solidRect.height;
			
			if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + pc.rectSize) {
				direction = "up";
			}
			else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + pc.rectSize) {
				direction = "down";
			}
			else if(enTopY >= nextY && enBottomY <= nextX  + pc.rectSize) {
				if(enLeftX > nextX) {
					direction = "left";
				}
				if(enLeftX < nextX) {
					direction = "right";
				}
			}
			else if(enTopY > nextY && enLeftX > nextX) {
				direction = "up";
				checkCollision();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			else if(enTopY > nextY && enLeftX < nextX) {
				direction = "up";
				checkCollision();
				if(collisionOn == true) {
					direction = "right";
				}
			}
			else if(enTopY < nextY && enLeftX > nextX) {
				direction = "down";
				checkCollision();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			else if(enTopY < nextY && enLeftX < nextX) {
				direction = "down";
				checkCollision();
				if(collisionOn == true) {
					direction = "right";
				}
			}
			//int nextCol = pc.pFinder.pathList.get(0).col;
			//int nextRow =pc.pFinder.pathList.get(0).row;
			//if(nextCol == goalCol && nextRow == goalRow) {
			//	onPath = false;
			//}
		}
	}
	
}
