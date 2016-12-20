package gui_list;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import bookcase_engine.BookString;

public class BookListModel implements ListModel{

	private ArrayList<ListDataListener> l = new ArrayList<ListDataListener>();
	private ArrayList<BookString> books=new ArrayList<BookString>();
	private ArrayList<BookString> all_books=new ArrayList<BookString>();
	private String last_txt_search="";
	private String last_where_search="book";
	private String orderby="book";
	
	
	public BookListModel(BookString[] books){
		for (BookString book : books){
			this.books.add(book);
			this.all_books.add(book);
		}
	}
	
	public void reorder(String orderby){
		this.orderby=orderby;
		System.out.println("Order by: " + orderby);
		order();
		doSearch();
		for (ListDataListener l : this.l){
			l.contentsChanged(new ListDataEvent(this, 0, 0, 1));
		}
	}
	
	public void order(){
		Collections.sort(all_books, new BooksComparator(orderby));
	}
	
	public void search(String text, String where){
		last_txt_search=text;
		last_where_search=where;
		doSearch();
	}
	
	private void doSearch(){
		String where=last_where_search;
		String text=last_txt_search;
		order();
		books.clear();
		for (BookString book : all_books){
			String t1=book.getTitle();
			if (where.equals("author")) 	t1=book.getAuthor();
			if (where.equals("type")) 		t1=book.getType();
			if (where.equals("media")) 		t1=book.getMedia();
			if (where.equals("position")) 	t1=book.getPosition();
			if (where.equals("publisher")) 	t1=book.getPublisher();
			
			
			if (simil(t1, text)){
				this.books.add(book);
			}
		}
		for (ListDataListener l : this.l){
			l.contentsChanged(new ListDataEvent(this, 0, 0, 1));
		}
	}
	
	private boolean simil(String t1, String t2){
		if (t2.equals("")) return true;
		if (false==t2.equals("") && t1.equals("")) return false;
		if (t1.equals(t2)) return true;
		if (t1.equalsIgnoreCase(t2)) return true;
		//Order string by lenght
		if (t1.length()<t2.length()){
			String t3=t1;
			t1=t2;
			t2=t3;
		}
		//String with the same case
		t1=t1.toLowerCase();
		t2=t2.toLowerCase();
		
		if (t1.indexOf(t2)!=-1) return true;
			
		
		return false;
	}
	
	public void addListDataListener(ListDataListener arg0) {
		l.add(arg0);
	}

	public Object getElementAt(int index) {
		if (index<getSize() && index>=0)	return books.get(index);
		return null;
	}

	public int getSize() {
		return books.size();
	}

	public void removeListDataListener(ListDataListener arg0) {
		l.remove(arg0);
	}
	
	public void removeBook(BookString book){
		int startsize=all_books.size();
		all_books.remove(book);
		if (startsize==all_books.size()){
			//search book manually and delete it
			int i=0;
			for (BookString b1 : all_books){
				System.out.println(b1.getCode());
				if (b1.getCode().equals(book.getCode())){
					all_books.remove(i);
					break;
				}
				i+=1;
			}
		}
		
		doSearch();
	}
	
	public void addBook(BookString book){
		all_books.add(book);

		doSearch();
	}
	
}
