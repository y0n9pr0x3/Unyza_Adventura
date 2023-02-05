package rects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.PlayingCanvas;

public class RectManager {
	PlayingCanvas pc;
	public Rect[] rect;
	public int mapRectNum[][];
	
	public RectManager(PlayingCanvas pc) {
		this.pc = pc;
		
		rect = new Rect[10];
		mapRectNum = new int[pc.maxWorldCol][pc.maxWorldCol];
		getViewRect();
		loadMap("/map/world01.txt");
	}
	
	public void getViewRect() {
		
		try {
			rect[0] = new Rect();
			rect[0].image = ImageIO.read(getClass().getResourceAsStream("/rects/002.png"));
			
			rect[1] = new Rect();
			rect[1].image = ImageIO.read(getClass().getResourceAsStream("/rects/032.png"));
			rect[1].collision = true;
			
			rect[2] = new Rect();
			rect[2].image = ImageIO.read(getClass().getResourceAsStream("/rects/019.png"));
			rect[2].collision = true;
			
			rect[3] = new Rect();
			rect[3].image = ImageIO.read(getClass().getResourceAsStream("/rects/017.png"));
			
			rect[4] = new Rect();
			rect[4].image = ImageIO.read(getClass().getResourceAsStream("/rects/016.png"));
			rect[4].collision = true;
			
			rect[5] = new Rect();
			rect[5].image = ImageIO.read(getClass().getResourceAsStream("/rects/sand.png"));
			
		}catch(IOException e) {
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
				
				g2.drawImage(rect[rectNum].image, screenX, screenY ,pc.rectSize, pc.rectSize, null);
			}
			

			worldCol++;
			
			if(worldCol == pc.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}