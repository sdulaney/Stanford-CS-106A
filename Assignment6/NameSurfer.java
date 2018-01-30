/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends ConsoleProgram implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	    // You fill this in, along with any helper methods //
		nameLabel = new JLabel("Name: ");
		add(nameLabel, SOUTH);
		
		nameField = new JTextField(20);
		add(nameField, SOUTH);
		nameField.addActionListener(this);
		
		graphButton = new JButton("Graph");
		add(graphButton, SOUTH);
		
		clearButton = new JButton("Clear");
		add(clearButton, SOUTH);
		
		addActionListeners();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		Object source = e.getSource();
		if (source == graphButton) {
			println("Graph: " + nameField.getText());
		} else if (source == nameField) {
			println("Graph: " + nameField.getText());
		} else if (source == clearButton) {
			println("Clear");
		}
	}
	
	/* Private instance variables */
	private JLabel nameLabel;
	private JTextField nameField;
	private JButton graphButton;
	private JButton clearButton;
}
