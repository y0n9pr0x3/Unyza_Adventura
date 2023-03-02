package object;

import javax.imageio.ImageIO;

import main.PlayingCanvas;

public class Obj_Door extends SuperObject{
	PlayingCanvas pc;
	public Obj_Door(PlayingCanvas pc){
		this.pc = pc;
		name = "Dvere";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
			utility.scaleImage(image, pc.rectSize, pc.rectSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		collision = true;
		
	}

}
