package JavaTetris;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Main extends JFrame {

	public Main() {
		// TODO Auto-generated constructor stub
		setTitle("Java Tetris!!");
		setResizable(false);
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout());

		JLabel scoreBox = new JLabel("0점");
		

		Score score = new Score(scoreBox);
		Board board = new Board(score);

		add(scoreBox, BorderLayout.NORTH);
		getContentPane().add(board, BorderLayout.CENTER); // 상속으로 Jpanel를 만들었을때만
		addKeyListener(board);
		

		setVisible(true);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();

	}

}
