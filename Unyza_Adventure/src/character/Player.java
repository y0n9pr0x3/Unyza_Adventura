package character;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.KeyInputs;
import main.PlayingCanvas;
import object.Obj_Fireball;
import object.Obj_Key;
import object.Obj_Shield_basic;
import object.Obj_Sword_basic;

public class Player extends Characters	{
	PlayingCanvas pc;
	KeyInputs keyI;
	
	public final int screenX;
	public final int screenY;
	public boolean attackCancel = false;
	
	
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
		getViewAttacPlayer();
		setItems();
	}
	public void setDefaultVal() {
		worldX= pc.rectSize * 25;
		worldY= pc.rectSize * 36;
		defaul_speed = 4;
		speed= defaul_speed;
		
		level=1;
		direction = "down";
		maxLife = 6;
		life=maxLife;
		maxMana = 4;
		mana=maxMana;
		strength=1;
		dexterity=1;
		exp=0;
		nextLevelExp=5;
		coin=500;
		 
		projectile = new Obj_Fireball(pc);
		currentWeapon=new Obj_Sword_basic(pc);
		currentShield= new Obj_Shield_basic(pc);
		
		
		attack= getAttack();
		defense= getDefense();
		
	}
	
	public void setItems() {
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		inventory.add(new Obj_Key(pc));
	}
	
	
	public void knockBack(Characters character, int knockBackPower) {
		character.direction = direction;
		character.speed += knockBackPower;
		character.knockback=true;
	}
	
	public int getAttack() {
		attackArea= currentWeapon.attackArea;
		return attack = strength * currentWeapon.attackValue;
	}
	
	public int getDefense() {
		return defense = dexterity * currentShield.defenseValue;
	}
	
	
	public void getViewPlayer() {
		up1 = setup("/player/boy_up_1",pc.rectSize,pc.rectSize);
		up2 = setup("/player/boy_up_2",pc.rectSize,pc.rectSize);
		down1 = setup("/player/boy_down_1",pc.rectSize,pc.rectSize);
		down2 = setup("/player/boy_down_2",pc.rectSize,pc.rectSize);
		left1 = setup("/player/boy_left_1",pc.rectSize,pc.rectSize);
		left2 = setup("/player/boy_left_2",pc.rectSize,pc.rectSize);
		right1 = setup("/player/boy_right_1",pc.rectSize,pc.rectSize);
		right2 = setup("/player/boy_right_2",pc.rectSize,pc.rectSize);
		
	}
	
	public void getViewAttacPlayer() {
		if(currentWeapon.type == type_sword) {
			attackUP1 = setup("/player/boy_attack_up_2",pc.rectSize,pc.rectSize*2);
			attackUP2 = setup("/player/boy_attack_up_2",pc.rectSize,pc.rectSize*2);
			attackDown1 = setup("/player/boy_attack_down_1",pc.rectSize,pc.rectSize*2);
			attackDown2 = setup("/player/boy_attack_down_2",pc.rectSize,pc.rectSize*2);
			attackLeft1 = setup("/player/boy_attack_left_1",pc.rectSize*2,pc.rectSize);
			attackLeft2= setup("/player/boy_attack_left_2",pc.rectSize*2,pc.rectSize);
			attackRight1 = setup("/player/boy_attack_right_1",pc.rectSize*2,pc.rectSize);
			attackRight2 = setup("/player/boy_attack_right_2",pc.rectSize*2,pc.rectSize);
		}
		if(currentWeapon.type == type_axe) {
			attackUP1 = setup("/player/boy_axe_up_2",pc.rectSize,pc.rectSize*2);
			attackUP2 = setup("/player/boy_axe_up_2",pc.rectSize,pc.rectSize*2);
			attackDown1 = setup("/player/boy_axe_down_2",pc.rectSize,pc.rectSize*2);
			attackDown2 = setup("/player/boy_axe_down_2",pc.rectSize,pc.rectSize*2);
			attackLeft1 = setup("/player/boy_axe_left_2",pc.rectSize*2,pc.rectSize);
			attackLeft2= setup("/player/boy_axe_left_2",pc.rectSize*2,pc.rectSize);
			attackRight1 = setup("/player/boy_axe_right_2",pc.rectSize*2,pc.rectSize);
			attackRight2 = setup("/player/boy_axe_right_2",pc.rectSize*2,pc.rectSize);
		}
	}

	public void update() {
		
		if(attacking == true) {
			attacking();
		}
		else if(keyI.upPress == true || keyI.downPress == true || keyI.leftPress == true || 
				keyI.rightPress == true || keyI.enterPress == true) {
			
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
			
			//check mon collision
			int monIndex = pc.collisionM.checkCharacters(this, pc.mon);
			contactMon(monIndex);
			
			//check iRect
			int iRectInd = pc.collisionM.checkCharacters(this, pc.iRect);
			
			//check events
			pc.events.checkEvents();
			
			
			
			if(collisionOn == false && keyI.enterPress == false) {
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
			
			if(keyI.enterPress == true && attackCancel == false) {
				pc.playSE(7);
				attacking = true;
				spriteCounter = 0;
			}
			
			attackCancel=false;
			pc.keyI.enterPress = false;
			
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
		
		if(pc.keyI.shotPress == true&& projectile.alive == false 
				&& shotAvail == 30 &&  projectile.haveResource(this)== true) {
			
			//set default coordinate , direction and user
			projectile.set(worldX,worldY,direction,true,this);
			
			//subtract mana
			projectile.subtractResource(this);
			
			for(int i=0; i < pc.projectile[1].length; i++) {
				if(pc.projectile[pc.currentMap][i] == null) {
					pc.projectile[pc.currentMap][i] = projectile;
					break;
				}
					
			}
			shotAvail = 0;
			pc.playSE(10);
			
			/*
			//projectile
			for(int i=0; i < pc.projectile.length; i++) {
				if(pc.projectile[i] == null) {
					pc.projectile[i]= projectile;
					break;
				}
			}
			*/
		}
		
		//invincible
		if(invincibl == true) {
			inviCount++;
			if(inviCount > 60) {
				invincibl = false;
				inviCount=0;
			}
		}
		
		if(shotAvail < 30) {
			shotAvail ++;	
		}
		
		if(life > maxLife) {
			life = maxLife;
		}
		if(mana > maxMana) {
			mana = maxMana;
		}
		if(life <= 0) {
			pc.stopMusic();
			pc.playSE(12);
			pc.gameState = pc.gameOverState;
		}
	}
	
	public boolean canObtainItem(Characters item) {
		boolean canObtain = false;
		
		//if it stackeble
		if(item.stackable == true) {
			int index = searchItemInInventory(item.name);
			
			if(index != 999) {
				inventory.get(index).amount++;
				canObtain=true;
			}else {//new item
				if(inventory.size() != inventorSize) {
					inventory.add(item);
					canObtain=true;
				}
				
			}
		}else { // not stackeble
			if(inventory.size() != inventorSize) {
				inventory.add(item);
				canObtain=true;
			}
		}
		return canObtain;
	}
	

	
	public int searchItemInInventory(String itemName) {
		
		int itemIndex = 999;
		for(int i=0; i < inventory.size(); i++) {
			if(inventory.get(i).name.equals(itemName)) {
				itemIndex =i;
				break;
			}
		}
		return itemIndex;
	}
	
	public void setDefaultPosition() {
		worldX=pc.rectSize * 10;
		worldY=pc.rectSize * 43;
		direction = "down";
	}
	
	public void restoreLifeAndMana() {
		life=maxLife;
		mana=maxMana;
		invincibl= false;
	}
	
	public void attacking(){
		spriteCounter++;
		if(spriteCounter <= 5) {
			spriteNum =1;
		}
		if(spriteCounter > 5 && spriteCounter <=25 ) {
			spriteNum=2;
			
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth= solidRect.width;
			int solidAreaHeight= solidRect.height;
			
			switch(direction) {
			case "up":	worldY -= attackArea.height;
				break;
			case "down":worldY += attackArea.height;
				break;
			case "left":	worldX -= attackArea.width;
				break;
			case "right":	worldX += attackArea.width;
				break;
			}
			
			solidRect.width = attackArea.width;
			solidRect.height = attackArea.height;
			
			int monsterIndex = pc.collisionM.checkCharacters(this, pc.mon);
			damageMonster(monsterIndex, attack, currentWeapon.knockBackPower);
			
			
			int iRectIndex = pc.collisionM.checkCharacters(this, pc.iRect);
			damageInteractRect(iRectIndex);
			
			
			int projectileIndex = pc.collisionM.checkCharacters(this, pc.projectile);
			damageProjectile(projectileIndex);
			
			worldX= currentWorldX;
			worldY = currentWorldY;
			
			solidRect.width = solidAreaWidth;
			solidRect.height= solidAreaHeight;
		}
		if(spriteCounter > 25 ) {
			spriteNum=1;
			spriteCounter=0;
			attacking=false;
		}
	}
	
	public void damageProjectile(int i) {
		if(i != 999) {
			Characters projectile = pc.projectile[pc.currentMap][i];
			projectile.alive = false;
		}
	}
	
	
	public void pickUpObj(int i) {
		if(i != 999) {
			
			if(pc.obj[pc.currentMap][i].type == type_pickupONLY) {
				pc.obj[pc.currentMap][i].use(this);
				pc.obj[pc.currentMap][i] = null;;
			}
			else if(pc.obj[pc.currentMap][i].type == type_obstacle) {
				pc.player.collision = true;
				if(keyI.enterPress == true) {
					attackCancel = true;
					pc.obj[pc.currentMap][i].interact();
					
				}
			}
			else {
				String text;
				if(inventory.size() != inventorSize) {
					inventory.add(pc.obj[pc.currentMap][i]);
					pc.playSE(1);
					text = "Zobral si + "+pc.obj[pc.currentMap][i].name + "!";
				}else {
					text = "Nemôžeš zobrať item ,\nmáš plný inventár!";
				}
				pc.ui.addMess(text);
				pc.obj[pc.currentMap][i]=null;
			}
		}
	}
	
	public void damageMonster(int i, int attack, int knockbackPower) {
		if(i != 999) {
			if(pc.mon[pc.currentMap][i].invincibl == false) {
				pc.playSE(5);
				
				if(knockbackPower > 0) {

					knockBack(pc.mon[pc.currentMap][i], knockbackPower);
				}
				
				
				int damage = attack - pc.mon[pc.currentMap][i].defense;
				if(damage < 1) {
					damage = 0;
				}
				
				pc.mon[pc.currentMap][i].life -= damage;
				pc.ui.addMess(damage + " poškodenie!");
				pc.mon[pc.currentMap][i].invincibl = true;
				pc.mon[pc.currentMap][i].damageReact();
				
				if(pc.mon[pc.currentMap][i].life <= 0) {
					pc.mon[pc.currentMap][i].dying = true;
					pc.ui.addMess("Zabil si " + pc.mon[pc.currentMap][i].name + "!");
					pc.ui.addMess("Exp + " + pc.mon[pc.currentMap][i].exp + "!");
					exp += pc.mon[pc.currentMap][i].exp;
					checkLevel();
				}
			}
		}
	}
	
	public void checkLevel() {
		if(exp >= nextLevelExp) {
			level++;
			nextLevelExp = nextLevelExp * 2;
			
			//maxLife += 1;
			life = maxLife;
			mana=maxMana;
			
			
			
			strength ++;
			dexterity ++;
			attack = getAttack();
			defense = getDefense();
			pc.playSE(8);
			pc.gameState = pc.dialogState;
			pc.ui.currentDialogue = "Dostal si nový level, \nneposer sa z toho!";
		}
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		int tempScreX = screenX;
		int tempScreY = screenY;
		
		switch(direction) {
		case "up":
			if(attacking == false) {
				if(spriteNum == 1) {
					image= up1;
				}
				if(spriteNum == 2) {
					image= up2;
				}
			}
			if(attacking == true) {
				tempScreY = screenY - pc.rectSize;
				if(spriteNum == 1) {
					image= attackUP1;
				}
				if(spriteNum == 2) {
					image= attackUP2;
				}
			}
			
			break;
		case "down":
			if(attacking == false) {
				if(spriteNum == 1) {
					image= down1;
				}
				if(spriteNum == 2) {
					image= down2;
				}
			}
			if(attacking == true) {
				if(spriteNum == 1) {
					image= attackDown1;
				}
				if(spriteNum == 2) {
					image= attackDown2;
				}
			}
			break;
		case "left":
			if(attacking == false) {
				if(spriteNum == 1) {
					image= left1;
				}
				if(spriteNum == 2) {
					image= left2;
				}
			}
			if(attacking == true) {
				tempScreX = screenX - pc.rectSize;
				if(spriteNum == 1) {
					image= attackLeft1;
				}
				if(spriteNum == 2) {
					image= attackLeft2;
				}
			}
			break;
		case "right":
			if(attacking == false) {
				if(spriteNum == 1) {
					image= right1;
				}
				if(spriteNum == 2) {
					image= right2;
				}
			}
			if(attacking == true) {
				if(spriteNum == 1) {
					image= attackRight1;
				}
				if(spriteNum == 2) {
					image= attackRight2;
				}
			}
			
			break;
			
		}
		
		if(invincibl == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		g2.drawImage(image, tempScreX, tempScreY, null);
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	
	public void interactNpc(int i) {
		
		if(pc.keyI.enterPress == true) {
			if(i != 999) {
				attackCancel =true;
				pc.gameState = pc.dialogState;
				pc.npc[pc.currentMap][i].speak();
			}
		}
	}
	
	public void selectItem() {
		int itemIndex = pc.ui.getItemIndexOnSlot(pc.ui.playerSlotCol,pc.ui.playerSlotRow);
		if(itemIndex < inventory.size()) {
			Characters selectedItem = inventory.get(itemIndex);
			if(selectedItem.type == type_sword || selectedItem.type == type_axe) {
				currentWeapon = selectedItem;
				attack=getAttack();
				getViewAttacPlayer();
			}
			if(selectedItem.type == type_shield) {
				currentShield = selectedItem;
				defense=getDefense();
			}
			if(selectedItem.type == type_consumable) {
				selectedItem.use(this);
				inventory.remove(itemIndex);
			}
		}
	}
	
	public void damageInteractRect(int i) {
		if(i != 999 && pc.iRect[pc.currentMap][i].destruct == true && pc.iRect[pc.currentMap][i].isAxe(this) == true 
				&& pc.iRect[pc.currentMap][i].invincibl == false) {
			
			pc.iRect[pc.currentMap][i].playSE();
			pc.iRect[pc.currentMap][i].life--;
			pc.iRect[pc.currentMap][i].invincibl=true;
			
			if(pc.iRect[pc.currentMap][i].life == 0) {
				pc.iRect[pc.currentMap][i] = pc.iRect[pc.currentMap][i].getDownTree();
				pc.ui.addMess("Zničil si strom , dobrej si!");
				//gs.ui.addMessage("Exp + " + 1);
				coin += 2;
				pc.playSE(3);
			}
		}
	}
	
	public void contactMon(int i) {
		if(i != 999) {
			if(invincibl == false && pc.mon[pc.currentMap][i].dying == false) {
				pc.playSE(6);
				int damage = pc.mon[pc.currentMap][i].attack - defense;
				if(damage < 0) {
					damage = 0;
				}
				life -= damage;
				invincibl = true;
			}
		}
	}
	
}
