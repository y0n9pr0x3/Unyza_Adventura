package object;

import character.Characters;
import character.Projectile;
import main.PlayingCanvas;

public class Obj_Rock extends Projectile{

	PlayingCanvas pc;
	public Obj_Rock(PlayingCanvas pc) {
		super(pc);
		
		this.pc=pc;
		
		name= "Kameň!";
		speed=5;
		maxLife=80;
		life=maxLife;
		attack=2;
		
		useCost = 1;
		alive=false;
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/projectile/rock_down_1",pc.rectSize,pc.rectSize);
		up2 = setup("/projectile/rock_down_1",pc.rectSize,pc.rectSize);
		down1 = setup("/projectile/rock_down_1",pc.rectSize,pc.rectSize);
		down2 = setup("/projectile/rock_down_1",pc.rectSize,pc.rectSize);
		left1 = setup("/projectile/rock_down_1",pc.rectSize,pc.rectSize);
		left2 =  setup("/projectile/rock_down_1",pc.rectSize,pc.rectSize);
		right1 = setup("/projectile/rock_down_1",pc.rectSize,pc.rectSize);
		right2 = setup("/projectile/rock_down_1",pc.rectSize,pc.rectSize);
	}
	
	public boolean haveResource(Characters user) {
		boolean haveResource = false;
		if(user.ammo >= useCost) {
			haveResource = true;
		}
		return haveResource;
	}
	
	public void subtractResource(Characters user) {
		user.ammo -= useCost;
	}
}
