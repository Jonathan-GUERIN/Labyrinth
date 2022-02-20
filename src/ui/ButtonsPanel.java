package ui;

import javax.swing.*;
import java.awt.*;

public final class ButtonsPanel extends JPanel{
	private final SelectedMode selectedMode;
	private final Modes modes;
	private final Draw draw;
	private final Erase erase;
	private final Reset reset;
	private final Solve solve;
	
	public ButtonsPanel(MazeApp mazeApp) {
		super();
		
		setLayout(new GridLayout(1,6));
		add(selectedMode = new SelectedMode(mazeApp));
		add(modes = new Modes(mazeApp));
		add(draw = new Draw(mazeApp));
		add(erase = new Erase(mazeApp));
		add(reset = new Reset(mazeApp));
		add(solve = new Solve(mazeApp));
	}
	
	public void notifyForUpdate() {
		this.selectedMode.notifyForUpdate();
	}
}
