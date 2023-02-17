package object;

import javax.imageio.ImageIO;

public class Obj_Key extends SuperObject{
	
	public Obj_Key(){
		name = "Kľúčik";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}