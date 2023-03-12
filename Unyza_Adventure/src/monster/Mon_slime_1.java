package monster;

import java.util.Random;

import character.Characters;
import main.PlayingCanvas;
import object.Obj_BeerAmmo;
import object.Obj_Coin;
import object.Obj_Heart;
import object.Obj_Rock;

public class Mon_slime_1 extends Characters{
	PlayingCanvas pc;
	public Mon_slime_1(PlayingCanvas pc) {
		super(pc);
		this.pc = pc;
		
		type = type_monster;
		name = "Modrého slima";
		speed = 1;
		maxLife = 4;
		life = maxLife;
		attack = 5;
		defense = 0;
		exp = 2;
		projectile = new Obj_Rock(pc);
		
		solidRect.x = 3;
		solidRect.y = 18;
		solidRect.width = 42;
		solidRect.height = 30;
		solidRectDefaultX = solidRect.x;
		solidRectDefaultY = solidRect.y;
		getImg();
	}
	
	public void getImg() {
		up1 = setupMon("/monster/1",58,58);
		up2 = setupMon("/monster/1",58,58);
		down1 = setupMon("/monster/1",58,58);
		down2 = setupMon("/monster/1",58,58);
		left1 = setupMon("/monster/1",58,58);
		left2 = setupMon("/monster/1",58,58);
		right1 = setupMon("/monster/1",58,58);
		right2 = setupMon("/monster/1",58,58);
	}
	
	public void setAction() {
		actionCount++;
		
		if(actionCount > 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1;
			if(i<=25) {
				direction="up";
			}
			if(i>25 && i <= 50) {
				direction="down";
			}
			if(i>50 && i <= 75) {
				direction="left";
			}
			if(i>75 && i <= 100) {
				direction="right";
			}
			actionCount =0;
			
		}
		int i = new Random().nextInt(100)+1;
		if(i > 99 && projectile.alive == false && shotAvail == 30) {
			projectile.set(worldX, worldY, direction, true, this);
			pc.projectileList.add(projectile);
			shotAvail=0;
		}
	}
	
	public void damageReact() {
		actionCount = 0;
		direction = pc.player.direction;
	}
	
	public void checkDrop() {
		int i = new Random().nextInt(100)+1;
		
		//set monster drop
		
		if(i < 50) {
			dropItem(new Obj_Coin(pc));
			
		}
		if(i >= 50 && i < 70) {
			dropItem(new Obj_Heart(pc));
		
		}
		if(i >= 70 && i < 100) {
			dropItem(new Obj_BeerAmmo(pc));
			
		}
	}
	
}
