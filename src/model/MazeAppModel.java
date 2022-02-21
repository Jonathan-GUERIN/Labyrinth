package model;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.event.*;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;
import maze.ABox;
import maze.DBox;
import maze.EBox;
import maze.Maze;
import maze.WBox;
import ui.MazeApp;

public final class MazeAppModel {
	private GraphInterface maze;
	private MazeApp mazeApp;
	private int height = 5;
	private int width = 12;
	private String selectedMode = "W";
	private Color selectedColor;
	private boolean modified = false ;
	private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>() ;
	
	public MazeAppModel(MazeApp mazeApp) {
		this.mazeApp = mazeApp;
		VertexInterface[][] boxes = new VertexInterface[height][width];
		this.maze = new Maze(boxes,height,width);
		for(int i=0;i < height;i++) {
			for(int j = 0; j < width;j++) {
				boxes[i][j] = new EBox(maze,i,j);
			}
		}
		this.setSelectedColor();
	}
	
	public void setSelectedColor() {
		if(selectedMode == "W") {
			this.selectedColor = Color.RED; 
		}else if(selectedMode == "E") {
			this.selectedColor = Color.GREEN;
		}else if(selectedMode == "D") {
			this.selectedColor = Color.YELLOW;
		}else if(selectedMode == "A") {
			this.selectedColor = Color.ORANGE;
		}
		this.stateChanges();
	}
	
	public Color getSelectedColor() {
		return this.selectedColor;
	}
	
	public int getWidth() {
		return this.width;
	}
	public void setWidth(int width) {
		if(this.width != width) {
			this.width = width;
			this.modified = true;
			this.stateChanges();
		}
	}
	
	public int getHeight() {
		return this.height;
	}
	public void setHeight(int height) {
		if(this.height != height) {
			this.height = height;
			this.modified = true;
			this.stateChanges();
		}
	}
	
	public GraphInterface getMaze() {
		return this.maze;
	}
	
	public void setBoxes(VertexInterface[][] boxes) {
		int height = boxes.length;
		int width = boxes[0].length;
		this.maze = new Maze(boxes,height,width);
		modified = true;
		stateChanges();
	}
	
	public void setBox(int i, int j, String label) {
		this.maze.setBox(i, j, label);
		modified = true;
		stateChanges();
	}
	
	public String getSelectedMode() {
		return this.selectedMode;
	}
	
	public void setSelectedMode(String selectedMode) {
		this.selectedMode = selectedMode;
		modified = true;
		this.stateChanges();
	}
	
	public final void paintBoxes(Graphics g) {
		System.out.println("model.paintBoxes (paint) does nothing");
		
		for(int i=0; i < height ; i++) {
			for(int j = 0; j < width ; j++) {
				this.mazeApp.getMazePanel().getBoxesPanel()[i][j].paint();
			}
		}
	}
	
	public final void chooseColorBox(int i , int j) {
		VertexInterface bboxes[][] = this.maze.getBoxes();
		VertexInterface box = bboxes[i][j];
		if(box instanceof EBox) {
			this.mazeApp.getMazePanel().getBoxesPanel()[i][j].setColor(Color.GREEN);
		}else if(box instanceof WBox) {
			this.mazeApp.getMazePanel().getBoxesPanel()[i][j].setColor(Color.RED);
		}else if(box instanceof DBox) {
			this.mazeApp.getMazePanel().getBoxesPanel()[i][j].setColor(Color.YELLOW);
		}else if(box instanceof ABox) {
			this.mazeApp.getMazePanel().getBoxesPanel()[i][j].setColor(Color.ORANGE);
		}
	}
	
	public void addObserver(ChangeListener listener) {
		this.listeners.add(listener) ;
	}
	
	public void stateChanges() {
        ChangeEvent evt = new ChangeEvent(this) ;
        for (ChangeListener listener : listeners) {
        	listener.stateChanged(evt);
        }
    }

	public void saveToFile() {
		System.out.println("model.saveToFile() does nothing");
	}

	public boolean isModified() {
		return modified;
	}
}
