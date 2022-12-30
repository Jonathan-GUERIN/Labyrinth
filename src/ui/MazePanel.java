package ui;

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;

import dijkstra.VertexInterface;
import maze.EBox;
import model.BoxPanel;
import model.BoxPanelMouseListener;
import model.MazeAppModel;

public class MazePanel extends JPanel{
	private final MazeApp mazeApp;
	private BoxPanel[][] boxesPanel;
	private int height;
	private int width;
	private MazeAppModel mazeAppModel;
	private GridLayout gridLayout;
	
	/*
	 * Le MazePanel est le panel qui contiendra toutes les BoxPanel, c'est à dire 
	 * toutes les cases graphiques du labyrinthe sur lesquelles on peut cliquer.
	 * La MazePanel contient donc le labyrinthe entier et stocker les cases 
	 * dans un tableau de tableau dont la taille est indiquée à l'initialisation par le modèle
	 */
	public MazePanel(MazeApp mazeApp) {
		super();
		
		this.mazeApp = mazeApp;
		
		this.mazeAppModel = mazeApp.getModel();
		this.height = this.mazeAppModel.getHeight();
		this.width = this.mazeAppModel.getWidth();
		this.gridLayout = new GridLayout(height,width);
		setLayout(gridLayout);
		
		this.boxesPanel = new BoxPanel[height][width];
		VertexInterface[][] boxes = this.mazeAppModel.getMaze().getBoxes();
		
		for(VertexInterface[] line : boxes) {
			for(VertexInterface box : line) {
				int i = box.getRef()[0];
				int j = box.getRef()[1];
				boxesPanel[i][j] = new BoxPanel(this.mazeApp,box);
				add(boxesPanel[i][j]);
			}
		}
		
		
		//addAncestorListener(this);
		//setBackground(Color.WHITE);
		//setPreferredSize(new Dimension(256,256));

	}
	
	public BoxPanel[][] getBoxesPanel(){
		return this.boxesPanel;
	}
	
	protected void paintComponent(Graphics g) {
		//paints the background
		super.paintComponent(g);
		
		//Ask the model to draw the segments
		//mazeApp.getModel().changeBoxes(g);
		this.mazeAppModel.getMaze().printMaze();
		
		
		//this.changeBoxesPanel();
		//mazeApp.getModel().paintBoxes(g);
	}
	
	public void notifyForUpdate() {
		this.repaint();
		for(BoxPanel[] row : boxesPanel) {
			for(BoxPanel boxPanel : row) {
				boxPanel.notifyForUpdate();
			}
		}
	}
}
