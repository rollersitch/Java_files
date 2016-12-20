package gui_elements;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.Document;

import translations.Translator;

public class MyTxtField extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel label=new JLabel();
	private JTextField field=new JTextField(30);
	
	private Translator t;
	
	public MyTxtField(String label, Translator t){
		this.t=t;
		this.label.setText(t._(label));
		this.label.setPreferredSize(new Dimension(100, 30));
		field.setPreferredSize(new Dimension(250, 30));
		this.add(this.label);
		this.add(field);
	}
	
	public void setText(String txt){
		field.setText(txt);
	}
	
	public String getText(){
		return field.getText();
	}

	public void setLabel(String txt){
		label.setText(t._(txt));
	}
	
	public String getLabel(){
		return label.getText();
	}
	
	public Document getDocument(){
		return field.getDocument();
	}
}
