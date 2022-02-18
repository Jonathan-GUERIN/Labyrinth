package model;

import java.awt.*;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;
import maze.EBox;
import maze.Maze;

public final class MazeAppModel {
	private GraphInterface maze;
	private int height = 5;
	private int width = 12;
	
	public MazeAppModel() {
		VertexInterface[][] boxes = new VertexInterface[height][width];
		this.maze = new Maze(boxes,height,width);
		for(int i=0;i < height;i++) {
			for(int j = 0; j < width;j++) {
				boxes[i][j] = new EBox(maze,i,j);
			}
		}
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
	
}
