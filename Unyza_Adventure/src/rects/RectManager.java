package rects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.PlayingCanvas;
import main.Utility;

public class RectManager {
	PlayingCanvas pc;
	public Rect[] rect;
	public int mapRectNum[][];
	
	public RectManager(PlayingCanvas pc) {
		this.pc = pc;
		
		rect = new Rect[100];
		mapRectNum = new int[pc.maxWorldCol][pc.maxWorldCol];
		getViewRect();
		loadMap("/map/world2.txt");
	}
	
	public void getViewRect() {
		setup(0, "000", false);
		setup(1, "001", false);
		setup(2, "002", false);
		setup(18, "018", true);
		setup(19, "019", true);
		setup(20, "020", true);
		setup(21, "021", true);
		setup(22, "022", true);
		setup(23, "023", true);
		setup(24, "024", true);
		setup(25, "025", true);
		setup(26, "026", true);
		setup(27, "027", true);
		setup(28, "028", true);
		setup(29, "029", true);
		setup(30, "030", true);
		setup(31, "031", true);
		setup(3, "003", false);
		setup(4, "004", false);
		setup(5, "005", false);
		setup(6, "006", false);
		setup(7, "007", false);
		setup(8, "008", false);
		setup(9, "009", false);
		setup(10, "010", false);
		setup(11, "011", false);
		setup(12, "012", false);
		setup(13, "013", false);
		setup(14, "014", false);
		setup(15, "015", false);
		setup(17, "017", false);
		setup(32, "032", true);
		setup(16, "016", true);
		setup(33, "033", false);
		setup(34, "034", false);
		setup(35, "035", true);
		setup(98, "098", true);
		setup(99, "099", false);
		
		setup(36, "036", false);
		setup(37, "037", false);
		setup(47, "tabula", false);
	}
	
	public void setup(int i, String imagePath, boolean collision) {
		Utility utility = new Utility();
		
		try {
			rect[i] = new Rect();
			rect[i].image = ImageIO.read(getClass().getResourceAsStream("/rects/" + imagePath +".png"));
			rect[i].image = utility.scaleImage(rect[i].image, pc.rectSize, pc.rectSize);
			rect[i].collision = collision;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String path) {
		
		try {
			InputStream is = getClass().getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col=0;
			int row=0;
			
			while(col < pc.maxWorldCol && row < pc.maxWorldRow) {
				String line = br.readLine();
				
				while(col < pc.maxWorldCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapRectNum[col][row]=num;
					col++;
				}
				if(col == pc.maxWorldCol) {
					col=0;
					row++;
				}
			}
			br.close();
		}catch(Exception e) {
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < pc.maxWorldCol && worldRow < pc.maxWorldRow) {
			
			int rectNum = mapRectNum[worldCol][worldRow];
			
			int worldX = worldCol * pc.rectSize;
			int worldY = worldRow * pc.rectSize;
			int screenX = worldX - pc.player.worldX + pc.player.screenX;
			int screenY = worldY - pc.player.worldY + pc.player.screenY;
			
			if(worldX + pc.rectSize > pc.player.worldX - pc.player.screenX && 
			   worldX - pc.rectSize < pc.player.worldX + pc.player.screenX &&
			   worldY + pc.rectSize > pc.player.worldY - pc.player.screenY && 
			   worldY - pc.rectSize < pc.player.worldY + pc.player.screenY) {
				
				g2.drawImage(rect[rectNum].image, screenX, screenY , null);
			}
			

			worldCol++;
			
			if(worldCol == pc.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
