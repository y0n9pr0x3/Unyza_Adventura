package object;

import javax.imageio.ImageIO;

import main.PlayingCanvas;

public class Obj_Coin extends SuperObject{
	PlayingCanvas pc;
	public Obj_Coin(PlayingCanvas pc){
		this.pc = pc;
		name = "Unyza Coin";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/coin_Uniza.png"));
			utility.scaleImage(image, pc.rectSize, pc.rectSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
