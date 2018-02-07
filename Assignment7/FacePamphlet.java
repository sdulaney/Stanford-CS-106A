/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends ConsoleProgram 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		
		nameLabel = new JLabel("Name");
		add(nameLabel, NORTH);
		
		nameField = new JTextField(TEXT_FIELD_SIZE);
		add(nameField, NORTH);
		
		addButton = new JButton("Add");
		add(addButton, NORTH);
		
		deleteButton = new JButton("Delete");
		add(deleteButton, NORTH);
		
		lookupButton = new JButton("Lookup");
		add(lookupButton, NORTH);
		
		changeStatusField = new JTextField(TEXT_FIELD_SIZE);
		add(changeStatusField, WEST);
		changeStatusField.addActionListener(this);
		
		changeStatusButton = new JButton("Change Status");
		add(changeStatusButton, WEST);
		
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		changePictureField = new JTextField(TEXT_FIELD_SIZE);
		add(changePictureField, WEST);
		changePictureField.addActionListener(this);
		
		changePictureButton = new JButton("Change Picture");
		add(changePictureButton, WEST);
		
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		addFriendField = new JTextField(TEXT_FIELD_SIZE);
		add(addFriendField, WEST);
		addFriendField.addActionListener(this);
		
		addFriendButton = new JButton("Add Friend");
		add(addFriendButton, WEST);
		
		addActionListeners();
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == addButton) {
			println("Add: " + nameField.getText());
		} else if (source == deleteButton) {
			println("Delete: " + nameField.getText());
		} else if (source == lookupButton) {
			println("Lookup: " + nameField.getText());
		} else if (source == changeStatusButton || source == changeStatusField) {
			println("Change Status: " + changeStatusField.getText());
		} else if (source == changePictureButton || source == changePictureField) {
			println("Change Picture: " + changePictureField.getText());
		} else if (source == addFriendButton || source == addFriendField) {
			println("Add Friend: " + addFriendField.getText());
		}
	}

    /* Private instance variables */
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton lookupButton;
    private JTextField changeStatusField;
    private JButton changeStatusButton;
    private JTextField changePictureField;
    private JButton changePictureButton;
    private JTextField addFriendField;
    private JButton addFriendButton;

}
