package ui;

import javax.swing.*;

public class LoadItem extends JMenuItem{
	private final MazeApp mazeApp;
	
	public LoadItem(MazeApp mazeApp) {
		super("Load");
		this.mazeApp = mazeApp;
	}
}
