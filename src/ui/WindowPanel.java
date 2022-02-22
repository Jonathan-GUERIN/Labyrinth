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
}
