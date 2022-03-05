package ui;

import javax.swing.*;
import java.awt.*;

public class WindowPanel extends JPanel{
	private MazePanel mazePanel;
	private final ButtonsPanel buttonsPanel;
	
	public WindowPanel(MazeApp mazeApp) {
		super();
		
		setLayout(new BorderLayout());
		
		mazePanel = new MazePanel(mazeApp);
		add(mazePanel,BorderLayout.CENTER);
		buttonsPanel = new ButtonsPanel(mazeApp);
		add(buttonsPanel,BorderLayout.SOUTH);
	}
	
	public MazePanel getPanel() {
		return this.mazePanel;
	}
	public void setMazePanel(MazePanel mazePanel) {
		this.mazePanel = mazePanel;
	}
	
	public void notifyForUpdate() {
		this.mazePanel.notifyForUpdate();
		this.buttonsPanel.notifyForUpdate();
	}
	
	/*
	 * Completely remove the actual Panel for the maze and replace it by a new one.
	 * The construction of the new one will use the informations of the model. 
	 */
	public void resize(MazeApp mazeApp) {
		remove(this.mazePanel);
		mazePanel = new MazePanel(mazeApp);
		add(mazePanel,BorderLayout.CENTER);
		//repaint();
	}
}
