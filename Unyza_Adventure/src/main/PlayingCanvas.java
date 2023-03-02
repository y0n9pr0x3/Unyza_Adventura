package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.UUID;

import javax.swing.JPanel;

import character.Characters;
import character.Player;
import object.SuperObject;
import rects.RectManager;

public class PlayingCanvas extends JPanel implements Runnable{
	
	//screen set
	public final int orgRectSize = 16; //16x16 rectangle
	public final int scale = 3;		
	
	public final int rectSize = orgRectSize * scale; //48x48 rectangles and player
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = rectSize * maxScreenCol;  // 760 pixels
	public final int screenHeight = rectSize * maxScreenRow; // 576 pixels
	
	//world settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	public KeyInputs keyI = new KeyInputs(this);
	public Player player = new Player(this, keyI);
	public RectManager rectM = new RectManager(this);
	public CollisionManager collisionM = new CollisionManager(this);
	public SuperObject obj[] = new SuperObject[20];
	public Characters npc[] = new Characters[10];
	public DrawObjects dObject = new DrawObjects(this);
	public UserInter ui = new UserInter(this);
	public Sound music = new Sound();
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
		generujID();
		//playMusic(0);
		gameState = tittleState;
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
			rectM.draw(g2);
			
			for(int i=0 ; i < obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(g2, this);
				}
			}
			
			//npc
			for(int i=0 ; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].draw(g2);
				}
			}
			
			//player
			player.draw(g2);
			
			//UI
			ui.draw(g2);
			
			
			//debug
			if(keyI.checkDraw == true) {
				long drawEnd = System.nanoTime();
				long passed = drawEnd- drawStart;
				g2.setColor(Color.white);
				g2.drawString("Draw time: " + passed, 10, 400);
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
