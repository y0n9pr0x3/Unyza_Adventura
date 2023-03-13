package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import character.Characters;
import object.Obj_BeerAmmo;
import object.Obj_Heart;

public class UserInter {
	
	PlayingCanvas pc;
	public Font purisaB;
	//BufferedImage keyI;
	public boolean messOn = false;
	public boolean gameFinish = false;
	public String currentDialogue = "";
	ArrayList<String> mess = new ArrayList<>();
	ArrayList<Integer> messCount = new ArrayList<>();
	//public String mess = "";
	//public int messCount = 0;
	BufferedImage ovca, znakU, monster, unyza, stuff11, stuff22, heart_f, heart_h, heart_b, beer_full, beer_blank;
	Graphics2D g2;
	Utility utility = new Utility();
	public int selectedNum = 0;
	public int tittlescreenState = 0;
	public int subState =0;
	public int slotCol =0;
	public int slotRow =0;
	
	
	public UserInter(PlayingCanvas pc) {
		this.pc= pc;
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/purisaB.ttf");
			purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		//ovca
		try {
			BufferedImage uniza = ImageIO.read(getClass().getResourceAsStream("/img/ovca.png"));
			ovca = utility.scaleImage(uniza, pc.screenWidth-30, pc.screenHeight-30);
			
			BufferedImage znak = ImageIO.read(getClass().getResourceAsStream("/img/uniza_znak.png"));
			znakU = utility.scaleImage(znak, pc.screenWidth-30, pc.screenHeight-30);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//hearts
		Characters heart = new Obj_Heart(pc);
		heart_f = heart.image;
		heart_h = heart.image2;
		heart_b = heart.image3;
		//ammo
		Characters beer = new Obj_BeerAmmo(pc);
		beer_full = beer.image;
		beer_blank = beer.image2;
	}
	
	public void addMess(String text) {

		mess.add(text);
		messCount.add(0);
		
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
			drawLife();
			drawMess();
		}
		
		//pause mode
		if(pc.gameState == pc.pauseState) {
			drawLife();
			drawPause();
		}
		
		//dialogue mode
		if(pc.gameState == pc.dialogState) {
			drawLife();
			drawDialogue();
		}
		
		//character mode
		if(pc.gameState == pc.characterState) {
			drawCharacter();
			drawInventory();
		}
		
		//option mode
		if(pc.gameState == pc.optionState) {
			drawOptionsScreen();
		}
		
	}
	
	public void drawLife() {
		
		int x = pc.rectSize;
		int y = pc.rectSize - (pc.rectSize/2);
		
		int i=0;
		
		//draw max life
		while(i <pc.player.maxLife/2) {
			g2.drawImage(heart_b, x, y, null);
			i++;
			x+= pc.rectSize;
		}
		
		//reset
		x = pc.rectSize;
		y = pc.rectSize - (pc.rectSize/2);
		
		i=0;
		
		//current life
		while(i<pc.player.life) {
			g2.drawImage(heart_h, x, y, null);
			i++;
			if(i <pc.player.life) {
				g2.drawImage(heart_f, x, y, null);
			}
			i++;
			x += pc.rectSize;
		}
		
		
		//draw mana
		x = pc.rectSize;
		y = pc.rectSize +20;
		i=0;
		
		while(i < pc.player.maxMana) {
			g2.drawImage(beer_blank, x, y, null);
			i++;
			x+=35;
		}
		
		//beer
		x = pc.rectSize;
		y = pc.rectSize +20;
		i=0;
		
		while(i < pc.player.mana) {
			g2.drawImage(beer_full, x, y, null);
			i++;
			x+=35;
		}
	}
	
