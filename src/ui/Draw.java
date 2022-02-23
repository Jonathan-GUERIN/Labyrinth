package ui;

import javax.swing.*;
import java.awt.event.*;

public class Draw extends JButton implements ActionListener{
	private final MazeApp mazeApp;
	
	public Draw(MazeApp mazeApp) {
		super("Draw");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.mazeApp.getModel().test();
	}
	
}
