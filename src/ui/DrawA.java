package ui;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class DrawA extends JButton implements ActionListener{
	private final MazeApp mazeApp;
	
	public DrawA(MazeApp mazeApp) {
		super("A");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}
	
	/*
	 * Lorqu'on clique sur le boutton "A", le mode sélectionné est "A", celui qui permet 
	 * de dessiner des ABox
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.mazeApp.getModel().setSelectedMode("A");
		//this.mazeApp.set
	}
	
}
