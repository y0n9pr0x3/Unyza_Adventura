package object;

import character.Characters;
import character.Projectile;
import main.PlayingCanvas;

public class Obj_Fireball extends Projectile{
	PlayingCanvas pc;
	public Obj_Fireball(PlayingCanvas pc) {
		super(pc);
		
		this.pc=pc;
		
		name= "Ohnivo guľo!";
		speed=8;
		maxLife=80;
		life=maxLife;
		attack=2;
		knockBackPower = 0;
		useCost = 1;
		alive=false;
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/projectile/fireball_up_1",pc.rectSize,pc.rectSize);
		up2 = setup("/projectile/fireball_up_2",pc.rectSize,pc.rectSize);
		down1 = setup("/projectile/fireball_down_1",pc.rectSize,pc.rectSize);
		down2 = setup("/projectile/fireball_down_2",pc.rectSize,pc.rectSize);
		left1 = setup("/projectile/fireball_left_1",pc.rectSize,pc.rectSize);
		left2 =  setup("/projectile/fireball_left_2",pc.rectSize,pc.rectSize);
		right1 = setup("/projectile/fireball_right_1",pc.rectSize,pc.rectSize);
		right2 = setup("/projectile/fireball_right_2",pc.rectSize,pc.rectSize);
	}
	
	public boolean haveResource(Characters user) {
		boolean haveResource = false;
		if(user.mana >= useCost) {
			haveResource = true;
		}
		return haveResource;
	}
	
	public void subtractResource(Characters user) {
		user.mana -= useCost;
	}

}
