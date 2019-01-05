package PracticeTetris;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements KeyListener {

	private BufferedImage blocks;
	// https://m.blog.naver.com/PostView.nhn?blogId=seektruthyb&logNo=150114863254&proxyReferer=https%3A%2F%2Fwww.google.com%2F

	private final int blockSize = 30;

	private final int boardWidth = 10, boardHeight = 20;

	private int[][] board = new int[boardHeight][boardWidth];

	private Shape[] shapes = new Shape[7];
	
	private Shape currentShape;
	
	private Timer timer;

	private final int FPS = 60;
	
	private final int delay = 1000/FPS;
	
	private boolean gameOver = false;
	
	public Board() {
		try {
			blocks = ImageIO.read(Board.class.getResource("/image.png"));
			// 이미지 파일을 일거와서 BufferedImage에 넣음
			// 관련 링크
			// https://moogii.tistory.com/entry/JAVA-ImageIO-%ED%81%B4%EB%9E%98%EC%8A%A4%EB%A1%9C-URL%EC%9D%84-%ED%86%B5%ED%95%9C-%EC%9D%B4%EB%AF%B8%EC%A7%80-%ED%8C%8C%EC%9D%BC-%EC%A0%80%EC%9E%A5
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		timer = new Timer(delay , new ActionListener() {
			//java.swing.timer 주기적으로(delay만큼) 이벤트를 발생시킵니다. 
			//유니티에 Update() 함수와 비슷함
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				update();
				repaint();
			}
		});
		
		timer.start();

		shapes[0] = new Shape(blocks.getSubimage(0, 0, blockSize, blockSize), new int[][] { 
			{ 1, 1, 1, 1 } }, this , 1); // ISHPE

		shapes[1] = new Shape(blocks.getSubimage(blockSize, 0, blockSize, blockSize), new int[][] { 
			{ 1, 1, 0 },
			{ 0, 1, 1 }}, this , 2); // ZSHPE
		
		shapes[2] = new Shape(blocks.getSubimage(blockSize*2, 0, blockSize, blockSize), new int[][] { 
			{ 0, 1, 1 },
			{ 1, 1, 0 }}, this , 3); // S-SHAPE
		
		shapes[3] = new Shape(blocks.getSubimage(blockSize*3, 0, blockSize, blockSize), new int[][] { 
			{ 1, 1, 1 },
			{ 0, 0, 1 }}, this , 4); // J-SHAP
		
		shapes[4] = new Shape(blocks.getSubimage(blockSize*4, 0, blockSize, blockSize), new int[][] { 
			{ 1, 1, 1 },
			{ 1, 0, 0 }}, this , 5); // L-SHAP
		
		shapes[5] = new Shape(blocks.getSubimage(blockSize*5, 0, blockSize, blockSize), new int[][] { 
			{ 1, 1, 1 },
			{ 0, 1, 0 }}, this , 6); // T-SHAP

		shapes[6] = new Shape(blocks.getSubimage(blockSize*6, 0, blockSize, blockSize), new int[][] { 
			{ 1, 1 },
			{ 1, 1 }}, this , 7); // O-SHAP
		
		setNextShape();
	}
	
	public int[][] getBoard(){
		return board;
	}

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		currentShape.render(g);
		
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if(board[row][col] != 0)
				g.drawImage(blocks.getSubimage((board[row][col]-1) * blockSize, 0, blockSize, blockSize) , col*blockSize, row*blockSize , null);
				//생성된 블럭에서 이미지를 자름 shape에 update 메소드 참고 -1한 이유는 왼쪽에서 부터 잘라야 하기 때문에
			}
		}
		//각 블럭에 shape클래스에서 변경한 값에 따라 출력을 하게 함
		
		
		for (int i = 0; i < boardHeight; i++) {
			g.drawLine(0, i * blockSize, boardWidth * blockSize, i * blockSize);
			// drawLine(int x1 , int y1 , int x2, int y2)
			// 좌표(x1 , y1)에서 좌표(x2, y2)까지 직선을 그린다.

			for (int j = 0; j < boardWidth; j++) {
				g.drawLine(j * blockSize, 0, j * blockSize, blockSize * boardHeight);
			}
		}

	}
	
	public void setNextShape() {
		
		int index = (int)(Math.random() * shapes.length);
		
		Shape newShape = new Shape(shapes[index].getBlock(), shapes[index].getCoords(), this , shapes[index].getColor());
		//새로운 블럭 생성
		
		currentShape = newShape;
		
		for (int row = 0; row < currentShape.getCoords().length; row++) {
			for (int col = 0; col < currentShape.getCoords()[row].length; col++) {
				if(currentShape.getCoords()[row][col] != 0) { //블럭 좌표를 불러와
					
					if(board[row][col + 3] != 0) //시작 지점에 겹친다면
						gameOver = true; //게임 오버
				}
			}
		}
	}
	
	public void update() {
		currentShape.update();
		
		if(gameOver)
			timer.stop();
	}
	
	public int getBlockSize() {
		return blockSize;
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			currentShape.setDeltaX(-1);
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			currentShape.setDeltaX(1);
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			currentShape.speedDown();
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			currentShape.rotate();
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			currentShape.normalSpeed();
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}

}
