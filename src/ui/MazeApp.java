package ui;

import javax.swing.*;
import javax.swing.event.*;
import model.*;

import model.MazeAppModel;

public class MazeApp extends JFrame implements ChangeListener{
	private final MazeMenuBar mazeMenuBar;
	private final WindowPanel windowPanel;
	private MazeAppModel mazeAppModel;
	
	public MazeApp() {
		super("Maze Application");  // Window title
		
		//Model Creation (for fist, it is empty, only made of EBox)
		this.mazeAppModel = new MazeAppModel(this);
		
		//Window menu bar creation
		mazeMenuBar = new MazeMenuBar(this);
		setJMenuBar(mazeMenuBar);
		
		//Window content creation
		windowPanel = new WindowPanel(this);
		setContentPane(windowPanel);
		
		this.mazeAppModel.addObserver(this);
		
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
	
	public final MazePanel getMazePanel() {
		return this.windowPanel.getPanel();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		this.windowPanel.notifyForUpdate();
	}

}
