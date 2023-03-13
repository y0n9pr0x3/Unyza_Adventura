package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
	PlayingCanvas pc;
	
	public Config(PlayingCanvas pc) {
		this.pc=pc;
	}
	
	public void saveConf() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
			
			bw.newLine();
			
			//music volume
			bw.write(String.valueOf(pc.music.volumeScale));
			bw.newLine();
			
			//se volume
			bw.write(String.valueOf(pc.se.volumeScale));
			bw.newLine();
			
			bw.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void loadConf() {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("config.txt"));
			
			String s= br.readLine();
			
			//music volume
			s=br.readLine();
			pc.music.volumeScale = Integer.parseInt(s);
			
			//se volume
			s=br.readLine();
			pc.se.volumeScale = Integer.parseInt(s);
			
			br.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
