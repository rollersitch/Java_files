package gui_elements;

import java.awt.Dimension;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import translations.Translator;

public class MyCmbTxtField extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel label=new JLabel();
	private JComboBox field=new JComboBox();
	
	private Translator t;
	
	public MyCmbTxtField(String label, Translator t){
		this.t=t;
		this.label.setText(t._(label));
		this.label.setPreferredSize(new Dimension(100, 30));
		field.setEditable(true);
		field.setPreferredSize(new Dimension(300, 30));
		this.add(this.label);
		this.add(field);
	}
	
	public void setElements(Object[] elements){
		DefaultComboBoxModel model=(DefaultComboBoxModel) field.getModel();
		String[] el_strings = new String[elements.length];
		int i=0;
		for (Object el : elements){
			el_strings[i]=el.toString();
			i+=1;
		}
		Arrays.sort(el_strings, String.CASE_INSENSITIVE_ORDER);

		for (String el : el_strings){
			model.addElement(el);
		}
	}
	
	public void setSelectedIndex(int anIndex){
		field.setSelectedIndex(anIndex);
	}
	
	public void setSelectedItem(Object item){
		field.setSelectedItem(item);
	}
	
	public String getText(){
		return field.getSelectedItem().toString();
	}

	
	public void setLabel(String txt){
		label.setText(t._(txt));
	}
	
	public String getLabel(){
		return label.getText();
	}
	
	public void setEditable(Boolean e){
		field.setEditable(e);
	}
	
	public JComboBox getComboBox(){
		return this.field;
	}
}
