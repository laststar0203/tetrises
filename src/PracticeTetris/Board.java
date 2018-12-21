package PracticeTetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import PracticeTetris.Shape.Tetrominose;

public class Board extends JPanel implements ActionListener{

	private static final int BOARD_WIDTH = 10;
	private static final int BOARD_HEIGHT = 22;
	private Timer timer;
	private boolean isFallingFinished = false;
	private boolean isStarted = false;
	private boolean isPaused = false;
	private int numLinesRemoved = 0;
	private int curX = 0;
	private int curY = 0;
	private JLabel stausBar;
	private Shape curPiece;
	private Tetrominose[] board;
	
	public Board(Tetris parent) {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
