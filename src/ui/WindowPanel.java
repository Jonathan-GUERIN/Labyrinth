package ui;

import javax.swing.*;
import java.awt.*;

public class WindowPanel extends JPanel{
	private MazePanel mazePanel;
	private final ButtonsPanel buttonsPanel;
	
	/*
	 * contient le MazePanel qui contient les BoxPanel (cases graphiques du labyrinthe),
	 * et arrange la disposition des bouttons en bas
	 */
	public WindowPanel(MazeApp mazeApp) {
		super();
		
		setLayout(new BorderLayout());
		
		mazePanel = new MazePanel(mazeApp);
		add(mazePanel,BorderLayout.CENTER);
		buttonsPanel = new ButtonsPanel(mazeApp);
		add(buttonsPanel,BorderLayout.SOUTH);
	}
	
	/*
	 * permet de d'obtenir les cases du tableau graphique.
	 */
	public MazePanel getPanel() {
		return this.mazePanel;
	}
	public void setMazePanel(MazePanel mazePanel) {
		this.mazePanel = mazePanel;
	}
	
	public void notifyForUpdate() {
		this.mazePanel.notifyForUpdate();
		this.buttonsPanel.notifyForUpdate();
	}
	
	/*
	 * Enl�ve le MazePanel actuel qui contenait le labyrinthe pr�c�dent,
	 * et le remplace par un nouveau MazePanel qui a les bonnes dimensions demand�es
	 * par l'utilisateur. La construction du nouveau labyrinthe utilise les informations
	 * fournies dans le mod�le.
	 */
	public void resize(MazeApp mazeApp) {
		remove(this.mazePanel);
		mazePanel = new MazePanel(mazeApp);
		add(mazePanel,BorderLayout.CENTER);
		//repaint();
	}
}
