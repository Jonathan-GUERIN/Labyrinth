package model;

import ui.*;
import java.awt.event.*;

public class BoxPanelMouseListener extends MouseAdapter{
	private MazeApp mazeApp;
	private final int i;
	private final int j;
	
	public BoxPanelMouseListener(MazeApp mazeApp, int i, int j) {
		super();
		this.mazeApp = mazeApp;
		this.i = i;
		this.j = j;
	}
	
	@Override
	public final void mouseClicked(MouseEvent e) {
		this.mazeApp.getModel().setBox(i,j);
		this.mazeApp.getModel().setSolved();
	}
	
	@Override
	public final void mousePressed(MouseEvent e) {
		//System.out.println("pressed");
		/*
		this.mazeApp.getModel().setBox(i,j);
		this.mazeApp.getModel().setSolved();
		*/
		this.mazeApp.getModel().setClicked(true);
	}
	@Override 
	public final void mouseReleased(MouseEvent e) {
		this.mazeApp.getModel().setClicked(false);
	}
	
	@Override
	public final void mouseEntered(MouseEvent e) {
		//System.out.println("entered");
		//this.mazeApp.getMazePanel().getBoxesPanel()[i][j].setIsHovered(true);
		//this.mazeApp.getMazePanel().getBoxesPanel()[i][j].revalidate();
		if(this.mazeApp.getModel().getClicked()) {
			this.mazeApp.getModel().setBox(i,j);
			this.mazeApp.getModel().setSolved();
		}else {
			
		}
		this.mazeApp.getModel().setBoxHovered(i,j, true);
	}
	@Override
	public final void mouseMoved(MouseEvent e) {
		if(this.mazeApp.getModel().getClicked()) {
			this.mazeApp.getModel().setBox(i,j);
			this.mazeApp.getModel().setSolved();
		}
	}
	
	@Override
	public final void mouseExited(MouseEvent e) {
		this.mazeApp.getModel().setBoxHovered(i,j, false);
	}
	
}
