package ui;

import javax.swing.*;

import model.MazeAppModel;

public class MazeApp extends JFrame{
	private final MazeMenuBar mazeMenuBar;
	private final WindowPanel windowPanel;
	private MazeAppModel mazeAppModel;
	
	public MazeApp() {
		super("Maze Application");  // Window title
		
		//Model Creation (for fist, it is empty, only made of EBox)
		this.mazeAppModel = new MazeAppModel();
		
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
	
	public final MazeAppModel getModel() {
		return this.mazeAppModel;
	}
	
	public final void setModel(MazeAppModel mazeAppModel) {
		this.mazeAppModel = mazeAppModel;
	}

}
