package object;

import javax.imageio.ImageIO;

import main.PlayingCanvas;

public class Obj_Key extends SuperObject{
	PlayingCanvas pc;
	
	public Obj_Key(PlayingCanvas pc){
		this.pc = pc;
		name = "Kľúčik";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			utility.scaleImage(image, pc.rectSize, pc.rectSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}