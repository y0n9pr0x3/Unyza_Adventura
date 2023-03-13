package main;


import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/img/fakla.jpg")));
		window.setTitle("Unyza Adventura! v0.1.9");
		
		PlayingCanvas playingCanvas = new PlayingCanvas();
		window.add(playingCanvas);
		playingCanvas.config.loadConf();
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		playingCanvas.setupGame();
		playingCanvas.startGamingThread();
	}

}
