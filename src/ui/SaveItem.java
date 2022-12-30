package ui;

import javax.swing.*;
import java.awt.event.*;
import model.*;

public final class SaveItem extends JMenuItem implements ActionListener{
	private final MazeApp mazeApp;
	
	public SaveItem(MazeApp mazeApp) {
		super("Save");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}
	
	/*
	 * Sauvegarde le labyrinthe actuel en appelant saveToFile() du modele
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		MazeAppModel mazeAppModel = this.mazeApp.getModel();
		System.out.println("save");
		mazeAppModel.saveToFile();
		//System.exit(0) ;
	}

}
