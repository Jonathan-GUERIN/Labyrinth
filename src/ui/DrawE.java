package ui;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class DrawE extends JButton implements ActionListener{
	private final MazeApp mazeApp;
	
	public DrawE(MazeApp mazeApp) {
		super("E");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}
	
	/*
	 * Lorqu'on clique sur le boutton "E", le mode sélectionné est "E", celui qui permet 
	 * de dessiner des EBox
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.mazeApp.getModel().setSelectedMode("E");
		//this.mazeApp.set
	}
	
}
