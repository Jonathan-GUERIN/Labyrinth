package model;

import ui.*;
import java.awt.event.*;

public class BoxPanelMouseListener extends MouseAdapter{
	private MazeApp mazeApp;
	private final int i;
	private final int j;
	
	/*
	 * Le MouseListener associé au BoxPanel de coordonnée i et j.
	 */
	public BoxPanelMouseListener(MazeApp mazeApp, int i, int j) {
		super();
		this.mazeApp = mazeApp;
		this.i = i;
		this.j = j;
	}
	
	/*
	 * Si on clique sur la BoxPanel, alors on change la couleurs en fonction du mode sélectionné.
	 * Le mode correspond au choix de WBox, EBox, ABox ou DBox.
	 */
	@Override
	public final void mouseClicked(MouseEvent e) {
		this.mazeApp.getModel().setBox(i,j);
		this.mazeApp.getModel().setSolved();
	}
	
	/*
	 * Si le clic gauche est enfoncé, alors on indique au modèle que l'utilisateur maintient son
	 * clique. L'attribut clicked du modele est alors mis sur true, et tant qu'il sera vrai,
	 * toutes les cases au dessus desquelles la sourie passera seront modifiées en fonction du
	 * mode sélectionné. On peut donc maintenir le clic et déplacer la souris pour modifier
	 * plusieurs cases du labyrinthe à la fois.
	 */
	@Override
	public final void mousePressed(MouseEvent e) {
		//System.out.println("pressed");
		/*
		this.mazeApp.getModel().setBox(i,j);
		this.mazeApp.getModel().setSolved();
		*/
		this.mazeApp.getModel().setClicked(true);
	}
	
	/*
	 * Désactive le mode de l'attribut clicked "false" et passer au dessus des cases ne change 
	 * donc plus le modèle.
	 */
	@Override 
	public final void mouseReleased(MouseEvent e) {
		this.mazeApp.getModel().setClicked(false);
	}
	
	/*
	 * Détecte si la souris est au dessus de la case, et demande au modèle si le clic gauche de
	 * la souris est enfoncé pour modifier ou non la case du modèle, puis raffraichier la version
	 * graphique.
	 */
	@Override
	public final void mouseEntered(MouseEvent e) {
		//System.out.println("entered");
		//this.mazeApp.getMazePanel().getBoxesPanel()[i][j].setIsHovered(true);
		//this.mazeApp.getMazePanel().getBoxesPanel()[i][j].revalidate();
		if(this.mazeApp.getModel().getClicked()) {
			this.mazeApp.getModel().setBox(i,j);
			this.mazeApp.getModel().setSolved();
		}else {
			
		}
		this.mazeApp.getModel().setBoxHovered(i,j, true);
	}
	
	/*
	 * Indique à la case BoxPanel que la souris n'est plus au dessus de celle-ci dans le modèle.
	 */
	@Override
	public final void mouseExited(MouseEvent e) {
		this.mazeApp.getModel().setBoxHovered(i,j, false);
	}
	
}
