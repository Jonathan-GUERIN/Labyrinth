package ui;

import javax.swing.*;

public class MazeApp extends JFrame{
	private final MazeMenuBar mazeMenuBar;
	private final WindowPanel windowPanel;
	
	public MazeApp() {
		super("Maze Application");  // Window title
		
		//Window menu bar creation
		mazeMenuBar = new MazeMenuBar(this);
		setJMenuBar(mazeMenuBar);
		
		//Window content creation
		windowPanel = new WindowPanel(this);
		setContentPane(windowPanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
		setVisible(true);
	}

}
