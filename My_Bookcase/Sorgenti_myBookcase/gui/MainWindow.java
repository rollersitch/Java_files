package gui;

import gui_list.BookList;
import gui_list.BookListModel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import listeners.BookWindowListener;

import org.jdom.JDOMException;

import translations.Translator;
import bookcase_engine.BookString;
import bookcase_engine.Bookcase;
import data.icons.MyImage;
import excepions.AuthorNotFoundException;
import excepions.BookNotFoundException;
import excepions.MediaNotFoundException;
import excepions.PositionNotFoundException;
import excepions.PublisherNotFoundException;
import excepions.TypeNotFoundException;

public class MainWindow extends JFrame implements ActionListener, BookWindowListener, ComponentListener, DocumentListener{
	private static final long serialVersionUID = 1L;
	
	private Bookcase mybookcase;
	
	private Toolbar toolbar;
	private BookList list;
	private JScrollPane listScroller;
	
	private Translator t;
	
	public MainWindow(){
		super();
		JPanel container =new JPanel();
		t=new Translator();
		mybookcase=new Bookcase();
		
		mybookcase.addMedia(t._("Book"), "book.png");
		mybookcase.addMedia(t._("Comic"), "comic.png");
		mybookcase.addMedia(t._("Dvd"), "dvd.png");
		mybookcase.addMedia(t._("CdAudio"), "cda.png");
		
		try {
			mybookcase.loadBookcase();
		} catch (FileNotFoundException e) {
			System.out.println("First launch or no book in library (file library.xml doesn't have been found)");
		} catch (JDOMException e) {
			new Error(e.getMessage());
		} catch (IOException e) {
			new Error(e.getMessage());
		}
		
		toolbar=new Toolbar(this, this);
		
		list = new BookList(mybookcase.toArray());
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setVisibleRowCount(-1);
		list.reorder("");
		
		listScroller = new JScrollPane(list);
		
		container.add(toolbar);
		container.add(listScroller);
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		this.add(container);
		this.setTitle(t._("MyBookcase"));
		this.setIconImage(new MyImage("logo.png").getImage());
		this.setSize(800, 600);
		this.setMinimumSize(new Dimension(800,450));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addComponentListener(this);
		Resize();
	}

	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if (command.equals("new_book")){
			newBook();
		} else if (command.equals("edt_book")) {
			BookString book=(BookString) list.getModel().getElementAt(list.getSelectedIndex());
			if (book!=null)	edtBook(book);
		} else if (command.equals("del_book")) {
			BookString book=(BookString) list.getModel().getElementAt(list.getSelectedIndex());
			if (book!=null){
				try {
					mybookcase.removeBook(book.getCode());
					((BookListModel)list.getModel()).removeBook(book);
				} catch (FileNotFoundException e1) {
					new Error(e1.getMessage());
				} catch (BookNotFoundException e1) {
					new Error(e1.getMessage());
				}
			}
		} else if (command.equals("search")) {
			search();
		} else if (command.equals("new_order")) {
			this.list.reorder(toolbar.getOrderBy());
		}
	}
	
	private void newBook(){
		new BookWindow(this, null, this.mybookcase);
	}
	
	private void edtBook(BookString book){
		new BookWindow(this, book, this.mybookcase);
	}

	public void NewBookEvent(String title, String author, String type, String publisher, String media, String position) {
		try{
			BookString book=this.mybookcase.addBook(title, author, type, publisher, media, position, "");
			((BookListModel)list.getModel()).addBook(book);
			Resize();
		} catch (FileNotFoundException e){
			new Error("Coulnd't save new book into the library");
		} catch (AuthorNotFoundException e) {
			new Error(e.getMessage());
		} catch (TypeNotFoundException e) {
			new Error(e.getMessage());
		} catch (PositionNotFoundException e) {
			new Error(e.getMessage());
		} catch (PublisherNotFoundException e) {
			new Error(e.getMessage());
		} catch (MediaNotFoundException e) {
			new Error(e.getMessage());
		}
	}

	public void EditBookEvent(String code, String title, String author,
			String type, String publisher, String media, String position) {
		try {
			((BookListModel)list.getModel()).removeBook(mybookcase.getBookString(code));
			BookString book=mybookcase.editBook(code, title, author, type, publisher, media, position);
			
			System.out.println(book.getTitle());
			((BookListModel)list.getModel()).addBook(book);
			
		} catch (BookNotFoundException e) {
			new Error(e.getMessage());
		} catch (AuthorNotFoundException e) {
			new Error(e.getMessage());
		} catch (FileNotFoundException e) {
			new Error(e.getMessage());
		} catch (TypeNotFoundException e) {
			new Error(e.getMessage());
		} catch (PositionNotFoundException e) {
			new Error(e.getMessage());
		} catch (PublisherNotFoundException e) {
			new Error(e.getMessage());
		} catch (MediaNotFoundException e) {
			new Error(e.getMessage());
		}
		
	}
	
	private void Resize(){
		int toolbar_h=150;
		Dimension dm=new Dimension();
		this.getSize(dm);
		int w=dm.width;
		if (w<800) {w=800;}
		//System.out.println("Resize to: " + dm.toString());
		listScroller.setPreferredSize(new Dimension(dm.width, dm.height-toolbar_h));
		list.setPreferredSize(new Dimension(w-30, 35*mybookcase.getSize()));
		toolbar.setPreferredSize(new Dimension(dm.width, toolbar_h));
		
		validate();
	}
	
	public void componentHidden(ComponentEvent arg0) { }
	public void componentMoved(ComponentEvent arg0) { }
	public void componentResized(ComponentEvent e) { Resize(); }
	public void componentShown(ComponentEvent arg0) { }

	public void changedUpdate(DocumentEvent arg0) {search();}
	public void insertUpdate(DocumentEvent arg0) {search();	}
	public void removeUpdate(DocumentEvent arg0) {search();}
	
	public void search(){
		this.list.Search(toolbar.getSearch()[0], toolbar.getSearch()[1]);
		Resize();
	}
	
}
