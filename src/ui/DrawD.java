package ui;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class DrawD extends JButton implements ActionListener{
	private final MazeApp mazeApp;
	
	public DrawD(MazeApp mazeApp) {
		super("D");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}
	
	/*
	 * Lorqu'on clique sur le boutton "D", le mode sélectionné est "D", celui qui permet 
	 * de dessiner des DBox
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.mazeApp.getModel().setSelectedMode("D");
		//this.mazeApp.set
	}
	
}
