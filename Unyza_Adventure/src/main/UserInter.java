package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

import javax.imageio.ImageIO;

import object.Obj_Key;

public class UserInter {
	
	PlayingCanvas pc;
	public Font purisaB;
	//BufferedImage keyI;
	public boolean messOn = false;
	public boolean gameFinish = false;
	public String currentDialogue = "";
	public String mess = "";
	public int messCount = 0;
	BufferedImage ovca, znakU, monster, unyza, stuff11, stuff22;
	Graphics2D g2;
	Utility utility = new Utility();
	public int selectedNum = 0;
	public int tittlescreenState = 0;
	public int subState =0;
	
	
	public UserInter(PlayingCanvas pc) {
		this.pc= pc;
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/purisaB.ttf");
			purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void showMess(String text) {
		mess = text;
		messOn = true;
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(purisaB);
		g2.setFont(g2.getFont().deriveFont(50f));
		g2.setColor(Color.decode("#FF742c"));
		
		//tittle state
		if(pc.gameState == pc.tittleState) {
			drawTittle();
		}
		
		//playing mode
		if(pc.gameState == pc.huntState) {
			
		}
		
		//pause mode
		if(pc.gameState == pc.pauseState) {
			drawPause();
		}
		
		//dialogue mode
		if(pc.gameState == pc.dialogState) {
			drawDialogue();
		}
		
	}
	
	public void drawPause() {
		String text = "PAUSE";
		int x,y;
		
		x = getXforCenteredText(text);
		y = pc.screenHeight/2;
		
		g2.setColor(Color.BLACK);
		g2.drawString(text, x, y);
		
		
		g2.setColor(Color.decode("#FF742c"));
		g2.drawString(text, x +3, y +6);
		
		
		//ovca
		try {
			BufferedImage uniza = ImageIO.read(getClass().getResourceAsStream("/img/ovca.png"));
			ovca = utility.scaleImage(uniza, pc.screenWidth-30, pc.screenHeight-30);
			
			BufferedImage znak = ImageIO.read(getClass().getResourceAsStream("/img/uniza_znak.png"));
			znakU = utility.scaleImage(znak, pc.screenWidth-30, pc.screenHeight-30);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		y -= 100;
		
		g2.drawImage(ovca, x +10, y , pc.rectSize,  pc.rectSize , null );
		g2.drawImage(ovca, x +70, y , pc.rectSize,  pc.rectSize , null );
		g2.drawImage(ovca, x +130, y , pc.rectSize,  pc.rectSize , null );
		
		y += 115;
		
		g2.drawImage(ovca, x +10, y , pc.rectSize,  pc.rectSize , null );
		g2.drawImage(ovca, x +70, y , pc.rectSize,  pc.rectSize , null );
		g2.drawImage(ovca, x +130, y , pc.rectSize,  pc.rectSize , null );
		
		text = "SPONZORING FEYT UNYZA!";
		x = pc.rectSize * 3;
		y = pc.screenHeight / 5;
		g2.setFont(g2.getFont().deriveFont(30f));
		
		g2.setColor(Color.BLACK);
		g2.drawString(text, x, y);
		
		
		g2.setColor(Color.decode("#FF742c"));
		g2.drawString(text, x +2, y +5);
		
		x = pc.rectSize * 6 + (pc.rectSize /2);
		y = pc.rectSize *8;
		
		g2.drawImage(znakU, x , y , (3 * pc.rectSize), (3 * pc.rectSize) , null );
	}
	
	public void drawDialogue() {
		
		//dialogue window
		g2.setFont(purisaB);
		int x=pc.rectSize*3;
		int y=pc.rectSize*7;
		int width=pc.screenWidth - (pc.rectSize*6);
		int height= pc.rectSize*4;
		
		drawSubWind(x, y, width, height);
		
		x += pc.rectSize;
		y += pc.rectSize+5;
		
		
		for(String line : currentDialogue.split("\n")) {
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,28));
			  g2.drawString(line, x, y); y +=52; 
			  }
	}
	
