package ui;

import javax.swing.*;
import java.awt.*;

public final class ButtonsPanel extends JPanel{
	private final ColorChooser colorChooser;
	private final Reset reset;
	private final DesignWalls designWalls;
	private final Solve solve;
	
	public ButtonsPanel(MazeApp mazeApp) {
		super();
		
		setLayout(new GridLayout(1,4));
		add(colorChooser = new ColorChooser(mazeApp));
		add(reset = new Reset(mazeApp));
		add(designWalls = new DesignWalls(mazeApp));
		add(solve = new Solve(mazeApp));
	}
	
}
