package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.PlayingCanvas;
import main.Utility;

public class SuperObject {
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX , worldY;
	public Rectangle solidRect = new Rectangle(0,0,48,48);
	public int solidRectDefaultX = 0;
	public int solidRectDefaultY = 0;
	public Utility utility = new Utility();
	
	
	public void draw(Graphics2D g2, PlayingCanvas pc) {
		
		int screenX = worldX - pc.player.worldX + pc.player.screenX;
		int screenY = worldY - pc.player.worldY + pc.player.screenY;
		
		if(worldX + pc.rectSize > pc.player.worldX - pc.player.screenX && 
		   worldX - pc.rectSize < pc.player.worldX + pc.player.screenX &&
		   worldY + pc.rectSize > pc.player.worldY - pc.player.screenY && 
		   worldY - pc.rectSize < pc.player.worldY + pc.player.screenY) {
			
			g2.drawImage(image, screenX, screenY ,pc.rectSize, pc.rectSize, null);
		}
	}
}
