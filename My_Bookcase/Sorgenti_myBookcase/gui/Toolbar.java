package gui;

import gui_elements.MyButton;
import gui_elements.OrderBy;
import gui_elements.SearchField;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentListener;

import translations.Translator;
import data.icons.MyImage;

public class Toolbar extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private Translator t=new Translator();
	private MyButton NewBook=new MyButton(t._("Add new book"), new MyImage("new.png"));
	private MyButton DelBook=new MyButton(t._("Remove book"), new MyImage("delete.png"));
	private MyButton EdtBook=new MyButton(t._("Edit book"), new MyImage("edit.png"));
	
	private SearchField SearchBook = new SearchField(t);
	
	private OrderBy OrderBy = new OrderBy("Order list by: ", t);
	
	public Toolbar(ActionListener AL, DocumentListener DL){
		
		
		JPanel container=new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		JPanel buttons=new JPanel();
		JPanel order=new JPanel();
		JPanel logoP=new JPanel();
		JLabel logo=new JLabel(new MyImage("logo_big.png"));
		logoP.add(logo);
		logoP.setPreferredSize(new Dimension(130, 130));
		
		NewBook.setActionCommand("new_book");
		NewBook.addActionListener(AL);
		EdtBook.setActionCommand("edt_book");
		EdtBook.addActionListener(AL);
		DelBook.setActionCommand("del_book");
		DelBook.addActionListener(AL);
		SearchBook.getDocument().addDocumentListener(DL);
		SearchBook.getComboBox().setActionCommand("search");
		SearchBook.getComboBox().addActionListener(AL);
		OrderBy.getComboBox().setActionCommand("new_order");
		OrderBy.getComboBox().addActionListener(AL);
		
		buttons.add(NewBook);
		buttons.add(EdtBook);
		buttons.add(DelBook);
		
		order.add(OrderBy);
		
		
		container.add(buttons);
		container.add(order);
		container.add(SearchBook);
		
		this.add(container);
		this.add(logoP);
	}
	
	public String[] getSearch(){
		String[] rtn=new String[2];
		rtn[0]=SearchBook.getText();
		rtn[1]=SearchBook.getWhereSearch();
		return rtn;
	}
	
	public String getOrderBy(){
		return t.getKey(OrderBy.getText());
	}
}
