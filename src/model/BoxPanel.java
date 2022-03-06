package model;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dijkstra.VertexInterface;
import maze.*;
import ui.MazeApp;

public class BoxPanel extends JPanel{
	private Color color;
	private VertexInterface box;
	private int i;
	private int j;
	private final MazeApp mazeApp;
	private BoxPanelMouseListener boxPanelMouseListener;
	private boolean isHovered = false;
	
	public BoxPanel(MazeApp mazeApp, VertexInterface box) {
		this.color = Color.WHITE;
		this.mazeApp = mazeApp;
		this.box = box;
		this.i = box.getRef()[0];
		this.j = box.getRef()[1];
		boxPanelMouseListener = new BoxPanelMouseListener(mazeApp,this.i,this.j);
		addMouseListener(boxPanelMouseListener);
		addMouseMotionListener(boxPanelMouseListener);
		
		
		setPreferredSize(new Dimension(50,50));
		setBackground(this.color);
		//addActionListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(isHovered) {
			int w = getWidth();
			int h = getHeight();
			
			//Get the color of the boxPanel from the model
			MazeAppModel mazeAppModel = this.mazeApp.getModel();
			mazeAppModel.chooseColorBox(this.i, this.j);
			g.setColor(java.awt.Color.black);
			
			//paint the boxPanel
			g.fillRect(0,0,w,h);
			g.setColor(java.awt.Color.black);
			g.drawRect(0, 0, w, h);
		}else {
			int w = getWidth();
			int h = getHeight();
			
			//Get the color of the boxPanel from the model
			MazeAppModel mazeAppModel = this.mazeApp.getModel();
			mazeAppModel.chooseColorBox(this.i, this.j);
			g.setColor(this.color);
			
			//paint the boxPanel
			g.fillRect(0,0,w,h);
			g.setColor(java.awt.Color.black);
			g.drawRect(0, 0, w, h);
		}
	}
	
	public void setIsHovered(boolean bool) {
		this.isHovered = bool;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public final void paint() {
		System.out.println("nothing for now");
	}
	/*
	//@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("click");
		this.mazeApp.getModel().setBox(i,j);
		this.mazeApp.getModel().setSolved();
	}
	*/
	
	public void notifyForUpdate() {
		//System.out.println("HERE");
		repaint();
	}
}
