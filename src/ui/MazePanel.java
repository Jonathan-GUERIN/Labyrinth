package ui;

import java.awt.*;
import javax.swing.*;

public class MazePanel extends JPanel{
	private final MazeApp mazeApp;
	
	public MazePanel(MazeApp mazeApp) {
		super();
		
		this.mazeApp = mazeApp;
		
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(256,256));
	}
	
}
