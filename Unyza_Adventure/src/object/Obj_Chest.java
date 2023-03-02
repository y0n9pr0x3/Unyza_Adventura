package object;

import javax.imageio.ImageIO;

import main.PlayingCanvas;

public class Obj_Chest extends SuperObject{
	PlayingCanvas pc;
	public Obj_Chest(PlayingCanvas pc){
		this.pc = pc;
		name = "Truhlos";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
			utility.scaleImage(image, pc.rectSize, pc.rectSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
