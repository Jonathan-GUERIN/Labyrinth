package ui;

import javax.swing.*;
import java.awt.event.*;
import model.*;

public final class LoadItem extends JMenuItem implements ActionListener{
	private final MazeApp mazeApp;
	
	public LoadItem(MazeApp mazeApp) {
		super("Load");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}
	
	/*
	 * Boutton pour charger un labyrinthe
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		MazeAppModel mazeAppModel = this.mazeApp.getModel();
		System.out.println("loading nothing");
		mazeAppModel.loadToFile() ;
	}

}
