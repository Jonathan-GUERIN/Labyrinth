package ui;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class DrawW extends JButton implements ActionListener{
	private final MazeApp mazeApp;
	
	public DrawW(MazeApp mazeApp) {
		super("W");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}
	
	/*
	 * Lorqu'on clique sur le boutton "W", le mode sélectionné est "W", celui qui permet 
	 * de dessiner des WBox
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.mazeApp.getModel().setSelectedMode("W");
		//this.mazeApp.set
	}
	
}
