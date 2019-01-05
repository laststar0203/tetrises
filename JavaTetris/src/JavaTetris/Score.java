package JavaTetris;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JLabel;

import com.sun.javafx.geom.transform.GeneralTransform3D;

import sun.security.krb5.internal.ccache.CCacheOutputStream;

public class Score {

	private JLabel scoreBox;
	private int score;
	public HashMap<String, Integer> playerScores = new HashMap<String, Integer>();
	String path;

	public Score(JLabel scoreBox) {
		this.scoreBox = scoreBox;
	

	}

	public String loadScore() throws Exception {
		File file = new File(path);
		if (file.exists())
			file.createNewFile();
		
		
		path = file.getPath();

		
		Scanner s = new Scanner(file);

		playerScores = new HashMap<String, Integer>();
		try {
		while (s.hasNextLine()) {
			String[] code = s.nextLine().split(":");
			playerScores.put(code[0], Integer.parseInt(code[1]));
		}
		}catch (NumberFormatException e) {
			for (int i = 0; i < 5; i++) {
				playerScores.put("AAA" ,0);
			}
	
			
		}

		Iterator<Integer> i = sortByValue(playerScores).iterator();

		
		StringBuilder sb = new StringBuilder();
		
		for (int j = 0; j < 5; j++) {
			//sb.append(playerScores.getk "님    " + i.next() + "\n");
		}
		
		
		
		return "당신의 점수는!! :"+getScore()+"\n"+sb.toString();
		
		
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score += score;
		scoreBox.setText(this.score + "점");
	}

	public void saveScore(String name , int score) throws Exception {
		
		File directory = new File(getDefultDirectory(), "/Tetirs");
		if (!directory.exists()) {
			directory.mkdirs();
		}
		File file = new File(directory, "/socre.text");
		if (!file.exists()) {
			file.createNewFile();
			System.out.println("File is net found saving defualts");
		}


		path = file.getPath();
		
		playerScores.put(name ,  score);

		PrintWriter pw = new PrintWriter(file);

		Iterator<String> i = playerScores.keySet().iterator();

		while (i.hasNext()) {
			pw.println(i.next() + ":" + playerScores.get(i));
			i.remove();
			pw.flush();
		}

	}

	public static ArrayList<Integer> sortByValue(final Map<String, Integer> map) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.addAll(map.values());

		
		Collections.sort(list, (o1, o2) -> {
			Object v1 = map.get(o1);
			Object v2 = map.get(o2);

			return ((Comparable) v2).compareTo(v1);
		});

		return list;

	}

	private String getDefultDirectory() {
		String OS = System.getProperty("os.name");

		if (OS.contains("WIN"))
			return System.getenv("APPDATA");
		if (OS.contains("MAC"))
			return System.getProperty("user.home") + "Library/Application support";

		return System.getProperty("user.home");
	}

}