	public void drawTittle() {
		
		if(tittlescreenState == 0) {
			//background
			
			try {
				BufferedImage uniza = ImageIO.read(getClass().getResourceAsStream("/img/uniza1111.jpg"));
				unyza= utility.scaleImage(uniza, pc.screenWidth, pc.screenHeight);
				g2.drawImage(uniza, 0, 0, null);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			//tittle name
			g2.setFont(purisaB);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,60));
			String text = "UNYZA ADVENTÜRE";
			int x = getXforCenteredText(text);
			int y = pc.rectSize*3;
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			g2.setColor(Color.decode("#FF742c"));
			g2.drawString(text, x+5, y+5);
			
			//ovca
			try {
				BufferedImage uniza = ImageIO.read(getClass().getResourceAsStream("/img/ovca.png"));
				unyza= utility.scaleImage(uniza, pc.screenWidth-30, pc.screenHeight-30);
				//g2.drawImage(uniza, 0, 0, null);
			} catch (Exception e) {
				// TODO: handle exception
			}
			x = (pc.rectSize*8)- (2* pc.rectSize) ;
			y = pc.rectSize*2;
			g2.drawImage(unyza, x, y,pc.rectSize-10, pc.rectSize-10,null);
			
			
			
			//text gandalf
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,30));
			x=pc.screenWidth/2 + (pc.rectSize*2);
			y += pc.rectSize*4;
			g2.setColor(Color.black);
			g2.drawString("Skúl Gandalf", x, y);
			
			g2.setColor(Color.decode("#FF742c"));
			
			g2.drawString("Skúl Gandalf", x+4, y+4);
			
			
			
			//gandalf
			x=pc.screenWidth/2 + (pc.rectSize*3);
			y += pc.rectSize;
			
			try {
				BufferedImage monster2 = ImageIO.read(getClass().getResourceAsStream("/img/oldman_down_1.png"));
				monster= utility.scaleImage(monster2, pc.screenWidth, pc.screenHeight);
				g2.drawImage(monster, x, y,pc.rectSize*3, pc.rectSize*3,null);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			//monster
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,42));
			
			
			
			text = "Nová hra";
			x = pc.rectSize*2;
			y = pc.rectSize *7+10;
			g2.setColor(Color.black);
			g2.drawString(text, x, y);
			g2.setColor(Color.decode("#FF742c"));
			g2.drawString(text, x+4, y+4);
			if(selectedNum == 0) {
				g2.setColor(Color.black);
				g2.drawString(">", x-pc.rectSize, y);
				g2.setColor(Color.decode("#FF742c"));
				g2.drawString(">", x-pc.rectSize+4, y+4);
			}
			
			
			text = "Načítaj";
			x = pc.rectSize*2;
			y += pc.rectSize+5;
			g2.setColor(Color.black);
			g2.drawString(text, x, y);
			g2.setColor(Color.decode("#FF742c"));
			g2.drawString(text, x+4, y+4);
			if(selectedNum == 1) {
				g2.setColor(Color.black);
				g2.drawString(">", x-pc.rectSize, y);
				g2.setColor(Color.decode("#FF742c"));
				g2.drawString(">", x-pc.rectSize+4, y+4);
			}
			
			
			text = "Možnosti";
			x = pc.rectSize*2;
			y += pc.rectSize;
			g2.setColor(Color.black);
			g2.drawString(text, x, y);
			g2.setColor(Color.decode("#FF742c"));
			g2.drawString(text, x+4, y+4);
			if(selectedNum == 2) {
				g2.setColor(Color.black);
				g2.drawString(">", x-pc.rectSize, y);
				g2.setColor(Color.decode("#FF742c"));
				g2.drawString(">", x-pc.rectSize+4, y+4);
			}
			
			
			text = "Ukončiť hru";
			x = pc.rectSize*2;
			y += pc.rectSize+5;
			g2.setColor(Color.black);
			g2.drawString(text, x, y);
			g2.setColor(Color.decode("#FF742c"));
			g2.drawString(text, x+4, y+4);
			if(selectedNum == 3) {
				g2.setColor(Color.black);
				g2.drawString(">", x-pc.rectSize, y);
				g2.setColor(Color.decode("#FF742c"));
				g2.drawString(">", x-pc.rectSize+4, y+4);
			}
		}else if(tittlescreenState == 1) {
			g2.setFont(purisaB);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,40));
			
			try {
				BufferedImage uniza = ImageIO.read(getClass().getResourceAsStream("/img/uniza1111.jpg"));
				unyza= utility.scaleImage(uniza, pc.screenWidth, pc.screenHeight);
				g2.drawImage(uniza, 0, 0, null);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			String text = "";
			int x;
			int y=0;
			
			text="Hrať";
			x = pc.rectSize;
			y += pc.rectSize*10;
			g2.setColor(Color.black);
			g2.drawString(text, x, y);
			
			g2.setColor(Color.decode("#FF742c"));
			g2.drawString(text, x+3, y+3);
			
			if(selectedNum == 0) {
				g2.setColor(Color.black);
				g2.drawString(">", x-pc.rectSize, y);
				g2.setColor(Color.decode("#FF742c"));
				g2.drawString(">", x-pc.rectSize+3, y+3);
			}
			text="Späť";
			x = pc.rectSize;
			y += pc.rectSize;
			g2.setColor(Color.black);
			g2.drawString(text, x, y);
			
			g2.setColor(Color.decode("#FF742c"));
			g2.drawString(text, x+3, y+3);
			if(selectedNum == 1) {
				g2.setColor(Color.black);
				g2.drawString(">", x-pc.rectSize, y);
				g2.setColor(Color.decode("#FF742c"));
				g2.drawString(">", x-pc.rectSize+3, y+3);
				
			}
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,40));
			text = "Krycia prezývka : " + pc.id;
			x = pc.rectSize- (pc.rectSize/2);
			y = pc.rectSize*2;
			g2.setColor(Color.black);
			g2.drawString(text, x, y);
			g2.setColor(Color.decode("#FF742c"));
			g2.drawString(text, x+3, y+3);
			
			
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,30));
			text = "Tvoja výbava, vagoš: ";
			x = pc.rectSize - (pc.rectSize/2);
			y = pc.rectSize*4;
			g2.setColor(Color.black);
			g2.drawString(text, x, y);
			g2.setColor(Color.decode("#FF742c"));
			g2.drawString(text, x+3, y+3);
			
			x=pc.screenWidth - (pc.rectSize*6);
			y=(2 * pc.rectSize) + (pc.rectSize/3);
			
			try {
				BufferedImage stuff1 = ImageIO.read(getClass().getResourceAsStream("/img/deafaultStuff.png"));
				stuff11= utility.scaleImage(stuff1, pc.screenWidth, pc.screenHeight);
				g2.drawImage(stuff11, x, y,pc.rectSize*5, pc.rectSize*9,null);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			x=pc.screenWidth - (pc.rectSize*12);
			y=pc.rectSize*5;
			
			try {
				BufferedImage stuff2 = ImageIO.read(getClass().getResourceAsStream("/img/deafaultStuff2.png"));
				stuff22= utility.scaleImage(stuff2, pc.screenWidth, pc.screenHeight);
				g2.drawImage(stuff22, x, y,pc.rectSize*5, pc.rectSize*6 + (pc.rectSize / 3),null);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
	public void drawSubWind(int x, int y , int width, int height) {
		Color c= new Color(252, 127, 3,180);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c= new Color(0,0,0);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
		
	}
	
	public int getXforCenteredText(String text) {
		int length =(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = pc.screenWidth/2 - length/2;
		return x;
	}
	
	public int getXforAlignToRightText(String text,int tailX) {
		int length =(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x=tailX - length;
		return x;
	}

}
