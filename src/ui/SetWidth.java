package ui;

import javax.swing.*;

import model.MazeAppModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class SetWidth extends JPanel{
	private JTextField textWidth;
	private JLabel labelWidth;
	
	public SetWidth(MazeApp mazeApp) {
		super();
		
		MazeAppModel mazeAppModel = mazeApp.getModel();
		
		setLayout(new GridLayout(2,1));
		add(labelWidth = new JLabel("Set Width"));
		
		textWidth = new JTextField("12");
		add(textWidth);
		textWidth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(textWidth.getText());
				mazeAppModel.prepareWidth(Integer.parseInt(textWidth.getText()));
			}
		});
	}
	
}
