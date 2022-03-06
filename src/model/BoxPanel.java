package model;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dijkstra.VertexInterface;
import maze.*;
import ui.MazeApp;

public class BoxPanel extends JPanel{
	private Color color;
	private VertexInterface box;
	private int i;
	private int j;
	private final MazeApp mazeApp;
	private BoxPanelMouseListener boxPanelMouseListener;
	
	/*
	 * constructeur du boxPanel, qui constitue une case graphique du labyrinthe,
	 * on peut cliquer dessus car  il contient un BoxPanelMouseListener, qui détectera les actions
	 * de la sourie pour actualiser le modèle et la fenetre graphique sera rafraichie une fois le
	 * modele modifié.
	 * Le BoxPanel est donc associé à une MBox du labyrinthe réel (modèle) et contient les coordonnées
	 * i et j de cette MBox
	 */
	public BoxPanel(MazeApp mazeApp, VertexInterface box) {
		this.color = Color.WHITE;
		this.mazeApp = mazeApp;
		this.box = box;
		this.i = box.getRef()[0];
		this.j = box.getRef()[1];
		boxPanelMouseListener = new BoxPanelMouseListener(mazeApp,this.i,this.j);
		addMouseListener(boxPanelMouseListener);
		addMouseMotionListener(boxPanelMouseListener);
		
		
		setPreferredSize(new Dimension(50,50));
		setBackground(this.color);
		//addActionListener(this);
	}
	
	/*
	 * A chaque fois que le modele est modifié, l'application sera notifiée, et ainsi toutes les 
	 * cases graphiques BoxPanel seront notifiées et se rafraichiront.
	 * On demande au modèle si la souris de l'utilisateur est actuellement au dessus de la BoxPanel
	 * pour peindre la case différement, elle aura de gros contours blancs pour indiquer que la 
	 * souris "is hovering" au dessus de cette BoxPanel
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.mazeApp.getModel().getMaze().getBoxes()[i][j].getBoxHovered()) {
			int w = getWidth();
			int h = getHeight();
			
			//Get the color of the boxPanel from the model
			MazeAppModel mazeAppModel = this.mazeApp.getModel();
			mazeAppModel.chooseColorBox(this.i, this.j);
			g.setColor(this.color);
			
			//paint the boxPanel
			g.fillRect(4,4,w-8,h-8);
			g.setColor(java.awt.Color.black);
			g.drawRect(0, 0, w, h);
		}else {
			int w = getWidth();
			int h = getHeight();
			
			//Get the color of the boxPanel from the model
			MazeAppModel mazeAppModel = this.mazeApp.getModel();
			mazeAppModel.chooseColorBox(this.i, this.j);
			g.setColor(this.color);
			
			//paint the boxPanel
			g.fillRect(0,0,w,h);
			g.setColor(java.awt.Color.black);
			g.drawRect(0, 0, w, h);
		}
	}
	
	/*
	 * La boxPanel contient la couleur avec laquelle elle se repeindra en demandant cette couleur 
	 * au modèle via la fontion paintComponent
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/*
	 * Est appelée dès que le modèle est modifié et que l'application en est notifiée.
	 */
	public final void paint() {
		System.out.println("nothing for now");
	}
	
	/*
	 * indique que le modèle a été modifiée lorqu'elle a été appelée plus haut dans la 
	 * hiérarchie de l'application.
	 */
	public void notifyForUpdate() {
		repaint();
	}
}
