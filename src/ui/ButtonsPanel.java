package ui;

import javax.swing.*;

import model.*;

import java.awt.event.*;
import java.awt.*;

public final class ButtonsPanel extends JPanel{
	private final SelectedMode selectedMode;
	private final Modes modes;
	private final Reset reset;
	private final Solve solve;
	private SetHeight setHeight;
	private SetWidth setWidth;
	
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
}
