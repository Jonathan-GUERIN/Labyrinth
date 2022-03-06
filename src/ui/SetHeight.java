package ui;

import javax.swing.*;

import model.MazeAppModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class SetHeight extends JPanel{
	private JTextField textHeight;
	private JLabel labelHeight;
	
	/*
	 * JPanel contenant un label et un TextField qui permettent de transmettre au modèle
	 * la hauteur souhaitée par l'utilisateur.
	 */
	public SetHeight(MazeApp mazeApp) {
		super();
		
		MazeAppModel mazeAppModel = mazeApp.getModel();
		
		setLayout(new GridLayout(2,1));
		add(labelHeight = new JLabel("Set Height"));
		
		textHeight = new JTextField("5");
		add(textHeight);
		textHeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(textHeight.getText());
				mazeAppModel.prepareHeight(Integer.parseInt(textHeight.getText()));
			}
		});
	}
	
}
