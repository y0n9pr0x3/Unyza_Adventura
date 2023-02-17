package object;

import javax.imageio.ImageIO;

public class Obj_Coin extends SuperObject{
	
	public Obj_Coin(){
		name = "Unyza Coin";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/coin_Uniza.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
