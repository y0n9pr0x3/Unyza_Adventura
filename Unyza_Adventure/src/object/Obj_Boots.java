package object;

import javax.imageio.ImageIO;

import main.PlayingCanvas;

public class Obj_Boots extends SuperObject{
	PlayingCanvas pc;
	public Obj_Boots(PlayingCanvas pc){
		this.pc = pc;
		name = "Jordany";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boot.png"));
			utility.scaleImage(image, pc.rectSize, pc.rectSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
