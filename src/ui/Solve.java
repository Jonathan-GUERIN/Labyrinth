package ui;

import javax.swing.*;
import java.awt.event.*;

public class Solve extends JButton implements ActionListener{
	private final MazeApp mazeApp;
	
	public Solve(MazeApp mazeApp) {
		super("Solve");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}
	
	/*
	 * appele la fonction de résolution du modele
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.mazeApp.getModel().solve();
	}
	
}
