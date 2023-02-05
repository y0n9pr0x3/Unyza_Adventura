package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Unyza Adventura! v0.0.1");
		
		PlayingCanvas playingCanvas = new PlayingCanvas();
		window.add(playingCanvas);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		playingCanvas.startGamingThread();
	}

}
