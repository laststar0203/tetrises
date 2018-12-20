package PracticeTetris;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sun.org.apache.bcel.internal.classfile.Field;

public class Config {

	public static String rotate = "Up", left = "Left", right = "Right", down = "Down", pause = "P";
	private static ArrayList<Choice> choices;

	public static void opneConfig(JFrame frame) {
		choices = new ArrayList<Choice>();
		final JFrame option = new JFrame("Option");
		option.setSize(400, 300);
		option.setResizable(false);
		option.setLocationRelativeTo(frame);
		option.setVisible(true);

		Choice left = addChoice("Left", option, 30, 30);
		left.select(Config.left); // 인자로 들어온 목록이 가장 먼저 뜨게함 예)기본값 설정할때
		Choice right = addChoice("Right", option, 150, 30);
		right.select(Config.right);
		Choice rotate = addChoice("Rotate", option, 30, 80);
		rotate.select(Config.rotate);
		Choice down = addChoice("Down", option, 150, 80);
		down.select(Config.down);
		Choice pause = addChoice("Pause", option, 30, 130);
		down.select(Config.pause);

		JButton done = new JButton("Done");
		done.setBounds(150, 220, 100, 30);
		done.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				option.dispose();
				saveChoice();
			}
		});

		option.add(done);

		option.setLayout(null);

	}

	public static void saveChoice() {

		Choice left = choices.get(0);
		Choice right = choices.get(1);
		Choice rotate = choices.get(2);
		Choice down = choices.get(3);
		Choice pause = choices.get(4);

		Config.left = left.getSelectedItem();
		// 현재 슬롯된 목록을 Config.left에 저장함으로써 opconfig메소드에서 addChoice할때 로드하게 함
		Config.right = right.getSelectedItem();
		Config.rotate = rotate.getSelectedItem();
		Config.down = down.getSelectedItem();
		Config.pause = pause.getSelectedItem();
		
		try {
			saveConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Choice addChoice(String name, JFrame option, int x, int y) {
		JLabel label = new JLabel(name);
		label.setBounds(x, y - 20, 100, 20);
		Choice key = new Choice();
		for (String s : getKeyNames()) {
			key.add(s);
		}
		key.setBounds(x, y, 100, 20);
		option.add(key);
		option.add(label);
		choices.add(key);

		return key;
	}

	public static ArrayList<String> getKeyNames() {
		ArrayList<String> result = new ArrayList<String>();
		for (String s : KeyGetter.keyNames) {
			// 왜 (String s : KeyGetter.keys.keySet 에서 위 코드로 바꿨을가? 4-15:03 KeySet()이 정렬과 관련이
			// 있을까?
			result.add(s);
			if (s.equalsIgnoreCase("F24"))
				break;
		}
		return result;
	}

	public static void saveConfig() throws Exception {
		File config = new File(getDefaultDirectory(), "Tetris/config.text");
		if (!config.exists()) {
			// exists 메소드는 파일 존재 여부를 확인하는 메소드입니다.
			config.createNewFile();
		}
		PrintWriter pw = new PrintWriter(config);
		//PritnWriter는 문자 기준으로 출력을 할때 사용하는 클래스
		//PritnWriter는 print와 println이 있는데 이 둘의 차이점은 Println는 개행문자까지 덧붙여서 출력한다는 것
		//http://iamscjper.tistory.com/1866
		pw.println("right:" + right);
		pw.println("left:" + left);
		pw.println("rotate:" + rotate);
		pw.println("down:" + down);
		pw.println("pause:" + pause);
		pw.close();
		//출력되지 않는 데이터가 없으면 먼저 출력을 하고 스트림을 닫는다.
	}

	public static void loadConfig() throws Exception {
		File directory = new File(getDefaultDirectory() , "/Tetris");
		if(!directory.exists()) {
			directory.mkdirs();
			//해당 경로에 폴더를 만든다.
		}
		File config = new File(directory, "/config.text");
		if (!config.exists()) {
			// exists 메소드는 파일 존재 여부를 확인하는 메소드입니다.
			config.createNewFile();
		}
		// File 클래스 생성자 : 첫번째 인자라는 이름을 가진 폴더에 두번째 인자라는 이름을 가진 파일에 대한 File 객체를 생성한다.
		// File 클래스 설명 http://hyeonstorage.tistory.com/233
		if (!config.exists()) {
			// exists 메소드는 파일 존재 여부를 확인하는 메소드입니다.
			config.createNewFile();
			// 주어지 이름의 파일이 없으면 새로 생성한다.
			System.out.println("File not Found saving defaults");
			saveConfig();
			return;
		}
		Scanner s = new Scanner(config);
		// 인자에 File 클래스 변수가 들어가는 경우 지정된 파일을 사용하는 Scanner 클래스 객체를 생성한다.

		HashMap<String, String> values = new HashMap<String, String>();

		while (s.hasNextLine()) {
			//이 스캐너가 입력에 다음 행이 있는경우 true를 반환
			String[] entry = s.nextLine().split(":");
			String key = entry[0];
			String value = entry[1];
			values.put(key, value);
		}
		if (values.size() != 5) {
			System.out.println("Config is unnusable , saving defaults");
			saveConfig();
			return;
		}
		if(!values.containsKey("right") || !values.containsKey("left") || !values.containsKey("rotate")
				||!values.containsKey("down") || !values.containsKey("pause")) {
			System.out.println("Invalue names in config saving default");
			saveConfig();
			return;
			
		}
		String left = values.get("left");
		String right = values.get("right");
		String rotate = values.get("rotate");
		String down = values.get("down");
		String pause = values.get("pause");
		
		if(!(getKeyNames().contains(right) && getKeyNames().contains(left) &&getKeyNames().contains(rotate) &&getKeyNames().contains(down) &&
				getKeyNames().contains(pause))) {
			//ArrayList클래스의 contians메소드는 Arraylist중에 찾고자 하는 데이터가 있는지 확인하는 메소드입니다.
			System.out.println("Invailed key in config, saving default");
			return;
		}
		
		Config.right = right;
		Config.left = left;
		Config.rotate = rotate;
		Config.down = down;
		Config.pause = pause;
	}

	public static String getDefaultDirectory() {
		String OS = System.getProperty("os.name");
		// 자바를 사용할때 운영체제나 JVM의 의존적인 정보를 알아올때가 필요할때 사용하는 메소드입니다.
		http: // unabated.tistory.com/entry/Java%EC%97%90%EC%84%9C-SystemgetProperty
		if (OS.contains("WIN")) {
			// String의 contains 메소드는 특정문자열이 포함되어 있는지 확인하는 메소드입니다.
			return System.getenv("APPDATA");
			// 시스템에 저장된 환경변수값을 불러옴
		}
		if (OS.contains("MAC")) {
			return System.getProperty("user.home") + "Library/Application Support";
		}
		return System.getProperty("user.home");
	}
}
