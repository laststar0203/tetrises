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
	public static final int[][] board = new int[10][20];
	private Timer timer;
	private final int FPS = 60;
	private final int delay = 1000 / FPS;

	private JLabel window;

	private Score score;

	public Board(Score score) {

		this.score = score;
		window = new JLabel();

		add(window);

		timer = new Timer(delay, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				printScreen();
			}
		});
		timer.start();

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
