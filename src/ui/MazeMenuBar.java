package ui;

import javax.swing.*;

public class MazeMenuBar extends JMenuBar{
	private FileMenu fileMenu;
	
	public MazeMenuBar(MazeApp mazeApp) {
		super();
		fileMenu = new FileMenu(mazeApp);
		add(fileMenu);
	}
	
}
