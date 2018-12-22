package PracticeTetris;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Shape {

	private BufferedImage block;
	private int[][] coords;
	private Board board;
	private int deltaX = 0;
	private int x, y;
	private long time, lastTime;

	private int normalSpeed = 600, speedDown = 10, cuurentSpeed;

	public Shape(BufferedImage block, int[][] coords, Board board) {
		this.block = block;
		this.coords = coords;
		this.board = board;

		cuurentSpeed = normalSpeed;
		time = 0;
		lastTime = 0;

	}

	public void update() {
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (!(x + deltaX + coords[0].length > 10) && !(x + deltaX < 0))
			x += deltaX;

		if (!(y + 1 + coords.length > 20)) {

			if (time > cuurentSpeed) {
				y++;
				time = 0;
			}

		}

		deltaX = 0; // 계속 증가 방지/

	};

	public void render(Graphics g) {

		for (int row = 0; row < coords.length; row++) {
			for (int col = 0; col < coords[row].length; col++) {
				if (coords[row][col] != 0)
					g.drawImage(block, col * board.getBlockSize() + x * board.getBlockSize(),
							row * board.getBlockSize() + y * board.getBlockSize(), null);
				// g.drawImage(IMG , X, Y , ImageObserver < 여기에 대한 자세한 설명은 없네요);
			}
		}

	}

	public void rotate() {
		int[][] rotatedmatrix = null;
		
		rotatedmatrix = getTranspose(coords);
		
		rotatedmatrix = getReverseMatrix(rotatedmatrix);
		
		if(x + rotatedmatrix[0].length > 10 || y + rotatedmatrix[1].length > 20)
			return;
		
		coords = rotatedmatrix;
	}

	private int[][] getTranspose(int[][] matrix) {
		int[][] newMatrix = new int[matrix[0].length][matrix.length];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				newMatrix[j][i] = matrix[i][j];
			}
		}

		return newMatrix;
	}

	private int[][] getReverseMatrix(int[][] matrix) {
		int middle = matrix.length / 2;

		for (int i = 0; i < middle; i++) {
			int[] m = matrix[i];
			matrix[i] = matrix[matrix.length - i - 1];
			matrix[matrix.length - i - 1] = m;
		}

		return matrix;
	}

	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}

	public void speedDown() {
		cuurentSpeed = speedDown;
	}

	public void normalSpeed() {
		cuurentSpeed = normalSpeed;
	}
}
