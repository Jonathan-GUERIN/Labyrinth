package ui;

import javax.swing.*;

public class SaveItem extends JMenuItem{
	private final MazeApp mazeApp;
	
	public SaveItem(MazeApp mazeApp) {
		super("Save");
		this.mazeApp = mazeApp;
	}
}
