package gui_elements;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

import data.icons.MyImage;

public class Error extends JFrame{
	private static final long serialVersionUID = 1L;

	public Error(String e){
		JLabel estring=new JLabel(e);
		estring.setIcon(new MyImage("error.png"));
		System.err.println(e);
		this.add (estring);
		this.setAlwaysOnTop(true);
		this.setTitle("Error");
		this.setMinimumSize(new Dimension(300, 200));
		this.pack();
		this.setVisible(true);
	}
}
