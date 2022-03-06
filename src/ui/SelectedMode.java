package ui;

import javax.swing.*;
import java.awt.*;

public class SelectedMode extends JPanel{
	private final MazeApp mazeApp;
	private String selectedMode;
	
	public SelectedMode(MazeApp mazeApp) {
		super();
		this.mazeApp = mazeApp;
		this.selectedMode = this.mazeApp.getModel().getSelectedMode();
	}
	
	protected final void paintComponent(Graphics g) {
		//paints the background
		super.paintComponent(g);
		
		// Get widget dimension
		int w = getWidth() ;
		int h = getHeight() ;
		
		//indicates which label is selected
		this.selectedMode = this.mazeApp.getModel().getSelectedMode();
		
		g.drawString(this.selectedMode,0,10);
		g.setColor(this.mazeApp.getModel().getSelectedColor());
		g.fillRoundRect(10, 10, w, h, 15,15);
		
		
	}
	
	public void notifyForUpdate() {
		//System.out.println("HERE");
		repaint();
	}
	
}
