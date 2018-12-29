package JavaTetris;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

import javax.sound.midi.Synthesizer;

public class Shape {

	int[][] shape;
	private int x = 0, y = 0, moveX = 0;
	int[][] beforeBoard; // 초기화값
	boolean isCollision = false, canMoveX = false;
	Board board;
	int width, height;
	long lastTime, time;
	long fastTime = 120, normalTime = 1200, currentTime;
	int code;

	public Shape(int[][] shape, Board board, int code) {
		
		System.out.println("newShape");
		
		this.shape = shape;
		this.board = board;
		this.code = code;

		width = board.getWIDTH();
		height = board.getHEIGHT();

		beforeBoard = new int[height][width];

		for (int i = 0; i < board.getBoard().length; i++) {
			beforeBoard[i] = Arrays.copyOf(board.getBoard()[i], board.getBoard()[i].length);
		}

		x = 3;
		y = 0;

		currentTime = normalTime;

	}

	public int[][] getShape() {
		return shape;
	}

	public void update() {

		if (isCollision) {

		
			
			for (int i = 0; i < shape.length; i++) {
				for (int j = 0; j < shape[i].length; j++) {

					if (shape[i][j] != 0) {
						board.getBoard()[y + i][x + j] = code;

					}
				}
			}
			checkLine();
			board.nextShape();

		}

		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		printShape();

		if (!isCollision)
			Move();

		moveX = 0;
		canMoveX = true;

	}

	private void checkLine() {

		// TODO Auto-generated method stub
		int height = board.getBoard().length - 1;

		for (int i = height; i > 0; i--) { // 아래서부터

			int count = 0;
			for (int j = 0; j < board.getBoard()[0].length; j++) {

				if (board.getBoard()[i][j] != 0)
					count++;
				board.getBoard()[height][j] = board.getBoard()[i][j];
				// height 변수 값에 변화가 있으면 가장 바깥 반복문에 i 값과 같아 변화가 없지만 변화가 없다면 값이 달라(변수 height 값이 큼)
				// 뒤 바뀜이 일어나게 됨
				

			}
			if (count < board.getBoard()[0].length) {
				height--;
				
			}else {
				board.system.setScore(100);
			}
			
		}
		
	
	

	}

	private void Move() {

		if (!(moveX + x < 0) && !(moveX + x + shape[0].length > width)) {

			for (int i = 0; i < shape.length; i++) {
				for (int j = 0; j < shape[i].length; j++) {

					int compare = board.getBoard()[y + i][x + j + moveX];

					if (compare != 0 && compare != code) {
						canMoveX = false;

					}
				}
			}

			if (canMoveX)
				x += moveX;
		}
		// x

		if (!(y + shape.length + 1 > height)) { // y++ 더하기 전

			for (int i = 0; i < shape.length; i++) {
				for (int j = 0; j < shape[i].length; j++) {

					if(shape[i][j] !=0) {
					int compare = board.getBoard()[y + i + 1 ][x + j];

					if (compare != 0 && compare != code) {
						isCollision = true;

					}
					}
				}
			}

			if (time > currentTime) {
				y++;
				time = 0;
			}

		} else {
			isCollision = true;
		}
		// y

	}

	public void setMoveValue(int x) {
		this.moveX = x;
	}

	private void printShape() {

		for (int i = 0; i < beforeBoard.length; i++) {
			board.getBoard()[i] = Arrays.copyOf(beforeBoard[i], beforeBoard[i].length);
		}

		for (int i = 0; i < shape.length; i++) {
			for (int j = 0; j < shape[i].length; j++) {

				if (shape[i][j] != 0) {
					board.getBoard()[y + i][x + j] = code;

				}
			}
		}
		
	}

	public void rotate() {

		if (isCollision)
			return;

		int[][] compare = getTransPose(shape);

		if (x + compare[0].length > 20 || y + compare.length > 10)
			return;

		for (int i = 0; i < compare.length; i++) {
			for (int j = 0; j < compare[i].length; j++) {
				if (board.getBoard()[i + y][j + x] != 0 && board.getBoard()[i + y][j + x] != code) {
					System.out.println("Don't Turn");
					return;
				}
			}
		}

		shape = compare;

	}

	private int[][] getTransPose(int[][] shape) {

		int[][] newShape = new int[shape[0].length][shape.length]; // x와 y가 바뀌므로

		for (int i = 0; i < shape.length; i++) {
			for (int j = 0; j < shape[0].length; j++) {
				newShape[j][i] = shape[i][j];
			}

		}

		return getReverseMatrix(newShape);

	}

	private int[][] getReverseMatrix(int[][] shape) {

		int middel = shape.length / 2;

		for (int i = 0; i < middel; i++) {
			int[] m = shape[i];
			shape[i] = shape[shape.length - i - 1];
			shape[shape.length - 1 - i] = m;
		}

		return shape;
	}

	public void speedUp() {
		currentTime = fastTime;

	}

	public void speedDown() {
		currentTime = normalTime;

	}

}
