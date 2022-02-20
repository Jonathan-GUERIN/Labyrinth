package model;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.event.*;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;
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
	}
	
	public Color getSelectedColor() {
		return this.selectedColor;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public GraphInterface getMaze() {
		return this.maze;
	}
	
	public String getSelectedMode() {
		return this.selectedMode;
	}
	
	public void setSelectedMode(String selectedMode) {
		this.selectedMode = selectedMode;
		this.stateChanges();
	}
	
	public final void paintBoxes(Graphics g) {
		
		for(int i=0; i < height ; i++) {
			for(int j = 0; j < width ; j++) {
				//this.mazeApp.getMazePanel().getBoxesPanel()[i][j].paintNormal();
			}
		}
		this.stateChanges();
	}
	
	public void addObserver(ChangeListener listener) {
		listeners.add(listener) ;
	}
	
	public void stateChanges() {
        ChangeEvent evt = new ChangeEvent(this) ;
        for (ChangeListener listener : listeners) {
        	listener.stateChanged(evt);
        }
    }
	
	public final void setColorBox(int i , int j) {	
		
		this.stateChanges();
	}
}
