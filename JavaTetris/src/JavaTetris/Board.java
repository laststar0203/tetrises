package JavaTetris;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import javafx.scene.input.KeyCode;

//JLabel은 개행문자 즉 줄바꿈을 할 수 없음

public class Board extends JPanel implements KeyListener {

	private final int HEIGHT = 10, WIDTH = 20;
	public int[][] board = new int[10][20];

	public int[][] getBoard() {
		return board;
	}

	private Timer timer;
	private final int FPS = 50;
	private final int delay = 1000 / FPS;

	private int[][][] shapes = new int[7][][];

	private JLabel window;

	public Score system;

	private Shape currentBoard;

	private int number = 0;

	private boolean isGameOver = false;

	public Board(Score score) {

		this.system = score;
		window = new JLabel();

		add(window);

		timer = new Timer(delay, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				update();
			}
		});
		timer.start();

		shapes[0] = new int[][] { { 1, 1, 1, 1 } }; // ISHPE

		shapes[1] = new int[][] { { 1, 1, 0 }, { 0, 1, 1 } }; // ZSHPE

		shapes[2] = new int[][] { { 0, 1, 1 }, { 1, 1, 0 } }; // S-SHAPE

		shapes[3] = new int[][] { { 1, 1, 1 }, { 0, 0, 1 } }; // J-SHAP

		shapes[4] = new int[][] { { 1, 1, 1 }, { 1, 0, 0 } }; // L-SHAP

		shapes[5] = new int[][] { { 1, 1, 1 }, { 0, 1, 0 } }; // T-SHAP

		shapes[6] = new int[][] { { 1, 1 }, { 1, 1 } }; // O-SHAP

		nextShape();

	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public void printScreen() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] != 0)
					sb.append("■");
				else
					sb.append("□");
			}
			// sb.append("\n");
			sb.append(System.getProperty("line.separator")); // 자바에서 시스템에 독립적인 줄바꿈
		}

		// window.setText(sb.toString());
		window.setText("<html>" + sb.toString().replaceAll(System.getProperty("line.separator"), "<br/>") + "</html>"); // jLabel은
																														// 줄바꿈이
																														// 안일어나기
																														// 때문에
																														// html을
																														// 이용해
																														// 주어야한다.
	}

	private void update() {
		if (isGameOver)
			gameOver();

		currentBoard.update();
		printScreen();
	}

	private void gameOver() {
		timer.stop();

		JFrame scoreBox = new JFrame("결과표");
		scoreBox.setResizable(false);
		scoreBox.setLocationRelativeTo(null);
		scoreBox.setLayout(null);
		scoreBox.setSize(400, 200);
		scoreBox.setVisible(true);
		
		JTextField result = new JTextField();
		

		Calendar calendar = Calendar.getInstance();
		java.util.Date date = calendar.getTime();
		String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));

		try {
			system.saveScore(today, system.getScore());
			System.out.println(system.loadScore());
			result.setText(system.loadScore());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			currentBoard.setMoveValue(-1);
			break;
		case KeyEvent.VK_RIGHT:
			currentBoard.setMoveValue(1);
			break;
		case KeyEvent.VK_UP:
			currentBoard.rotate();
			break;
		case KeyEvent.VK_DOWN:
			currentBoard.speedUp();

			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			currentBoard.speedDown();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void nextShape() {
		// TODO Auto-generated method stub

		int random = ((int) (Math.random() * shapes.length));

		Shape newShape = new Shape(shapes[random], this, ++number);

		for (int i = 0; i < newShape.getShape().length; i++) {
			for (int j = 0; j < newShape.getShape()[i].length; j++) {
				if (newShape.getShape()[i][j] != 0) {
					if (board[i][j + 3] != 0) {
						isGameOver = true;
						return;
					}
				}
			}
		}

		currentBoard = newShape;
	}

}
