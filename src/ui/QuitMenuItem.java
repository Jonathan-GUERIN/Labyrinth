package ui;

import javax.swing.*;
import java.awt.event.*;
import model.*;

public final class QuitMenuItem extends JMenuItem implements ActionListener{
	private final MazeApp mazeApp;
	
	public QuitMenuItem(MazeApp mazeApp) {
		super("Quit");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MazeAppModel mazeAppModel = this.mazeApp.getModel();
		System.out.println("quitting");
		
		if (mazeAppModel.isModified()) {
	         int response = JOptionPane.showInternalOptionDialog(this,
	                                                             "Maze not saved. Save it ?",
	                                                             "Quit application",
	                                                             JOptionPane.YES_NO_CANCEL_OPTION,
	                                                             JOptionPane.WARNING_MESSAGE,
	                                                             null,null,null) ;
			   switch (response) {
			   case JOptionPane.CANCEL_OPTION:
				   return ;
			   case JOptionPane.OK_OPTION:
				   mazeAppModel.saveToFile() ;
				   break ;
			   case JOptionPane.NO_OPTION:
				   break ;
			   }
		   }
		   System.exit(0) ;
		
	}

}
