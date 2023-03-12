package character;

import main.PlayingCanvas;

public class Projectile extends Characters{
	
	Characters user;

	public Projectile(PlayingCanvas pc) {
		super(pc);
		//this.pc=pc;
	}
	
	public void set(int worldX , int worldY, String direction, boolean alive, Characters user) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction=direction;
		this.alive=alive;
		this.user=user;
		this.life = this.maxLife;	//reset life projectile
		
	}
	
	public void update() {
		
		
		if(user == pc.player ) {
			int monsterIndex = pc.collisionM.checkCharacters(this, pc.mon);
			if(monsterIndex != 999) {
				pc.player.damageMonster(monsterIndex, attack);
				alive=false;
			}
		}
		if(user != pc.player) {
			boolean contactPlayer = pc.collisionM.checkPlayer(this);
			if(pc.player.invincibl == false && contactPlayer == true) {
				damagePlayer(attack);
				alive=false;
			}
		}
		
		switch(direction) {
		case "up":		worldY -= speed; break;
		case "down":	worldY += speed; break;
		case "left":	worldX -= speed; break;
		case "right":	worldX += speed; break;
		}
		
		life--;
		if(life <= 0) {
			alive=false;
		}
		
		spriteCounter++;
		if(spriteCounter > 12) {
			if(spriteNum == 1) {
				spriteNum =2;
			}else if(spriteNum == 2) {
				spriteNum =1;
			}
			spriteCounter=0;
		}
	}
	
	
	public boolean haveResource(Characters user) {
		boolean haveResource = false;
		return haveResource;
	}
	
	public void subtractResource(Characters user) {}
}

