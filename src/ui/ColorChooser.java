package ui;

import javax.swing.*;

public final class ColorChooser extends JButton{
	private final MazeApp mazeApp;
	
	public ColorChooser(MazeApp mazeApp) {
		super("Choose color");
		this.mazeApp = mazeApp;
	}
	
}
