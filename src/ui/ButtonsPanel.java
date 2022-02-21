package ui;

import javax.swing.*;
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
	
	public ButtonsPanel(MazeApp mazeApp) {
		super();
		
		setLayout(new GridLayout(1,7));
		add(selectedMode = new SelectedMode(mazeApp));
		add(modes = new Modes(mazeApp));
		add(draw = new Draw(mazeApp));
		add(erase = new Erase(mazeApp));
		add(reset = new Reset(mazeApp));
		add(solve = new Solve(mazeApp));
		textHeight = new JTextField("Height");
		add(textHeight);
		textHeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(textHeight.getText());
				mazeApp.getModel().setHeight(Integer.parseInt(textHeight.getText()));
			}
		});
		textWidth = new JTextField("Width");
		add(textWidth);
		textWidth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(textWidth.getText());
				mazeApp.getModel().setHeight(Integer.parseInt(textWidth.getText()));
			}
		});
	}
	
	public void notifyForUpdate() {
		this.selectedMode.notifyForUpdate();
	}
}
