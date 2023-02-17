import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class Tetris extends JFrame {
  private final int[][] grid = new int[20][10];
  private final Random random = new Random();

  private int currentPiece;
  private int rotation;
  private int x;
  private int y;

  public Tetris() {
    setTitle("Tetris");
    setSize(200, 440);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);

    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
          case KeyEvent.VK_UP:
            rotate();
            break;
          case KeyEvent.VK_DOWN:
            moveDown();
            break;
          case KeyEvent.VK_LEFT:
            moveLeft();
            break;
          case KeyEvent.VK_RIGHT:
            moveRight();
            break;
        }
        repaint();
      }
    });

    initGrid();
    generatePiece();
  }

  private void initGrid() {
    for (int row = 0; row < 20; row++) {
      for (int col = 0; col < 10; col++) {
        grid[row][col] = 0;
      }
    }
  }

  private void generatePiece() {
    currentPiece = random.nextInt(7) + 1;
    rotation = random.nextInt(4);
    x = 5;
    y = 0;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    for (int row = 0; row < 20; row++) {
      for (int col = 0; col < 10; col++) {
        if (grid[row][col] == 1) {
          g.fillRect(col * 20, row * 20, 20, 20);
        }
      }
    }

    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 4; col++) {
        if (Piece.SHAPES[currentPiece][rotation][row][col] == 1) {
          g.fillRect((col + x) * 20, (row + y) * 20, 20, 20);
        }
      }
    }
  }

  public static void main(String[] args) {
    Tetris tetris = new Tetris();
    tetris.setVisible(true);

    while (true) {
      try {
        Thread.sleep(1000);
        tetris.moveDown();
        tetris.repaint();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void rotate() {
    int nextRotation = (rotation + 1) % 4;
    if (checkRotation(nextRotation)) {
      rotation =nextRotation;
    }
  }
  
  private void moveDown() {
	  if (checkMovement(x, y + 1, rotation)) {
		  y++;
	  } else {
		  lockPiece();
		  generatePiece();
	  }
	  }

	  private void moveLeft() {
	  if (checkMovement(x - 1, y, rotation)) {
	  x--;
	  }
	  }

	  private void moveRight() {
	  if (checkMovement(x + 1, y, rotation)) {
	  x++;
	  }
	  }

	  private boolean checkRotation(int nextRotation) {
	  for (int row = 0; row < 4; row++) {
	  for (int col = 0; col < 4; col++) {
	  int pieceRow = row + y;
	  int pieceCol = col + x;
}