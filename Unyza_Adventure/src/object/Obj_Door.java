package object;

import javax.imageio.ImageIO;

public class Obj_Door extends SuperObject{
	
	public Obj_Door(){
		name = "Dvere";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		collision = true;
		
	}

}
