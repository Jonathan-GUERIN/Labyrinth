package model;

import java.awt.*;
import javax.swing.*;

import dijkstra.VertexInterface;
import ui.MazeApp;

public class BoxPanel extends JButton{
	private final Color color;
	private VertexInterface box;
	private final MazeApp mazeApp;
	
	
	public BoxPanel(MazeApp mazeApp, VertexInterface box) {
		this.color = Color.WHITE;
		this.mazeApp = mazeApp;
		this.box = box;
	}
	
}
