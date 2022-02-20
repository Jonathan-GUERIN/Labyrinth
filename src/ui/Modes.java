package ui;

import javax.swing.*;
import java.awt.*;

public final class Modes extends JPanel{
	private final DrawW drawW;
	private final DrawD drawD;
	private final DrawA drawA;

	
	public Modes(MazeApp mazeApp) {
		super();
		
		setLayout(new GridLayout(3,1));
		add(drawW = new DrawW(mazeApp));
		add(drawD = new DrawD(mazeApp));
		add(drawA = new DrawA(mazeApp));
	}
	
}
