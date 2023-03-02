package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputs implements KeyListener{
	
	PlayingCanvas pc;
	public boolean upPress, downPress, leftPress, rightPress, enterPress;
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
		else if(pc.gameState == pc.huntState) {
			
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
			if(code == KeyEvent.VK_ENTER) {
				enterPress = true;
			}
			
			//debug
			if(code == KeyEvent.VK_T) {
				if(checkDraw == false) {
					checkDraw = true;
				}else if(checkDraw == true) {
					checkDraw = false;
				}
			}
		}
		
		else if(pc.gameState == pc.pauseState) {
			if(code == KeyEvent.VK_P) {
				pc.gameState = pc.huntState;
			}
		}
		
		else if(pc.gameState == pc.dialogState) {
			if(code == KeyEvent.VK_ENTER) {
				pc.gameState = pc.huntState;
			}
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
		
	}

}
