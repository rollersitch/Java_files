package gui_elements;

import translations.Translator;

public class OrderBy extends MyCmbTxtField{
	private static final long serialVersionUID = 1L;

	public OrderBy(String label, Translator t){
		super(label, t);
		setEditable(false);
		//getComboBox().setPreferredSize(new Dimension(100, 30));
		
		getComboBox().addItem(t._("Book title"));
		getComboBox().addItem(t._("Author name"));
		getComboBox().addItem(t._("Type"));
		getComboBox().addItem(t._("Media Type"));
		getComboBox().addItem(t._("Position"));
		getComboBox().addItem(t._("Publisher"));
	}
}
