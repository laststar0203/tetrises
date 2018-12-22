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

	private int[][] board = new int[boardWidth][boardHeight];

	private Shape[] shapes = new Shape[7];
	
	private Shape currentShape;
	
	private Timer timer;

	private final int FPS = 60;
	
	private final int delay = 1000/FPS;
	
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
			{ 1, 1, 1, 1 } }, this); // ISHPE

		shapes[1] = new Shape(blocks.getSubimage(blockSize, 0, blockSize, blockSize), new int[][] { 
			{ 1, 1, 0 },
			{ 0, 1, 1 }}, this); // ZSHPE
		
		shapes[2] = new Shape(blocks.getSubimage(blockSize*2, 0, blockSize, blockSize), new int[][] { 
			{ 0, 1, 1 },
			{ 1, 1, 0 }}, this); // S-SHAPE
		
		shapes[3] = new Shape(blocks.getSubimage(blockSize*3, 0, blockSize, blockSize), new int[][] { 
			{ 1, 1, 1 },
			{ 0, 0, 1 }}, this); // J-SHAP
		
		shapes[4] = new Shape(blocks.getSubimage(blockSize*4, 0, blockSize, blockSize), new int[][] { 
			{ 1, 1, 1 },
			{ 1, 0, 0 }}, this); // L-SHAP
		
		shapes[5] = new Shape(blocks.getSubimage(blockSize*5, 0, blockSize, blockSize), new int[][] { 
			{ 1, 1, 1 },
			{ 0, 1, 0 }}, this); // T-SHAP

		shapes[6] = new Shape(blocks.getSubimage(blockSize*6, 0, blockSize, blockSize), new int[][] { 
			{ 1, 1 },
			{ 1, 1 }}, this); // O-SHAP
		
		currentShape = shapes[4];
	}

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		currentShape.render(g);
		
		for (int i = 0; i < boardHeight; i++) {
			g.drawLine(0, i * blockSize, boardWidth * blockSize, i * blockSize);
			// drawLine(int x1 , int y1 , int x2, int y2)
			// 좌표(x1 , y1)에서 좌표(x2, y2)까지 직선을 그린다.

			for (int j = 0; j < boardWidth; j++) {
				g.drawLine(j * blockSize, 0, j * blockSize, blockSize * boardHeight);
			}
		}

	}
	
	public void update() {
		currentShape.update();
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
