package object;

import javax.imageio.ImageIO;

public class Obj_Boots extends SuperObject{
	
	public Obj_Boots(){
		name = "Jordany";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boot.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
