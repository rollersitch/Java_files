package gui_list;

import java.util.Comparator;

import bookcase_engine.BookString;

public class BooksComparator implements Comparator<BookString>{
	
	private String orderby="book";
	
	public BooksComparator(String orderby){
		this.orderby=orderby;		
	}
	
	public int compare(BookString o1, BookString o2) {
		String s1=o1.getTitle();
		String s2=o2.getTitle();
		if (orderby.equals("Author name")){
			s1=o1.getAuthor();
			s2=o2.getAuthor();
		} else if (orderby.equals("Type")){
			s1=o1.getType();
			s2=o2.getType();
		} else if (orderby.equals("Media Type")){
			s1=o1.getMedia();
			s2=o2.getMedia();
		} else if (orderby.equals("Position")){
			s1=o1.getPosition();
			s2=o2.getPosition();
		} else if (orderby.equals("Publisher")){
			s1=o1.getPublisher();
			s2=o2.getPublisher();
		} 
		//Basecase book
		return s1.compareTo(s2);
	}

}
