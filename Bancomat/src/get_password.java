/**
 * @get_pwd_string3.java
 *
 * @author Caffery
 * @url http://www.purpleindigo.co.cc/
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;

public class get_password extends JFrame {

	private JPasswordField pwdField;
	private JButton loginButton;
	
	public get_password() {
		// Building the component for the frame
		pwdField = new JPasswordField();
		loginButton = new JButton();
		
		loginButton.setText("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				check_password(); 
			}
		});
        
		setLayout(new BorderLayout());
		add(pwdField, BorderLayout.CENTER);
		add(loginButton, BorderLayout.EAST);
	}

	private void check_password() {
		boolean password_validated = true;
    	String password_in_string = "1234";
		char [] correct_password = password_in_string.toCharArray();
		
		// getPassword() returns password in char array
		char [] input_password = pwdField.getPassword();
		
		// Check if the user input password equals to the correct password
		if(input_password.length != correct_password.length){
			password_validated = false;
		}else{
			password_validated = Arrays.equals(input_password, correct_password);
		}
	
		// Zero out the password
		Arrays.fill(correct_password, '0');
		Arrays.fill(input_password, '0');
		
		// Check if the user input password equals with the correct password
		if(password_validated){
			JOptionPane.showMessageDialog(this, "Success! You typed the right password.");
			bancomat sportello=new bancomat();
			sportello.schermata_iniziale();
		}
		else
			JOptionPane.showMessageDialog(this, "Invalid password. Please try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
		
		// Clear the jPasswordField
		pwdField.setText("");
	}

	public static void main(String [] args) {
		// Create a new frame
    	get_password frame = new get_password();
		frame.setTitle("Password Validator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(300, 50));
		frame.setVisible(true);
		frame.pack();
	}

}