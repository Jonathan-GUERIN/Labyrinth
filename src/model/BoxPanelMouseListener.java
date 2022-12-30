package model;

import ui.*;
import java.awt.event.*;

public class BoxPanelMouseListener extends MouseAdapter{
	private MazeApp mazeApp;
	private final int i;
	private final int j;
	
	/*
	 * Le MouseListener associ� au BoxPanel de coordonn�e i et j.
	 */
	public BoxPanelMouseListener(MazeApp mazeApp, int i, int j) {
		super();
		this.mazeApp = mazeApp;
		this.i = i;
		this.j = j;
	}
	
	/*
	 * Si on clique sur la BoxPanel, alors on change la couleurs en fonction du mode s�lectionn�.
	 * Le mode correspond au choix de WBox, EBox, ABox ou DBox.
	 */
	@Override
	public final void mouseClicked(MouseEvent e) {
		this.mazeApp.getModel().setBox(i,j);
		this.mazeApp.getModel().setSolved();
	}
	
	/*
	 * Si le clic gauche est enfonc�, alors on indique au mod�le que l'utilisateur maintient son
	 * clique. L'attribut clicked du modele est alors mis sur true, et tant qu'il sera vrai,
	 * toutes les cases au dessus desquelles la sourie passera seront modifi�es en fonction du
	 * mode s�lectionn�. On peut donc maintenir le clic et d�placer la souris pour modifier
	 * plusieurs cases du labyrinthe � la fois.
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
	 * D�sactive le mode de l'attribut clicked "false" et passer au dessus des cases ne change 
	 * donc plus le mod�le.
	 */
	@Override 
	public final void mouseReleased(MouseEvent e) {
		this.mazeApp.getModel().setClicked(false);
	}
	
	/*
	 * D�tecte si la souris est au dessus de la case, et demande au mod�le si le clic gauche de
	 * la souris est enfonc� pour modifier ou non la case du mod�le, puis raffraichier la version
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
	 * Indique � la case BoxPanel que la souris n'est plus au dessus de celle-ci dans le mod�le.
	 */
	@Override
	public final void mouseExited(MouseEvent e) {
		this.mazeApp.getModel().setBoxHovered(i,j, false);
	}
	
}
