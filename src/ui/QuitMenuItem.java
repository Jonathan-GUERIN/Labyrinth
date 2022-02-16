package ui;

import javax.swing.*;

public final class QuitMenuItem extends JMenuItem{
	private final MazeApp mazeApp;
	
	public QuitMenuItem(MazeApp mazeApp) {
		super("Quit");
		this.mazeApp = mazeApp;
	}

}
