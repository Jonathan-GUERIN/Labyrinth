package ui;

import javax.swing.*;

import model.*;

import java.awt.event.*;
import java.awt.*;

public final class ButtonsPanel extends JPanel{
	private final SelectedMode selectedMode;
	private final Modes modes;
	private final Draw draw;
	private final Erase erase;
	private final Reset reset;
	private final Solve solve;
	private JTextField textHeight;
	private JTextField textWidth;
	private SetHeight setHeight;
	private SetWidth setWidth;
	
	public ButtonsPanel(MazeApp mazeApp) {
		super();
		
		MazeAppModel mazeAppModel = mazeApp.getModel();
		
		setLayout(new GridLayout(1,8));
		add(selectedMode = new SelectedMode(mazeApp));
		add(modes = new Modes(mazeApp));
		add(draw = new Draw(mazeApp));
		add(erase = new Erase(mazeApp));
		add(reset = new Reset(mazeApp));
		add(solve = new Solve(mazeApp));
		
		textHeight = new JTextField("set x");
		add(textHeight);
		textHeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(textHeight.getText());
				mazeAppModel.prepareHeight(Integer.parseInt(textHeight.getText()));
			}
		});
		textWidth = new JTextField("set y");
		add(textWidth);
		textWidth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(textWidth.getText());
				mazeAppModel.prepareWidth(Integer.parseInt(textWidth.getText()));
			}
		});
		
		setHeight = new SetHeight(mazeApp);
		add(setHeight);
		setWidth = new SetWidth(mazeApp);
		add(setWidth);
	}
	
	public void notifyForUpdate() {
		this.selectedMode.notifyForUpdate();
	}
}
