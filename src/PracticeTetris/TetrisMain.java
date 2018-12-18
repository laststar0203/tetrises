package PracticeTetris;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.sun.glass.events.WheelEvent;
import com.sun.xml.internal.ws.api.pipe.Tube;

public class TetrisMain extends Canvas implements Runnable, KeyListener {

	public final static int WIDTH = 400, HEIGHT = 565;

	public static void main(String[] args) {

		JFrame frame = new JFrame("Tetris");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 이 플랫폼을 종료해도 메모리상에 남아 있지 않게함
		frame.setLocationRelativeTo(null); // 윈도우 창을 가운데 화면에 띄우게 함
		frame.setResizable(false); // 프레임의 크기를 사용자가 조정할수있을지 말지 결정하는 메소드
		frame.setLayout(null);

		KeyGetter.loadKeys();

		JMenuBar bar = new JMenuBar();
		bar.setBounds(0, 0, WIDTH - 10, 25);
		JMenu file = new JMenu("file");
		file.setBounds(0, 0, 45, 24);

		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Code for new Game
				System.out.println("Starting new Game....");
			}
		});

		JMenuItem highScore = new JMenuItem("HighScore");
		highScore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Code for new Game
				int highscore = 0;
				final JFrame alert = new JFrame("High Score"); // 내부클래스에서 JFrame에 접근하기 위해 상수값으로 변경?!
				alert.setSize(200, 150);
				alert.setLayout(null);
				alert.setLocationRelativeTo(null);

				JLabel score = new JLabel("The HighScore is: " + highscore);
				score.setBounds(0, 0, 200, 50);

				JButton okayBtn = new JButton("Okay");
				okayBtn.setBounds(50, 80, 100, 30);
				okayBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						alert.dispose();
					}
				});

				alert.add(score);
				alert.add(okayBtn);
				alert.setResizable(false);
				alert.setVisible(true);

				// 마이뇌피셜 : 하이스코어를 열때마다 이렇게 프레임을 새로 생성하기 보다는 미리 생성해놓고 점수 값만 업데이트 하고 setVisible 값을
				// 바꿔주는 게 더 낮지 않을까?
			}
		});

		JMenuItem option = new JMenuItem("Option");
		option.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Config.opneConfig(frame);
			}
		});

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Code for new Game
				System.out.println("Closing....");
				System.exit(0);
			}
		});

		TetrisMain main = new TetrisMain();
		main.setBounds(0, 25, WIDTH, HEIGHT - 25);
		frame.add(main);
		bar.add(file);

		file.add(newGame);
		file.add(highScore);
		file.add(option);
		file.add(exit);

		frame.add(bar);
		frame.setVisible(true);
		main.start();

	}

	public void start() {
		Thread t = new Thread(this);
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean running = true;

		while (running) {
			update();
			BufferStrategy buf = getBufferStrategy();
			if (buf == null) {
				createBufferStrategy(3);
				/*
				 * 이 화면을 갱신하다보면, 자주 있는 일이 화면이 깜박이는 현상(flickering)입니다 이런 점을 해결하기 위해서 만들어진 클래스
				 * 입니다. 더블 버퍼링을 구현하려면, 일단, 임시 버퍼에 모두 그리고 나서, 이 임시버퍼를 다시 본래화면표시용 버퍼에 그리는 방식으로
				 * 했었습니다. 이렇게 하면, 일단 paint()의 내부 로직이 더럽게 됩니다....swing에서는 이 문제를 아주 쉽게 해결할 수 있도록,
				 * JFrame에서 지원해줍니다 http://quadflask.tistory.com/290
				 */
				continue;
			}
			Graphics2D g = (Graphics2D) buf.getDrawGraphics();
			render(g);
			buf.show();
		}

	}

	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Calibri", Font.PLAIN, 20));

		// Font설명 http://blog.naver.com/PostView.nhn?blogId=bestheroz&logNo=103808914

		g.drawString("Tetris", 170, 50);

	}

	public void update() {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
