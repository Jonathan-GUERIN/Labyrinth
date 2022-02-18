package ui;

import java.awt.*;
import javax.swing.*;

import dijkstra.VertexInterface;
import maze.EBox;
import model.BoxPanel;
import model.MazeAppModel;

public class MazePanel extends JPanel{
	private final MazeApp mazeApp;
	private BoxPanel[][] boxesPanel;
	private int height;
	private int width;
	private MazeAppModel mazeAppModel;
	
	public MazePanel(MazeApp mazeApp) {
		super();
		
		this.mazeApp = mazeApp;
		
		this.mazeAppModel = mazeApp.getModel();
		this.height = this.mazeAppModel.getHeight();
		this.width = this.mazeAppModel.getWidth();
		setLayout(new GridLayout(height,width));
		
		this.boxesPanel = new BoxPanel[height][width];
		VertexInterface[][] boxes = this.mazeAppModel.getMaze().getBoxes();
		
		for(VertexInterface[] line : boxes) {
			for(VertexInterface box : line) {
				int i = box.getRef()[0];
				int j = box.getRef()[1];
				//box.printPos();
				boxesPanel[i][j] = new BoxPanel(this.mazeApp,box);
				System.out.print("i = "+i);
				System.out.println(" j = "+j);
				box.printPos();
				add(boxesPanel[i][j]);
			}
		}
		
		
		/*
		for(int i=0;i < height;i++) {
			for(int j = 0; j < width;j++) {
				boxes[i][j].printPos();
				boxesPanel[i][j] = new BoxPanel(this.mazeApp,boxes[i][j]);
				boxes[i][j].printPos();
				add(boxesPanel[i][j]);
			}
		}
		*/
		
		
		
		//setBackground(Color.WHITE);
		//setPreferredSize(new Dimension(256,256));
	}
	
}
