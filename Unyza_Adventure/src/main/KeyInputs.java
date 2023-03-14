package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputs implements KeyListener{
	
	PlayingCanvas pc;
	public boolean upPress, downPress, leftPress, rightPress, enterPress, shotPress;
	public boolean checkDraw = false;
	
	public KeyInputs(PlayingCanvas pc ) {
		this.pc = pc;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(pc.gameState == pc.tittleState) {
			tittleState(code);
		}
		else if(pc.gameState == pc.huntState) {
			huntState(code);
		}
		
		else if(pc.gameState == pc.pauseState) {
			pauseState(code);
		}
		
		else if(pc.gameState == pc.dialogState) {
			dialogueState(code);
		}
		else if(pc.gameState == pc.characterState) {
			characterState(code);
		}
		else if(pc.gameState == pc.optionState) {
			optionState(code);
		}
		else if(pc.gameState == pc.gameOverState) {
			gameOverState(code);
		}
		else if(pc.gameState == pc.tradingState) {
			tradeState(code);
		}
	}
	
	public void tittleState(int code) {
		if(pc.ui.tittlescreenState == 0) {
			
			if(code == KeyEvent.VK_W) {
				pc.ui.selectedNum--;
				pc.playSE(9);
				if(pc.ui.selectedNum < 0) {
					pc.ui.selectedNum = 3;
				}
			}
			if(code == KeyEvent.VK_S) {
				pc.ui.selectedNum++;
				pc.playSE(9);
				if(pc.ui.selectedNum > 3) {
					pc.ui.selectedNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(pc.ui.selectedNum == 0) {
					pc.ui.tittlescreenState = 1;
					//pc.playMusic(0);
				}
				if(pc.ui.selectedNum == 1) {
					//load game
				}
				if(pc.ui.selectedNum == 2) {
					//options
				}
				if(pc.ui.selectedNum == 3) {
					System.exit(0);
				}
			}
		}
		else if(pc.ui.tittlescreenState == 1) {
			
			if(code == KeyEvent.VK_W) {
				pc.ui.selectedNum--;
				pc.playSE(9);
				if(pc.ui.selectedNum < 0) {
					pc.ui.selectedNum = 1;
				}
			}
			if(code == KeyEvent.VK_S) {
				pc.ui.selectedNum++;
				pc.playSE(9);
				if(pc.ui.selectedNum > 1) {
					pc.ui.selectedNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(pc.ui.selectedNum == 0) {
					pc.gameState = pc.huntState;
					pc.playMusic(0);
				}
				if(pc.ui.selectedNum == 1) {
					pc.ui.tittlescreenState = 0;
					pc.ui.selectedNum =0;
				}
			}
		}
	}
	
	public void tradeState(int code) {
		if(code == KeyEvent.VK_ENTER) {
			enterPress = true;
		}
		
		if(pc.ui.subState == 0) {
			if(code == KeyEvent.VK_W) {
				pc.ui.selectedNum--;
				if(pc.ui.selectedNum < 0) {
					pc.ui.selectedNum = 2;
				}
				pc.playSE(9);
			}
			if(code == KeyEvent.VK_S) {
				pc.ui.selectedNum++;
				if(pc.ui.selectedNum > 2) {
					pc.ui.selectedNum = 0;
				}
				pc.playSE(9);
			}
		}
		if(pc.ui.subState == 1) {
			npcInventory(code);
			if(code == KeyEvent.VK_ESCAPE) {
				pc.ui.subState =0;
			}
		}
		
		if(pc.ui.subState == 2) {
			playerInventory(code);
			if(code == KeyEvent.VK_ESCAPE) {
				pc.ui.subState =0;
			}
		}
	}
	
	public void gameOverState(int code) {
		if(code == KeyEvent.VK_W) {
			pc.ui.selectedNum--;
			if(pc.ui.selectedNum < 0) {
				pc.ui.selectedNum=1;
			}
			pc.playSE(9);
		}
		if(code == KeyEvent.VK_S) {
			pc.ui.selectedNum++;
			if(pc.ui.selectedNum > 1) {
				pc.ui.selectedNum=0;
			}
			pc.playSE(9);
		}
		if(code == KeyEvent.VK_ENTER) {
			if(pc.ui.selectedNum == 0) {
				pc.retry();
				pc.gameState = pc.huntState;
				
			}else if(pc.ui.selectedNum == 1) {
				pc.gameState = pc.tittleState;
				pc.ui.tittlescreenState = 0;
				pc.restart();
				pc.ui.selectedNum = 0;
				
			}
		}
	}
	
	public void optionState(int code) {
		if(code == KeyEvent.VK_ESCAPE) {
			pc.gameState = pc.huntState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPress = true;
		}
		int maxSelectNum = 0;
		
		switch(pc.ui.subState) {
		case 0: maxSelectNum=6; break;
		case 1: maxSelectNum=0; break;
		case 3: maxSelectNum=1; break;
		case 4: maxSelectNum=1; break;
		}
		
		if(code == KeyEvent.VK_W) {
			pc.ui.selectedNum--;
			pc.playSE(9);
			if(pc.ui.selectedNum < 0) {
				pc.ui.selectedNum = maxSelectNum;
			}
		}
		if(code == KeyEvent.VK_S) {
			pc.ui.selectedNum++;
			pc.playSE(9);
			if(pc.ui.selectedNum > maxSelectNum) {
				pc.ui.selectedNum = 0;
			}
		}
		
		if(code == KeyEvent.VK_A) {
			if(pc.ui.subState == 0) {
				if(pc.ui.selectedNum == 1 && pc.music.volumeScale > 0) {
					pc.music.volumeScale--;
					pc.music.checkVolume();
					pc.playSE(9);
				}
				if(pc.ui.selectedNum == 2 && pc.se.volumeScale > 0) {
					pc.se.volumeScale--;
					pc.playSE(9);
				}
			}
		}
		
		if(code == KeyEvent.VK_D) {
			if(pc.ui.subState == 0) {
				if(pc.ui.selectedNum == 1 && pc.music.volumeScale < 5) {
					pc.music.volumeScale++;
					pc.music.checkVolume();
					pc.playSE(9);
				}
				if(pc.ui.selectedNum == 2 && pc.se.volumeScale < 5) {
					pc.se.volumeScale++;
					pc.playSE(9);
				}
			}
		}
	}
	
	public void huntState(int code) {

		if(code == KeyEvent.VK_W) {
			upPress = true;
		}
		if(code == KeyEvent.VK_S) {
			downPress= true;
		}
		if(code == KeyEvent.VK_A) {
			leftPress= true;
		}
		if(code == KeyEvent.VK_D) {
			rightPress = true;
		}
		if(code == KeyEvent.VK_P) {
			pc.gameState = pc.pauseState;
		}
		if(code == KeyEvent.VK_C) {
			pc.gameState = pc.characterState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPress = true;
		}
		if(code == KeyEvent.VK_F) {
			shotPress = true;
		}
		if(code == KeyEvent.VK_ESCAPE) {
			pc.gameState = pc.optionState;
		}
		
		//debug
		if(code == KeyEvent.VK_T) {
			if(checkDraw == false) {
				checkDraw = true;
			}else if(checkDraw == true) {
				checkDraw = false;
			}
		}
		if(code == KeyEvent.VK_R) {
			switch(pc.currentMap) {
			case 0: pc.rectM.loadMap("/map/world2.txt",0);break;
			case 1: pc.rectM.loadMap("/map/interior01.txt",0);break;
			}
			
		}
	}
	
	public void pauseState(int code) {
		if(code == KeyEvent.VK_P) {
			pc.gameState = pc.huntState;
		}
	}

	public void dialogueState(int code) {
		if(code == KeyEvent.VK_ENTER) {
			pc.gameState = pc.huntState;
		}
	}
	
	
	public void playerInventory(int code) {
		if(code == KeyEvent.VK_W) {
			if(pc.ui.playerSlotRow != 0) {
				pc.playSE(9);
				pc.ui.playerSlotRow --;
			}
		}
		if(code == KeyEvent.VK_S) {
			if(pc.ui.playerSlotRow != 3) {
				pc.playSE(9);
				pc.ui.playerSlotRow ++;
			}
		}
		if(code == KeyEvent.VK_A) {
			if(pc.ui.playerSlotCol != 0) {
				pc.playSE(9);
				pc.ui.playerSlotCol --;
			}
		}
		if(code == KeyEvent.VK_D) {
			if(pc.ui.playerSlotCol != 4) {
				pc.playSE(9);
				pc.ui.playerSlotCol ++;
			}
		}
	}
	
	public void npcInventory(int code) {
		if(code == KeyEvent.VK_W) {
			if(pc.ui.npcRow != 0) {
				pc.playSE(9);
				pc.ui.npcRow --;
			}
		}
		if(code == KeyEvent.VK_S) {
			if(pc.ui.npcRow != 3) {
				pc.playSE(9);
				pc.ui.npcRow ++;
			}
		}
		if(code == KeyEvent.VK_A) {
			if(pc.ui.npcCol != 0) {
				pc.playSE(9);
				pc.ui.npcCol --;
			}
		}
		if(code == KeyEvent.VK_D) {
			if(pc.ui.npcCol != 4) {
				pc.playSE(9);
				pc.ui.npcCol ++;
			}
		}
	}
	
	public void characterState(int code) {
		if(code == KeyEvent.VK_C) {
			pc.gameState = pc.huntState;
		}
		if(code == KeyEvent.VK_ENTER) {
			pc.player.selectItem();
		}
		playerInventory(code);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPress = false;
		}
		if(code == KeyEvent.VK_S) {
			downPress= false;
		}
		if(code == KeyEvent.VK_A) {
			leftPress= false;
		}
		if(code == KeyEvent.VK_D) {
			rightPress = false;
		}
		if(code == KeyEvent.VK_F) {
			shotPress = false;
		}
		
	}

}