	public void drawOptionsScreen() {
		g2.setColor(Color.white);
		g2.setFont(purisaB);
		g2.setFont(g2.getFont().deriveFont(24F));
		
		//sub window
		
		int frameX = pc.rectSize *6;
		int frameY= pc.rectSize;
		int frameWidth= pc.rectSize *8;
		int frameHeight= pc.rectSize *10;
		
		drawSubWind(frameX, frameY, frameWidth, frameHeight);
		
		switch(subState) {
		case 0: optionsTop(frameX,frameY); break;
		case 1: options_aboutGame(frameX, frameY);break;
		case 2: options_control(frameX, frameY); break;
		case 3: options_endGameConf(frameX, frameY); break;
		case 4: options_saveGAm(frameX, frameY);break;
		case 5: options_saved(frameX, frameY);break;
		}
		
		pc.keyI.enterPress = false;
	}
	
	public void optionsTop(int frameX, int frameY) {
		int textX;
		int textY;
		
		String text = "MOŽNOSTI";
		
		textX= getXforCenteredText(text);
		textY= frameY + pc.rectSize;
		g2.drawString(text, textX, textY);
		
		//o hre
		textX= frameX + pc.rectSize;
		textY += pc.rectSize+ (pc.rectSize/2);
		g2.drawString("O HRE" , textX, textY);
		if(selectedNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(pc.keyI.enterPress == true) {
				subState = 1;
			}
		}
		
		//music
		textY+=pc.rectSize;
		g2.drawString("HUDBA", textX, textY);
		if(selectedNum == 1) {
			g2.drawString(">", textX-25, textY);
		}
		
		//se
		textY+=pc.rectSize;
		g2.drawString("EFEKTY", textX, textY);
		if(selectedNum == 2) {
			g2.drawString(">", textX-25, textY);
		}
		
		//control
		textY+=pc.rectSize;
		g2.drawString("OVLÁDANIE", textX, textY);
		if(selectedNum == 3) {
			g2.drawString(">", textX-25, textY);
			if(pc.keyI.enterPress == true) {
				subState =2;
				selectedNum=0;
			}
		}
		
		
		//save game
		textY+=pc.rectSize;
		g2.drawString("ULOŽENIE HRY", textX, textY);
		if(selectedNum == 4) {
			g2.drawString(">", textX-25, textY);
			if(pc.keyI.enterPress == true) {
				subState=4;
				selectedNum = 0;
			}
		}
		
		
		//end game
		textY+=pc.rectSize;
		g2.drawString("KONEC HRY", textX, textY);
		if(selectedNum == 5) {
			g2.drawString(">", textX-25, textY);
			if(pc.keyI.enterPress == true) {
				subState=3;
				selectedNum = 0;
			}
		}
		
		
		
		
		//back
		textY+=pc.rectSize+25;
		g2.drawString("SPÄŤ", textX, textY);
		if(selectedNum == 6) {
			g2.drawString(">", textX-25, textY);
			if(pc.keyI.enterPress == true) {
				pc.gameState= pc.huntState;
				selectedNum=0;
			}
		}
		
		
		textX= frameX+ pc.rectSize*6;
		textY= frameY+ pc.rectSize*2 + 24;
		g2.setStroke(new BasicStroke(3));
		
		
		//music volume
		textX= frameX+ pc.rectSize*4;
		textY += pc.rectSize - (pc.rectSize/2);
		g2.drawRect(textX, textY, 120, 24); // 120 /5 = 24 
		
		int volimeWidth = 24*pc.music.volumeScale;
		g2.fillRect(textX, textY, volimeWidth, 24);
		
		//se volume
		textX= frameX+ pc.rectSize*4;
		textY += pc.rectSize;
		g2.drawRect(textX, textY, 120, 24);
		volimeWidth = 24*pc.se.volumeScale;
		g2.fillRect(textX, textY, volimeWidth, 24);
		
		textX= frameX+ pc.rectSize*6;
		textY += pc.rectSize-10;
		
		//g2.drawImage(ovca,textX , textY,null);
		
		textX= frameX+ pc.rectSize*6;
		textY += pc.rectSize;
		
		//g2.drawImage(ovca,textX , textY,null);
		
		
		pc.config.saveConf();

		
	}
  	
