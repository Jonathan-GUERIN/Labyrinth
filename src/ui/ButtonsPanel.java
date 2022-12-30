package ui;

import javax.swing.*;

import model.*;

import java.awt.*;

public final class ButtonsPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final SelectedMode selectedMode;
	private final Modes modes;
	private final Reset reset;
	private final Solve solve;
	private SetHeight setHeight;
	private SetWidth setWidth;
	
	/*
	 * affiche les différents boutons de l'interface.
	 */
	public ButtonsPanel(MazeApp mazeApp) {
		super();
		
		MazeAppModel mazeAppModel = mazeApp.getModel();
		
		setLayout(new GridLayout(1,4));
		add(selectedMode = new SelectedMode(mazeApp));
		add(modes = new Modes(mazeApp));
		add(reset = new Reset(mazeApp));
		add(solve = new Solve(mazeApp));
		
		
		
		setHeight = new SetHeight(mazeApp);
		add(setHeight);
		setWidth = new SetWidth(mazeApp);
		add(setWidth);
	}
	
	public void notifyForUpdate() {
		this.selectedMode.notifyForUpdate();
	}

	public Modes getModes() {
		return modes;
	}

	public Reset getReset() {
		return reset;
	}

	public Solve getSolve() {
		return solve;
	}
}
