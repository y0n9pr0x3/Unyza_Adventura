package object;

import javax.imageio.ImageIO;

public class Obj_Chest extends SuperObject{
	
	public Obj_Chest(){
		name = "Truhlos";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
