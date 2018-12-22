package PracticeTetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import PracticeTetris.Shape.Tetrominose;

public class Board extends JPanel implements ActionListener {

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
		setFocusable(true);
		// 키 이벤트의 포커스르 받을 수 있는 컴포넌트가 여러개 있을 때 우선적으로 입력받기 위해 설정
		curPiece = new Shape();
		timer = new Timer(400, this);
		stausBar = parent.getStatusBar();
		board = new Tetrominose[BOARD_HEIGHT * BOARD_WIDTH];
		clearBoard();
	}

	public int squareWidth() {
		return (int) getSize().getWidth() / BOARD_WIDTH;
	}

	public int squareHeight() {
		return (int) getSize().getHeight() / BOARD_HEIGHT;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public Tetrominose shapeAt(int x, int y) {
		return board[y * BOARD_WIDTH + x];
	}

	public void clearBoard() {
		for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; i++) {
			board[i] = Tetrominose.NoShape;
		}
	}
	
	private void pieceDropped() {
		for (int i = 0; i < 4; i++) {
			int x = curX + curPiece.x(i);
			int y = curY - curPiece.y(i);
			board[y * BOARD_WIDTH + x] = curPiece.getShape();
		}
	}

}
