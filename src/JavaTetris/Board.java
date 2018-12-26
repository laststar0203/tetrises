package JavaTetris;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;



//JLabel은 개행문자 즉 줄바꿈을 할 수 없음

public class Board extends JPanel implements KeyListener {

	private final int HEIGHT = 10, WIDTH = 20;
	public static int[][] board = new int[10][20];
	private Timer timer;
	private final int FPS = 1;
	private final int delay = 1000 / FPS;

	private Shape[] shapes = new Shape[7];
	
	private JLabel window;

	private Score score;

	private Shape currentBoard;

	public Board(Score score) {

		this.score = score;
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
		
		shapes[0] = new Shape(new int[][] { 
			{ 1, 1, 1, 1 } } , this); // ISHPE

		shapes[1] = new Shape( new int[][] { 
			{ 1, 1, 0 },
			{ 0, 1, 1 }} , this); // ZSHPE
		
		shapes[2] = new Shape( new int[][] { 
			{ 0, 1, 1 },
			{ 1, 1, 0 }} , this); // S-SHAPE
		
		shapes[3] = new Shape(new int[][] { 
			{ 1, 1, 1 },
			{ 0, 0, 1 }} , this); // J-SHAP
		
		shapes[4] = new Shape( new int[][] { 
			{ 1, 1, 1 },
			{ 1, 0, 0 }} , this); // L-SHAP
		
		shapes[5] = new Shape( new int[][] { 
			{ 1, 1, 1 },
			{ 0, 1, 0 }} , this); // T-SHAP

		shapes[6] = new Shape(new int[][] { 
			{ 1, 1 },
			{ 1, 1 }}, this); // O-SHAP
		
		
		currentBoard = shapes[2];
		

	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	private void printScreen() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 1)
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

		currentBoard.update();
		printScreen();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
