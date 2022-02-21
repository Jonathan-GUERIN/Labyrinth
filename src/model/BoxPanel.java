package model;

import java.awt.*;
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
	
	private final static float[] dash = { 4.0f } ;
	   
	private final static BasicStroke usualStroke  ;
	private final static BasicStroke largeStroke ;
	private final static BasicStroke dashStroke  ;
	   
	static { // Static initialization of drawing modes
	   usualStroke  = new BasicStroke() ;
	   largeStroke  = new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER) ;
	   dashStroke   = new BasicStroke(1.0f, BasicStroke.CAP_ROUND,
	                                  BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f) ;	   
	}
	
	public BoxPanel(MazeApp mazeApp, VertexInterface box) {
		this.color = Color.WHITE;
		this.mazeApp = mazeApp;
		this.box = box;
		this.i = box.getRef()[0];
		this.j = box.getRef()[1];
		
		setPreferredSize(new Dimension(50,50));
		setBackground(this.color);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
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
		
		
	/*
	private final static float[] dash = { 4.0f } ;
	   
	private final static BasicStroke usualStroke  ;
	private final static BasicStroke largeStroke ;
	private final static BasicStroke dashStroke  ;
	   
	static { // Static initialization of drawing modes
	   usualStroke  = new BasicStroke() ;
	   largeStroke  = new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER) ;
	   dashStroke   = new BasicStroke(1.0f, BasicStroke.CAP_ROUND,
	                                  BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f) ;	   
	}
	   
	public final void paint(Graphics g, boolean selected, boolean current){
	   Graphics2D g2 = (Graphics2D)g ;
	   g2.setColor(color);
		   
	   if (current) {   
	      g2.setStroke(dashStroke) ;  // Dashed lines mode
	      g2.draw(this) ;
	      g2.setStroke(usualStroke) ; // Normal mode
	   } else if (selected) {
	      g2.setStroke(largeStroke) ; // Large lines mode
	      g2.draw(this) ;
	      g2.setStroke(usualStroke) ; // Normal mode
	   } else {
	      g2.draw(this) ;
	   }
	}
	*/
		
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public final void paint() {
	}
}
