package gui_list;

import javax.swing.JList;

import bookcase_engine.BookString;

public class BookList extends JList{
	private static final long serialVersionUID = 1L;
	BookListModel list_model;
	BookRow bookrow=new BookRow();
	
	public BookList(BookString[] list){
		list_model=new BookListModel(list);
		this.setModel(list_model);
		this.setCellRenderer(bookrow);
	}
	
	public void Search(String text, String where){
		if (text==null) text="";
		if (where==null) where="";
		
		list_model.search(text, where);
	}
	
	public void reorder(String orderby){
		if (orderby==null) orderby="Book title";
		list_model.reorder(orderby);
	}
}
