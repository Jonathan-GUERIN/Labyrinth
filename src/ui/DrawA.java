package ui;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class DrawA extends JButton {
	private final MazeApp mazeApp;
	
	public DrawA(MazeApp mazeApp) {
		super("A");
		this.mazeApp = mazeApp;
	}
	
}