	public void options_control(int frameX, int frameY) {
		String text = "OVLÁDANIE";
		int textX = getXforCenteredText(text);
		int textY = frameY + pc.rectSize;
		g2.drawString(text, textX, textY);
		g2.setFont(g2.getFont().deriveFont(19F));
		
		
		
		textX = frameX + pc.rectSize;
		textY+= pc.rectSize;
		
		g2.drawString("POHYB", textX, textY);
		textY+= pc.rectSize;
		
		g2.drawString("ÚTOK", textX, textY);
		textY+= pc.rectSize;
		
		g2.drawString("STREĽBA", textX, textY);
		textY+= pc.rectSize;
		
		g2.drawString("INVENTOR", textX, textY);
		textY+= pc.rectSize;
		
		g2.drawString("PAUZA", textX, textY);
		textY+= pc.rectSize;
		
		g2.drawString("MOŽNOSTI", textX, textY);
		textY+= pc.rectSize;
		
		//back
		textX = frameX + pc.rectSize;
		textY = frameY + pc.rectSize*9;
		g2.drawString("BACK", textX, textY);
		if(selectedNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(pc.keyI.enterPress == true) {
				subState=0;
				selectedNum=3;
			}
		}
		
		textX = frameX + pc.rectSize*5;
		textY = frameY + pc.rectSize*2;
		g2.setColor(Color.yellow);
		
		g2.drawString("WASD", textX, textY);
		textY+= pc.rectSize;
		g2.drawString("ENTER", textX, textY);
		textY+= pc.rectSize;
		g2.drawString("F", textX, textY);
		textY+= pc.rectSize;
		g2.drawString("C", textX, textY);
		textY+= pc.rectSize;
		g2.drawString("P", textX, textY);
		textY+= pc.rectSize;
		g2.drawString("ESCAPE", textX, textY);
		textY+= pc.rectSize;
		
		
		
	}
	
	
	public void options_saved(int frameX,int frameY) {
		int textX = frameX + pc.rectSize;
		int textY= frameY + pc.rectSize*3;
		
		currentDialogue = "Hra bola úspešne\nuložena!";
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY+= 40;
		}
		
