package gui_elements;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyButton extends JButton{
	private static final long serialVersionUID = 757011386183674120L;
	
	private Dimension d=new Dimension(200, 30);
	
	public MyButton(){
		super();
		this.setPreferredSize(d);
	}
	
	public MyButton(String text){
		super(text);
		this.setPreferredSize(d);
	}
	
	public MyButton(String text, ImageIcon image){
		super(text, image);
		this.setPreferredSize(d);
	}
}
