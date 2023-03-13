package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

import javax.swing.JPanel;

import character.Characters;
import character.Player;
import rects.RectManager;
import rects_intera.InteractiveRect;

@SuppressWarnings("serial")
public class PlayingCanvas extends JPanel implements Runnable{
	
	//screen set
	public final int orgRectSize = 16; //16x16 rectangle
	public final int scale = 3;		
	
	public final int rectSize = orgRectSize * scale; //48x48 rectangles and player
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 12;
	public final int screenWidth = rectSize * maxScreenCol;  // 960 pixels
	public final int screenHeight = rectSize * maxScreenRow; // 576 pixels
	
	//world settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
	
	public KeyInputs keyI = new KeyInputs(this);
	public Player player = new Player(this, keyI);
	public RectManager rectM = new RectManager(this);
	public CollisionManager collisionM = new CollisionManager(this);
	public Characters obj[] = new Characters[20];
	public Characters npc[] = new Characters[10];
	public Characters mon[] = new Characters[20];
	public ArrayList<Characters> characList = new ArrayList<>();
	public ArrayList<Characters> projectileList = new ArrayList<>();
	public InteractiveRect iRect[] = new InteractiveRect[50];
	public DrawObjects dObject = new DrawObjects(this);
	public Events events = new Events(this);
	public UserInter ui = new UserInter(this);
	public Sound music = new Sound();
	public Config config = new Config(this);
	public Sound se = new Sound();
	Thread gamingThread;
	
	String id;
	
	//state
	public int gameState;
	public final int tittleState = 0;
	public final int huntState = 1;
	public final int pauseState = 2;
	public final int dialogState =3;
	public final int characterState=4;
	public final int optionState = 5;
	public final int gameOverState=6;
	public final int transitionState=7;
	public final int tradingState=8;
	public final int sleepState=9;
	
	
	int FPS = 60;
	
	public PlayingCanvas() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyI);
		this.setFocusable(true);
	}
	
	
	public void setupGame() {
		dObject.setObject();
		dObject.setNpc();
		dObject.setMon();
		dObject.setInterRect();
		generujID();
		//playMusic(0);
		gameState = tittleState;
		
		
		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		g2= (Graphics2D)tempScreen.getGraphics();
	}
	
	public void startGamingThread() {
		gamingThread = new Thread(this);
		gamingThread.start();
	}

	@Override
	public void run() {
		
		double drawInter = 1000000000/FPS; 
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer= 0;
		int drawCount = 0;
		
		while(gamingThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime ) / drawInter;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			if(timer >= 1000000000) {
				System.out.println("FPS: "+ drawCount);
				drawCount=0;
				timer=0;
			}
		}
		
	}
	
	public void update() {
		if(gameState == huntState) {
			//player
			player.update();
			
			//npc
			for(int i=0; i< npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			
			//mon
			for(int i=0; i< mon.length; i++) {
				if(mon[i] != null) {
					if(mon[i].alive == true && mon[i].dying == false ) {

						mon[i].update();
					}
					if(mon[i].alive == false) {
						mon[i].checkDrop();
						mon[i]= null;
					}
				}
			}
			
			//projectile
			for(int i=0; i< projectileList.size(); i++) {
				if(projectileList.get(i) != null) {
					if(projectileList.get(i).alive == true ) {

						projectileList.get(i).update();
					}
					if(projectileList.get(i).alive == false) {

						projectileList.remove(i);
					}
				}
			}
			
			//iRect
			for(int i = 0; i < iRect.length ; i++) {
				if(iRect[i] != null) {
					iRect[i].update();
				}
			}
		}
		if(gameState == pauseState) {

		}
	}

	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		
		//debug
		long drawStart=0;
		if(keyI.checkDraw == true) {
			drawStart = System.nanoTime();
		}
		
		//tittle screen
		if(gameState == tittleState) {
			ui.draw(g2);
			
		}else {
			
			//rects
			rectM.draw(g2);
			
			for(int i = 0; i < iRect.length ; i++) {
				if(iRect[i] != null) {
					iRect[i].draw(g2);;
				}
			}
			
			
			//add characters to list
			characList.add(player);
			
			for(int i = 0; i < npc.length ; i++) {
				if(npc[i] != null) {
					characList.add(npc[i]);
				}
			}
			
			for(int i = 0; i < obj.length ; i++) {
				if(obj[i] != null) {
					characList.add(obj[i]);
				}
			}
			
			for(int i = 0; i < mon.length ; i++) {
				if(mon[i] != null) {
					characList.add(mon[i]);
				}
			}
			
			for(int i = 0; i < projectileList.size() ; i++) {
				if(projectileList.get(i) != null) {
					characList.add(projectileList.get(i));
				}
			}
			
			
			//sort
			Collections.sort(characList, new Comparator<Characters>() {

				@Override
				public int compare(Characters o1, Characters o2) {
					int result = Integer.compare(o1.worldY, o2.worldY);
					return result;
				}
				
			});
			
			//draw characters
			for(int i = 0; i < characList.size() ; i++) {
				characList.get(i).draw(g2);
			}
			
			//reset
			characList.clear();
			
			//UI
			ui.draw(g2);
			
			
			//debug
			if(keyI.checkDraw == true) {
				long drawEnd = System.nanoTime();
				long passed = drawEnd- drawStart;
				g2.setColor(Color.white);
				
				int x = 10;
				int y = 400;
				int lineHeight = 20;
				g2.drawString("World X: " + player.worldX, x, y); y += lineHeight;
				g2.drawString("World Y: " + player.worldY,x, y );y += lineHeight;
				g2.drawString("Col: " + (player.worldX + player.solidRect.x)/rectSize ,x, y );y += lineHeight;
				g2.drawString("Row: " + (player.worldY + player.solidRect.y)/rectSize,x, y );y += lineHeight;
				g2.drawString("Draw time: " + passed, x , y );
			}
			
			g2.dispose();
		}
	}
	
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
	
	public String generujID() {
		id = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toLowerCase();
		return  id;
	}
}
