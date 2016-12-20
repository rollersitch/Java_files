package gui;

import gui_elements.MyButton;
import gui_elements.MyCmbTxtField;
import gui_elements.MyTxtField;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import listeners.BookWindowListener;
import translations.Translator;
import bookcase_engine.BookString;
import bookcase_engine.Bookcase;
import data.icons.MyImage;

public class BookWindow extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private BookString book=null;
	private BookWindowListener AL=null;
	
	private Translator t=new Translator();
	
	private MyTxtField title=new MyTxtField("Book title", t);
	private MyCmbTxtField author=new MyCmbTxtField("Author name", t);
	private MyCmbTxtField type=new MyCmbTxtField("Type", t);
	private MyCmbTxtField media=new MyCmbTxtField("Media Type", t);
	private MyCmbTxtField publisher=new MyCmbTxtField("Publisher", t);
	private MyCmbTxtField position=new MyCmbTxtField("Position", t);
	
	private MyButton apply=new MyButton(t._("Insert"), new MyImage("new.png"));
	
	private Boolean newBook=true;
	
	public BookWindow(BookWindowListener AL, BookString book, Bookcase library){
		this.AL=AL;
		if (book!=null){
			this.book=book;
			newBook=false;
		}
		
		
		author.setElements(library.getAuthors().toArray());
		type.setElements(library.getTypes().toArray());
		position.setElements(library.getPositions().toArray());
		media.setElements(library.getMedias().toArray());
		media.setEditable(false);
		publisher.setElements(library.getPublishers().toArray());
		apply.setActionCommand("new_book_window_return");
		if (newBook==false){
			apply.setText(t._("Edit book"));
			apply.setIcon(new MyImage("edit.png"));
			
			title.setText(this.book.getTitle());
			author.setSelectedItem(book.getAuthor());
			type.setSelectedItem(book.getType());
			position.setSelectedItem(book.getPosition());
			media.setSelectedItem(book.getMedia());
			publisher.setSelectedItem(book.getPublisher());
			apply.setActionCommand("edit_book_window_return");
		}
		
		
		apply.addActionListener(this);
		
		JPanel container = new JPanel();
		
		container.add(new JPanel());
		container.add(title);
		container.add(author);
		container.add(publisher);
		container.add(type);
		container.add(media);
		container.add(position);
		
		container.add(apply);
		container.add(new JPanel());
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		this.add(container);
		this.setMinimumSize(new Dimension(500, 400));
		if (newBook){
			this.setTitle(t._("New book"));
		} else {
			this.setTitle(t._("Edit book"));
		}
		this.setIconImage(new MyImage("book.png").getImage());
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if (command.equals("new_book_window_return")){
			AL.NewBookEvent(title.getText(), author.getText(), type.getText(), media.getText(), publisher.getText(), position.getText());
			this.setVisible(false);
		} else if (command.equals("edit_book_window_return")){
			AL.EditBookEvent(book.getCode(), title.getText(), author.getText(), type.getText(), media.getText(), publisher.getText(), position.getText());
			this.setVisible(false);
		}
	}
}
