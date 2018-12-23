package JavaTetris;

import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;

public class GameSystem {

	private JLabel scoreBox;
	private int score;
	
	
	public GameSystem(JLabel scoreBox) {		
		this.scoreBox = scoreBox;
		
		
	
	}
	
	
	public void loadScore() throws Exception {
		File directory = new File(getDefultDirectory() , "/Tetirs");
		if(directory.exists()) {
			directory.mkdirs();
		}
		File file = new File(directory , "/socre.text");
		if(file.exists()) {
			file.createNewFile();
			System.out.println("File is net found saving defualts");
		}
		
	}
	
	private String getDefultDirectory() {
		String OS = System.getProperty("os.name");
		
		if(OS.contains("WIN"))
			return System.getenv("APPDATA");
		if(OS.contains("MAC"))
			return System.getProperty("user.home") + "Library/Application support";
		
		return System.getProperty("user.home")
	}
	
}
