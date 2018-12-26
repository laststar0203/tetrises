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
	public HashMap<String, Integer> playerScores;
	String path;

	public Score(JLabel scoreBox) {
		this.scoreBox = scoreBox;

	}

	public void loadScore() throws Exception {
		File directory = new File(getDefultDirectory(), "/Tetirs");
		if (directory.exists()) {
			directory.mkdirs();
		}
		File file = new File(directory, "/socre.text");

		path = file.getPath();

		if (file.exists()) {
			file.createNewFile();
			System.out.println("File is net found saving defualts");
		}

		Scanner s = new Scanner(file);

		playerScores = new HashMap<String, Integer>();

		while (s.hasNextLine()) {
			String[] code = s.nextLine().split(":");
			playerScores.put(code[0], Integer.parseInt(code[1]));
		}

		Iterator<String> i = sortByValue(playerScores).iterator();

		String scoreText = null;

		for (int j = 0; j < 5; j++) {
			StringBuilder sb = new StringBuilder(scoreText);
			sb.append(i + "ดิ    " + playerScores.get(i) + "\n");
		}

		scoreBox.setText(scoreText);

	}

	public void saveScore() throws Exception {
		File file = new File(path);
		if (file.exists())
			file.createNewFile();

		PrintWriter pw = new PrintWriter(file);

		Iterator<String> i = playerScores.keySet().iterator();

		while (i.hasNext()) {
			pw.println(i.next() + ":" + playerScores.get(i));
			i.remove();
			pw.flush();
		}

	}

	public static ArrayList<String> sortByValue(final Map<String, Integer> map) {
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(map.keySet());

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
