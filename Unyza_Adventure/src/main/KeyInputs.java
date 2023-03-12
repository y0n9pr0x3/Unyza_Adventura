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
		
		//debug
		if(code == KeyEvent.VK_T) {
			if(checkDraw == false) {
				checkDraw = true;
			}else if(checkDraw == true) {
				checkDraw = false;
			}
		}
		if(code == KeyEvent.VK_R) {
			pc.rectM.loadMap("/map/world2.txt");
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
	
	public void characterState(int code) {
		if(code == KeyEvent.VK_C) {
			pc.gameState = pc.huntState;
		}
		if(code == KeyEvent.VK_W) {
			if(pc.ui.slotRow != 0) {
				pc.playSE(9);
				pc.ui.slotRow --;
			}
		}
		if(code == KeyEvent.VK_S) {
			if(pc.ui.slotRow != 3) {
				pc.playSE(9);
				pc.ui.slotRow ++;
			}
		}
		if(code == KeyEvent.VK_A) {
			if(pc.ui.slotCol != 0) {
				pc.playSE(9);
				pc.ui.slotCol --;
			}
		}
		if(code == KeyEvent.VK_D) {
			if(pc.ui.slotCol != 4) {
				pc.playSE(9);
				pc.ui.slotCol ++;
			}
		}
		if(code == KeyEvent.VK_ENTER) {
			pc.player.selectItem();
		}
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