		//yes 
		String text = "Ok";
		textX = getXforCenteredText(text);
		textY += pc.rectSize*3;
		g2.drawString(text, textX, textY);
		if(selectedNum==0) {
			g2.drawString(">", textX-25, textY);
			if(pc.keyI.enterPress == true) {
				subState=0;
				selectedNum = 0;
				
			}
		}
	}
	
	
	public void options_saveGAm(int frameX,int frameY){
		int textX = frameX + pc.rectSize;
		int textY= frameY + pc.rectSize*3;
		
		currentDialogue = "Chceš uložiť hru ?!";
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY+= 40;
		}
		
		//yes 
		String text = "ANO";
		textX = getXforCenteredText(text);
		textY += pc.rectSize*3;
		g2.drawString(text, textX, textY);
		if(selectedNum==0) {
			g2.drawString(">", textX-25, textY);
			if(pc.keyI.enterPress == true) {
				subState=5;
				//pc.saveLoad.save();
				
				
			}
		}
		
		//no
		
		text = "NIE";
		textX = getXforCenteredText(text);
		textY += pc.rectSize;
		g2.drawString(text, textX, textY);
		if(selectedNum==1) {
			g2.drawString(">", textX-25, textY);
			if(pc.keyI.enterPress == true) {
				subState=0;
				selectedNum = 0;
			}
		}
	}
	
	
	
	public void options_endGameConf(int frameX,int frameY) {
		int textX = frameX + pc.rectSize;
		int textY= frameY + pc.rectSize*3;
		
		currentDialogue = "Si si istý že chceš\nvypnúť najlepšiu hru\nna svete?";
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY+= 40;
		}
		
		//yes 
		String text = "ANO";
		textX = getXforCenteredText(text);
		textY += pc.rectSize*3;
		g2.drawString(text, textX, textY);
		if(selectedNum==0) {
			g2.drawString(">", textX-25, textY);
			if(pc.keyI.enterPress == true) {
				subState=0;
				pc.gameState = pc.tittleState;
				tittlescreenState = 0;
				pc.stopMusic();
				//pc.resetGame(true);;
			}
		}
		
		//no
		
		text = "NIE";
		textX = getXforCenteredText(text);
		textY += pc.rectSize;
		g2.drawString(text, textX, textY);
		if(selectedNum==1) {
			g2.drawString(">", textX-25, textY);
			if(pc.keyI.enterPress == true) {
				subState=0;
				selectedNum = 5;
			}
		}
	}
	
	
	public void options_aboutGame(int frameX, int frameY) {
		int textX= frameX + pc.rectSize-20;
		int textY= frameY + pc.rectSize;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD));
		String text = "O HRE";
		
		g2.drawString(text, textX+110, textY);
		textY+= 40;
		currentDialogue = "Hru vypracoval študent 3. ročníka\nŽilinskej univerzity Marek Baláž!";
		
		Utility uTool = new Utility();
		try {
			BufferedImage uniza = ImageIO.read(getClass().getResourceAsStream("/img/uniza_znak.png"));
			unyza= uTool.scaleImage(uniza, pc.rectSize+90, pc.rectSize+90);
			g2.drawImage(unyza, textX+90, textY+60, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//g2.drawImage(keyImage, textY, textY, textY, textY, frameX, frameY, textX, textY, gs);
		
		for(String line: currentDialogue.split("\n")) {
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16f));
			g2.drawString(line, textX, textY);
			textY+= 40;
		}
		
		currentDialogue = "Kontultanom práce bol\nIng. Miroslav Benčo, PhD.";
		
		for(String line: currentDialogue.split("\n")) {
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16f));
			g2.drawString(line, textX, textY+160);
			textY+= 40;
		}
		
		textY= frameY + pc.rectSize*9;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20f));
		g2.drawString("BACK", textX, textY);
		if(selectedNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(pc.keyI.enterPress == true) {
				subState=0;
				selectedNum=0;
			}
		}
	}
	
	public void drawMess() {
		int messX = pc.rectSize;
		int messY = pc.rectSize*6;
		g2.setFont(purisaB);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,23F));
		
		for(int i =0; i < mess.size(); i++) {
			if(mess.get(i) != null) {
				g2.setColor(Color.black);
				g2.drawString(mess.get(i),messX,messY);
				
				g2.setColor(new Color(252, 127, 3,200));
				g2.drawString(mess.get(i),messX+2,messY+2);
				
				int counter = messCount.get(i) +1;	// messageCounter++
				messCount.set(i, counter);			//set the counter to the array
				messY += 50;
				
				if(messCount.get(i) > 180) {
					mess.remove(i);
					messCount.remove(i);
				}
			}
		}
	}
	
	public void drawInventory() {
		int frameX= pc.rectSize *13;
		int frameY= pc.rectSize;
		int frameWidth= pc.rectSize *6;
		int frameHeight= pc.rectSize *5;
		
		
		drawSubWind(frameX, frameY, frameWidth, frameHeight);
		
		//SLot
		final int slotXstart= frameX + 20;
		final int slotYstart= frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize= pc.rectSize+3;
		
		//player items
		for(int i = 0; i < pc.player.inventory.size(); i++) {
			
			if(pc.player.inventory.get(i) == pc.player.currentWeapon ||
					pc.player.inventory.get(i) == pc.player.currentShield) {
				g2.setColor(new Color(179,98,0));
				g2.fillRoundRect(slotX, slotY, pc.rectSize, pc.rectSize,10,10);
			}
			
			
			g2.drawImage(pc.player.inventory.get(i).down1, slotX, slotY,null);
			slotX += slotSize;
			if(i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += slotSize;
			}
		}
		
		
		int cursorX = slotXstart + (slotSize * slotCol);
		int cursorY = slotYstart + (slotSize * slotRow);
		int cursorWidth = pc.rectSize;
		int cursorHeight = pc.rectSize;
		
		//draw cursor
		g2.setColor(new Color(169,169,169));
		//g2.fillRect(cursorX, cursorY, cursorWidth, cursorHeight);
		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight,10 ,10);
		
		
		//description
		int dFrameX=frameX;
		int dFrameY= frameY+frameHeight+10;
		int dFrameWidth= frameWidth;
		int dFrameHeight= pc.rectSize*3;
		
		
		//text
		int textX = dFrameX+20;
		int textY= dFrameY+ pc.rectSize;
		g2.setFont(g2.getFont().deriveFont(20F));
		
		int itemIndex = getItemIndexOnSlot(slotCol,slotRow);
		if(itemIndex < pc.player.inventory.size()) {
			
			drawSubWind(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
			
			for(String line : pc.player.inventory.get(itemIndex).description.split("\n")) {
				g2.drawString(line, textX, textY);
				textY += 32;
			}
		}
	}
	
	public void drawCharacter() {
		//window frame
		final int frameX = pc.rectSize ;
		final int frameY = pc.rectSize;
		final int frameWidth = pc.rectSize*5;
		final int frameHeight = pc.rectSize * 10;
		drawSubWind(frameX, frameY, frameWidth, frameHeight);
		
		//text
		g2.setColor(Color.black);
		g2.setFont(purisaB);
		g2.setFont(g2.getFont().deriveFont(24F));
		
		g2.drawImage(ovca, 160, 65 , pc.rectSize,  pc.rectSize , null );
		
		int textX = frameX + 20;
		int textY = frameY+pc.rectSize;
		final int lineHeight = 35;
		
		//names
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Život", textX, textY);
		textY += lineHeight;
		g2.drawString("Mana", textX, textY);
		textY += lineHeight;
		g2.drawString("Sila", textX, textY);
		textY += lineHeight;
		g2.drawString("Obratnosť", textX, textY);
		textY += lineHeight;
		g2.drawString("Útok", textX, textY);
		textY += lineHeight;
		g2.drawString("Obrana", textX, textY);
		textY += lineHeight;
		g2.drawString("Exp", textX, textY);
		textY += lineHeight;
		g2.drawString("Další Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Dolár", textX, textY);
		textY += lineHeight+15;
		g2.drawString("Zbraň", textX, textY);
		textY += lineHeight+15;
		g2.drawString("Štít", textX, textY);
		textY += lineHeight;
		
		
		//values 
		int tailX = (frameX + frameWidth)- 30;
		//reset
		textY = frameY + pc.rectSize;
		String value;
		
		
		value= String.valueOf(pc.player.level);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value= String.valueOf(pc.player.life +"/"+ pc.player.maxLife);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value= String.valueOf(pc.player.mana +"/"+ pc.player.maxMana);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value= String.valueOf(pc.player.strength);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value= String.valueOf(pc.player.dexterity);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value= String.valueOf(pc.player.attack);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		
		value= String.valueOf(pc.player.defense);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value= String.valueOf(pc.player.exp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value= String.valueOf(pc.player.nextLevelExp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value= String.valueOf(pc.player.coin);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += 15;
		
		g2.drawImage(pc.player.currentWeapon.down1, tailX-pc.rectSize, textY, null);
		textY += pc.rectSize;
		
		g2.drawImage(pc.player.currentShield.down1, tailX-pc.rectSize, textY, null);
		textY += pc.rectSize;
	}
	
	public int getItemIndexOnSlot(int slotCol, int slotRow) {
		int itemIndex = slotCol + (slotRow*5);
		return itemIndex;
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
		
		
		
		
		y -= 100;
		
		g2.drawImage(ovca, x +10, y , pc.rectSize,  pc.rectSize , null );
		g2.drawImage(ovca, x +70, y , pc.rectSize,  pc.rectSize , null );
		g2.drawImage(ovca, x +130, y , pc.rectSize,  pc.rectSize , null );
		
		y += 115;
		
		g2.drawImage(ovca, x +10, y , pc.rectSize,  pc.rectSize , null );
		g2.drawImage(ovca, x +70, y , pc.rectSize,  pc.rectSize , null );
		g2.drawImage(ovca, x +130, y , pc.rectSize,  pc.rectSize , null );
		
		text = "SPONZORING FEYT UNYZA!";
		x = pc.rectSize * 5;
		y = pc.screenHeight / 5;
		g2.setFont(g2.getFont().deriveFont(30f));
		
		g2.setColor(Color.BLACK);
		g2.drawString(text, x, y);
		
		
		g2.setColor(Color.decode("#FF742c"));
		g2.drawString(text, x +2, y +5);
		
		x = pc.rectSize * 8 + (pc.rectSize /2);
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
				e.printStackTrace();
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
			x = (pc.rectSize*8) ;
			y = pc.rectSize*2;
			g2.drawImage(unyza, x, y,pc.rectSize-10, pc.rectSize-10,null);
			
			
			
			//text gandalf
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,30));
			x=pc.screenWidth/2 + (pc.rectSize*3);
			y += pc.rectSize*4;
			g2.setColor(Color.BLACK);
			g2.drawString("PO", x+4, y+2);
			g2.setColor(Color.decode("#FF742c"));
			g2.drawString("PO", x, y);
			g2.setColor(Color.red);
			g2.drawString("U", x+45, y);
			g2.setColor(Color.BLACK);
			g2.drawString("čNOSŤ", x+74, y+2);
			g2.setColor(Color.decode("#FF742c"));
			g2.drawString("čNOSŤ", x+70, y);
			
			g2.setColor(Color.BLACK);
			g2.drawString("VZDELÁVA", x-154, y+32);
			g2.setColor(Color.decode("#FF742c"));
			g2.drawString("VZDELÁVA", x-150, y+30);
			g2.setColor(Color.red);
			g2.drawString("N", x+45, y+30);
			g2.setColor(Color.BLACK);
			g2.drawString("IE", x+74, y+32);
			g2.setColor(Color.decode("#FF742c"));
			g2.drawString("IE", x+70, y+30);
			
			
			g2.setColor(Color.BLACK);
			g2.drawString("S", x+34, y+62);
			g2.setColor(Color.decode("#FF742c"));
			g2.drawString("S", x+30, y+60);
			g2.setColor(Color.red);
			g2.drawString("Y", x+48, y+60);
			g2.setColor(Color.BLACK);
			g2.drawString("STÉM", x+79, y+62);
			g2.setColor(Color.decode("#FF742c"));
			g2.drawString("STÉM", x+75, y+60);
			
			g2.setColor(Color.red);
			g2.drawString("Z", x+48, y+90);
			g2.setColor(Color.BLACK);
			g2.drawString("RUčNOSŤ", x+79, y+92);
			g2.setColor(Color.decode("#FF742c"));
			g2.drawString("RUčNOSŤ", x+75, y+90);
			
			
			g2.setColor(Color.BLACK);
			g2.drawString("KATASTROF", x-154, y+122);
			g2.setColor(Color.decode("#FF742c"));
			g2.drawString("KATASTROF", x-158, y+120);
			g2.setColor(Color.red);
			g2.drawString("A", x+48, y+120);
			
			
			
			
			
			
			
			
			
			
			
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
				BufferedImage stuff1 = ImageIO.read(getClass().getResourceAsStream("/img/deafaultStuffv2.png"));
				stuff11= utility.scaleImage(stuff1, pc.screenWidth, pc.screenHeight);
				g2.drawImage(stuff11, x, y,pc.rectSize*5, pc.rectSize*9,null);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			x=pc.screenWidth - (pc.rectSize*12);
			y=pc.rectSize*5;
			
			try {
				BufferedImage stuff2 = ImageIO.read(getClass().getResourceAsStream("/img/deafaultStuff2v2.png"));
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
