package JavaTetris;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;



public class Main extends JFrame{

	public Main() {
		// TODO Auto-generated constructor stub
		setTitle("Java Tetris!!");
		setResizable(false);
		setSize(500, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		JLabel score = new JLabel();
		
		GameSystem system = new GameSystem(score);
		Board board = new Board(system);
		
		
		add(score , BorderLayout.NORTH);	
		add(board , BorderLayout.CENTER);
		
		setVisible(true);
	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
		
	}

}
