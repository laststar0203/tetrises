package JavaTetris;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Shape {

	final int[][] shape;
	int x = 0, y = 0;
	final int[][] beforeBoard; //초기화값
	boolean iscollection = false;
	Board board;
	int width, height;
	
	public Shape(int[][] shape , Board board) {
		this.shape = shape;
		this.board = board;
		
		width = board.getWIDTH();
		height = board.getHEIGHT();
		
		beforeBoard = new int[height][width];
		
		for (int i = 0; i < beforeBoard.length; i++) {
			Board.board[i] = Arrays.copyOf(beforeBoard[i], beforeBoard[i].length);
		}
		
		
		x = 3; y= 0;
		
	}
	
	public void update() {
		
		
		if(!(y + 1 > height)) y++;
		printShape();
	}
	
	private void move(int x) {
		
	}
	
	private void printShape() {
		for (int i = 0; i < beforeBoard.length; i++) {
			Board.board[i] = Arrays.copyOf(beforeBoard[i], beforeBoard[i].length);
		}
		
		System.out.println("초기화");
		for (int i = 0; i < shape.length; i++) {
			for (int j = 0; j < shape[i].length; j++) {
				
				if(shape[i][j] != 0) {
					Board.board[y + i][x + j] = 1;
					
				}
			}
		}
	}

	
	
}
