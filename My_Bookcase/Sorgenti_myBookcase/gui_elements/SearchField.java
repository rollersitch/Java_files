package gui_elements;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.text.Document;

import translations.Translator;

public class SearchField extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private MyTxtField searchText;
	private JComboBox searchWhere;
	private Translator t;
	
	public SearchField(Translator t){
		this.t=t;
		
		searchText=new MyTxtField("Search book", t);
		searchWhere= new JComboBox();
		searchWhere.addItem(t._("Book title"));
		searchWhere.addItem(t._("Author name"));
		searchWhere.addItem(t._("Type"));
		searchWhere.addItem(t._("Media Type"));
		searchWhere.addItem(t._("Position"));
		searchWhere.addItem(t._("Publisher"));
		
		this.add(searchText);
		this.add(searchWhere);
	}
	
	public JComboBox getComboBox(){
		return searchWhere;
	}
	
	public Document getDocument(){
		return searchText.getDocument();
	}
	
	public String getWhereSearch(){
		String tr=t.getKey(searchWhere.getModel().getSelectedItem().toString());
	
		if (tr.equals("Book title"))		return "book";
		if (tr.equals("Author name"))		return "author";
		if (tr.equals("Type"))				return "type";
		if (tr.equals("Media Type"))		return "media";
		if (tr.equals("Position"))			return "position";
		if (tr.equals("Publisher"))			return "publisher";
		
		return "book";
	}
	
	public String getText(){
		return searchText.getText();
	}
}
