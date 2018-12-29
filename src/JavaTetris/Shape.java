package JavaTetris;

import java.lang.reflect.Array;
import java.util.Arrays;

import javax.sound.midi.Synthesizer;

public class Shape {

	final int[][] shape;
	private int x = 0, y = 0, moveX = 0;
	final int[][] beforeBoard; // 초기화값
	boolean isCollision = false, canMoveX = false;
	Board board;
	int width, height;
	long lastTime, time;
	long fastTime = 60, normalTime = 600, currentTime;
	int code;

	public Shape(int[][] shape, Board board, int code) {
		this.shape = shape;
		this.board = board;
		this.code = code;

		width = board.getWIDTH();
		height = board.getHEIGHT();

		beforeBoard = new int[height][width];

		for (int i = 0; i < beforeBoard.length; i++) {
			Board.board[i] = Arrays.copyOf(beforeBoard[i], beforeBoard[i].length);
		}

		x = 3;
		y = 0;

		currentTime = normalTime;

	}

	public void update() {

		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		printShape();

		if (!isCollision)
			Move();

		moveX = 0;
		canMoveX = true;

	}

	private void Move() {

		if (!(moveX + x < 0) && !(moveX + x + shape[0].length > width)) {

			for (int i = 0; i < shape.length; i++) {
				for (int j = 0; j < shape[i].length; j++) {

					System.out.println((y + 1) + " : " + (x + j + moveX));

					int compare = Board.board[y + i][x + j + moveX];

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

					int compare = Board.board[y + i + 1][x + j];

					if (!(compare == 0 || compare == code)) {
						isCollision = true;

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
			Board.board[i] = Arrays.copyOf(beforeBoard[i], beforeBoard[i].length);
		}

		for (int i = 0; i < shape.length; i++) {
			for (int j = 0; j < shape[i].length; j++) {

				if (shape[i][j] != 0) {
					Board.board[y + i][x + j] = code;

				}
			}
		}
	}

	public void rotate() {

		getTransPose(shape);
	}

	private int[][] getTransPose(int[][] shape) {

		int[][] newShape = new int[shape.length][shape[0].length];

		for (int i = 0; i < shape.length; i++) {
			for (int j = 0; j < shape.length; j++) {
				if (shape[i][j] != 0) {
					newShape[i][j] = shape[i][j];
				}

			}
		}

		return getReverseMatrix(newShape);

	}

	private int[][] getReverseMatrix(int[][] shape) {

		return shape;
	}

	public void speedUp() {
		currentTime = fastTime;

	}

	public void speedDown() {
		currentTime = normalTime;

	}

}
