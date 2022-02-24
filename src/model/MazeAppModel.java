package model;

import java.awt.*;
import java.io.File;

import javax.swing.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.*;

import dijkstra.Dijkstra;
import dijkstra.GraphInterface;
import dijkstra.PreviousInterface;
import dijkstra.VertexInterface;
import maze.ABox;
import maze.DBox;
import maze.EBox;
import maze.Maze;
import maze.WBox;
import ui.*;

public final class MazeAppModel {
	private GraphInterface maze;
	private MazeApp mazeApp;
	private int height = 5;
	private int width = 12;
	private String selectedMode = "W";
	private Color selectedColor;
	private boolean modified = false ;
	private VertexInterface arrival;
	private VertexInterface departure;
	private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>() ;
	private ArrayList<VertexInterface> path = new ArrayList<VertexInterface>();
	private boolean solved = false;
	
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
	
	public VertexInterface getArrival() {
		return this.arrival;
	}
	public void setArrival(VertexInterface arrival) {
		this.arrival = arrival;
		this.stateChanges();
	}
	public VertexInterface getDeparture() {
		return this.departure;
	}
	public void setDeparture(VertexInterface departure) {
		this.departure = departure;
		this.stateChanges();
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
	
	public void setSolved() {
		this.solved = false;
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
			//System.out.println("new height "+height);
			this.modified = true;
			this.changeBoxes();
			this.stateChanges();
		}
	}
	
	public GraphInterface getMaze() {
		return this.maze;
	}
	
	public String getSelectedMode() {
		return this.selectedMode;
	}
	
	public void setSelectedMode(String selectedMode) {
		this.selectedMode = selectedMode;
		modified = true;
		this.setSelectedColor();
	}
	
	public final void changeBoxes() {
		System.out.println("model.paintBoxes (paint) does nothing");
		
		System.out.println("new height "+this.height);
		System.out.println("new width "+this.width);
		
		VertexInterface[][] newBoxes = new VertexInterface[height][width];
		this.maze = new Maze(newBoxes,height,width);
		for(int i=0;i < height;i++) {
			for(int j = 0; j < width;j++) {
				System.out.println("i "+i+" j "+j);
				newBoxes[i][j] = new EBox(maze,i,j);
			}
		}
		
		this.changePanel();
	}
	
	public void changePanel() {
		//WindowPanel windowPanel = new WindowPanel(this.mazeApp);
		this.mazeApp.getMazePanel().changeBoxesPanel();
	}
	
	public final void paintBoxes(Graphics g) {
		
	}
	
	public final void chooseColorBox(int i , int j) {
		VertexInterface bboxes[][] = this.maze.getBoxes();
		VertexInterface box = bboxes[i][j];
		if(box instanceof EBox) {
			//this.mazeApp.getMazePanel().getBoxesPanel()[i][j].setColor(Color.GREEN);
			if((path.contains(this.maze.getBoxes()[i][j]))&&(solved == true)) {
				this.mazeApp.getMazePanel().getBoxesPanel()[i][j].setColor(Color.BLUE);
			}else {
				this.mazeApp.getMazePanel().getBoxesPanel()[i][j].setColor(Color.GREEN);
			}
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
        //System.out.println("evt "+evt);
        for (ChangeListener listener : listeners) {
        	System.out.println("listener "+listener);
        	listener.stateChanged(evt);
        }
    }

	public void saveToFile() {
		System.out.println("model.saveToFile() does something");
		// parent component of the dialog
		JFrame parentFrame = new JFrame();
		 
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		 
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    System.out.println("Save as file: " + fileToSave.getAbsolutePath()+".txt");
		    this.maze.saveToTextFile(fileToSave.getAbsolutePath()+".txt");
		}
		
	}
	
	public void loadToFile() {
		System.out.println("model.saveToFile() does something");
		// parent component of the dialog
		JFrame parentFrame = new JFrame();
		 
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to load");   
		 
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    System.out.println("Load file: " + fileToSave.getAbsolutePath()+".txt");
		    //this.maze.initFromTextFile(fileToSave.getAbsolutePath());
		    //this.maze.load(fileToSave.getAbsolutePath()+".txt");
		}
	}

	public boolean isModified() {
		return modified;
	}
	
	public void setBox(int i, int j) {
		this.maze.setBox(i, j, selectedMode);
		if(selectedMode=="A") {
			if(arrival!=null) {
				int x = arrival.getRef()[0];
				int y = arrival.getRef()[1];
				this.setBoxForce(x, y, "E");
				this.arrival = this.maze.getBoxes()[i][j];
			}else {
				this.arrival = this.maze.getBoxes()[i][j];
			}
		}
		if(selectedMode=="D") {
			if(departure!=null) {
				int x = departure.getRef()[0];
				int y = departure.getRef()[1];
				this.setBoxForce(x, y, "E");
				this.departure = this.maze.getBoxes()[i][j];
			}else {
				this.departure = this.maze.getBoxes()[i][j];
			}
		}
		modified = true;
		stateChanges();
	}
	public void setBoxForce(int i, int j, String label) {
		this.maze.setBox(i, j, label);
	}
	
	public void reset() {
		System.out.println("Full Reset");
		for(int i=0;i < height;i++) {
			for(int j = 0; j < width;j++) {
				this.setBoxForce(i, j, "E");
			}
		}
		
		stateChanges();
	}
	
	public void solve() {
		System.out.println("Solving");
		if((arrival==null)||(departure==null)) {
			System.out.println("Put arrival and departure");
		}else {
			PreviousInterface previous = Dijkstra.dijkstra(maze, departure);
			
			VertexInterface currentCase = arrival;
			path = new ArrayList<VertexInterface>();
			while(currentCase!=null) {
				currentCase.printPos();
				if((currentCase.getLabel2()!="A")&&(currentCase.getLabel2()!="D")){
					path.add(currentCase);
				}
				currentCase = previous.getDad(currentCase);
				
			}
			for(VertexInterface[] line : this.maze.getBoxes()) {
				for(VertexInterface box : line) {
					if(path.contains(box)) {
						System.out.print(". ");
					} else {
						System.out.print(box.getLabel2()+" ");
					}
				}
				System.out.println();
			}
			stateChanges();
			solved = true;
		}
	}
	
	public void test() {
		System.out.println("test");
		GraphInterface mazeLocal;
		height = 6;
		width = 4;
		VertexInterface[][] boxes2 = new VertexInterface[height][width];
		mazeLocal = new Maze(boxes2,height,width);
		for(int i=0;i < height;i++) {
			for(int j = 0; j < width;j++) {
				boxes2[i][j] = new EBox(mazeLocal,i,j);
			}
		}
		this.maze = mazeLocal;
		//this.setSelectedColor();
		stateChanges();
	}
}
