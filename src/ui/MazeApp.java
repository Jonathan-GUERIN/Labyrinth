package ui;

import javax.swing.*;
import javax.swing.event.*;
import model.*;

import model.MazeAppModel;

public class MazeApp extends JFrame implements ChangeListener{
	private final MazeMenuBar mazeMenuBar;
	private WindowPanel windowPanel;
	private MazeAppModel mazeAppModel;
	
	/*
	 * On cr�e le mod�le (vide au d�but),
	 * le menu,
	 * la fenetre qui contiendra les options, le MazePanel qui contiendra la labyrinthe,
	 * et donc les BoxPanel (les cases du labyrinthes)
	 */
	public MazeApp() {
		super("Maze Application");  // Window title
		
		//Model Creation (for fist, it is empty, only made of EBox)
		this.mazeAppModel = new MazeAppModel(this);
		
		//Window menu bar creation
		mazeMenuBar = new MazeMenuBar(this);
		setJMenuBar(mazeMenuBar);
		
		//Window content creation
		windowPanel = new WindowPanel(this);
		setContentPane(windowPanel);
		
		this.mazeAppModel.addObserver(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
		setVisible(true);
	}
	
	/*
	 * permet d'acc�der au mod�le pour appeler des fonctions du mod�le pour modifier
	 * l'interface graphique.
	 */
	public final MazeAppModel getModel() {
		return this.mazeAppModel;
	}
	
	public void setWindowPanel(WindowPanel windowPanel) {
		this.windowPanel = windowPanel;
		setContentPane(windowPanel);
	}
	
	public final void setModel(MazeAppModel mazeAppModel) {
		this.mazeAppModel = mazeAppModel;
	}
	
	/*
	 * doit retourner le maze panel qui contient toutes les cases graphiques du labyrinthes
	 * cela permettra de modifier les cases du tableau
	 */
	public final MazePanel getMazePanel() {
		return this.windowPanel.getPanel();
	}
	
	public final void setMazePanel(MazePanel mazePanel) {
		this.windowPanel.setMazePanel(mazePanel);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		//System.out.println("here");
		this.windowPanel.notifyForUpdate();
	}
	
	public void resize() {
		this.windowPanel.resize(this);
		this.revalidate();
	}

}
