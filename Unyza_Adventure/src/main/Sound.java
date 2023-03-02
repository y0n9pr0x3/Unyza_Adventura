package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sound/gameMusic.wav");
		soundURL[1] = getClass().getResource("/sound/coin.wav");
		soundURL[2] = getClass().getResource("/sound/powerup.wav");
		soundURL[3] = getClass().getResource("/sound/unlock.wav");
		soundURL[4] = getClass().getResource("/sound/fanfare.wav");
		soundURL[5]= getClass().getResource("/sound/hitmonster.wav");
		soundURL[6]= getClass().getResource("/sound/receivedamage.wav");
		soundURL[7]= getClass().getResource("/sound/swing.wav");
		soundURL[8]= getClass().getResource("/sound/levelup.wav");
		soundURL[9]= getClass().getResource("/sound/cursor.wav");
		soundURL[10]= getClass().getResource("/sound/burning.wav");
		soundURL[11]= getClass().getResource("/sound/cuttree.wav");
		soundURL[12]= getClass().getResource("/sound/gameover.wav");
		soundURL[13]= getClass().getResource("/sound/stairs.wav");
		soundURL[14]= getClass().getResource("/sound/uniza_level_music1.wav");
		soundURL[15]= getClass().getResource("/sound/sleep.wav");
	}
	
	public void setFile(int i) {
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
}	
