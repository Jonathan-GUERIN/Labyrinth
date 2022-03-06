package ui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class Reset extends JButton implements ActionListener{
	private final MazeApp mazeApp;
	
	public Reset(MazeApp mazeApp) {
		super("Reset");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}
	
	/*
	 * reset le labyrinthe
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.mazeApp.getModel().reset();
	}
	
}
