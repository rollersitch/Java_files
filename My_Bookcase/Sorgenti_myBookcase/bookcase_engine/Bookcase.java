package bookcase_engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import excepions.AuthorNotFoundException;
import excepions.BookNotFoundException;
import excepions.MediaNotFoundException;
import excepions.PositionNotFoundException;
import excepions.PublisherNotFoundException;
import excepions.TypeNotFoundException;

public class Bookcase {
	private ArrayList<Book> mybookcase;
	private ArrayList<Author> authors;
	private ArrayList<Type> types;
	private ArrayList<Publisher> publishers;
	private ArrayList<MediaType> medias;
	private ArrayList<Position> positions;
	private String filename="";
	
	public Bookcase(){
		this.mybookcase= new ArrayList<Book>();
		this.authors= new ArrayList<Author>();
		this.types= new ArrayList<Type>();
		this.publishers= new ArrayList<Publisher>();
		this.medias= new ArrayList<MediaType>();
		this.positions= new ArrayList<Position>();
		
		
		
		
		filename=System.getProperty("user.home")+File.separator+".library.xml";
		
		
	}
	
	public void addMedia(String name, String icon){
		this.medias.add(new MediaType(this.medias.size(), name, icon));
	}
	
	public ArrayList<Type> getTypes() {
		return types;
	}

	public ArrayList<Book> getMybookcase() {
		return mybookcase;
	}

	public ArrayList<Author> getAuthors() {
		return authors;
	}

	public ArrayList<Position> getPositions() {
		return positions;
	}
	
	public ArrayList<MediaType> getMedias() {
		return medias;
	}
	
	public ArrayList<Publisher> getPublishers() {
		return publishers;
	}
	
	public BookString addBook(String title, String author_name, String type_name, String media_name, String publisher_name, String position_name, String code) throws FileNotFoundException, AuthorNotFoundException, TypeNotFoundException, PositionNotFoundException, PublisherNotFoundException, MediaNotFoundException{
		int author_id=-1;
		int type_id=-1;
		int publisher_id=-1;
		int position_id=-1;
		int media_id=-1;
		
		//Codice
		Boolean no_good_code=false;
		if (code==""){
			code=randomCode();
		}
		do{
			//Cerca se il codice è già in uso
			no_good_code=false;
			for (Book book : mybookcase) {
				if (code.equals(book.getCode())){
					no_good_code=true;
					code=randomCode();
					break;				
				}
			}
		} while(no_good_code==true);
		//System.out.println(code);
		
		System.out.println("Creating a new book: title=" + title + " code=" + code + " author=" + author_name + " type=" + type_name);
		
		//Autore
		for (Author author : authors) {
			if (author_name.equals(author.getName())){
				author_id=author.getId();
				break;				
			}
		}
		if (author_id<=0){
			author_id=get_free_id(ListToArray(authors));
			Author newAuthor = new Author(author_id, author_name);
			authors.add(newAuthor);
		}
		//System.out.println(author_id);
		
		//Tipo
		for (Type type : types){
			if (type_name.equals(type.getType())){
				type_id=type.getId();
				break;
			}
		}
		if (type_id<=0){
			type_id=get_free_id(ListToArray(types));
			Type newType = new Type(type_id, type_name);
			types.add(newType);
		}
		//System.out.println(type_id);
		
		//MediaType
		for (MediaType media : medias){
			if (media_name.equals(media.getMedia())){
				media_id=media.getId();
				break;
			}
		}
		if (media_id<=0){
			media_id=0;
		}
		//System.out.println(media_id);
		
		//Publisher
		if (!publisher_name.equals("") && publisher_name!=null){
			for (Publisher publisher : publishers){
				if (publisher_name.equals(publisher.getPublisher())){
					publisher_id=publisher.getId();
					break;
				}
			}
			if (publisher_id<=0){
				publisher_id=get_free_id(ListToArray(publishers));
				Publisher newPublisher = new Publisher(publisher_id, publisher_name);
				publishers.add(newPublisher);
				
			}
		} else {
			publisher_id=0;
		}
		//System.out.println(publisher_id);
		
		//Position
		for (Position position : positions){
			if (position_name.equals(position.getPosition())){
				position_id=position.getId();
				break;
			}
		}
		if (position_id<=0){
			position_id=get_free_id(ListToArray(positions));
			Position newPosition = new Position(position_id, position_name);
			positions.add(newPosition);
		}
		//System.out.println(position_id);
		
		//Create the book and insert it in the bookcase
		Book new_book = new Book(code, title, author_id, type_id, media_id, publisher_id, position_id);
		this.mybookcase.add(new_book);
		
		//Save changes of the bookcase
		this.saveBookcase();
		System.out.println("Book added to the library");
		
		return new BookString(new_book, getAuthor(author_id), getType(type_id), getMedia(media_id), getPublisher(publisher_id), getPosition(position_id));
	}
	
	private Publisher getPublisher(int publisher_id) throws PublisherNotFoundException {
		for (Publisher publisher:publishers){
			if (publisher_id==publisher.getId()){
				return publisher;
			}
		}
		if (publisher_id<=0){
			Publisher newPub= new Publisher(0, "");
			publishers.add(newPub);
			return newPub;
		}
		throw new PublisherNotFoundException();
	}

	private MediaType getMedia(int media_id) throws MediaNotFoundException {
		for (MediaType media:medias){
			if (media_id==media.getId()){
				return media;
			}
		}
		if (media_id==-1) return medias.get(0);
		throw new MediaNotFoundException();
	}

	public BookString[] toArray(){
		BookString[] libreria = new BookString[this.mybookcase.size()];
		
		
		int i=0;
		for (Book book:mybookcase){
			try {
				libreria[i]=new BookString(book, getAuthor(book.getAuthor_id()), getType(book.getType_id()), getMedia(book.getMedia_id()), getPublisher(book.getPublisher_id()), getPosition(book.getPosition_id()));
				i+=1;
			} catch (Exception e){
				System.err.println("Something is missing in this book" +e.getMessage());
			}
		}
		
		return libreria;
	}
	
	public void removeBook(String code) throws BookNotFoundException, FileNotFoundException{
		Book book=getBook(code);
		
		//Save relevant data before delete the book
		int author_id=book.getAuthor_id();
		int type_id=book.getType_id();
		int publisher_id=book.getPublisher_id();
		
		//Remove the book, I don't need this anymore
		mybookcase.remove(book);
		
		//Check if there'are other book with this type, author and if not delete them.
		try{
			findSameAuthor(author_id);
		} catch (BookNotFoundException e){
			//remove author
			try {
				Author author=getAuthor(author_id);
				if (author!=null) this.authors.remove(author);
			} catch (AuthorNotFoundException e2){
				System.err.println("Warning: author not exist.");
			}
		}

		try{
			findSameType(type_id);
		} catch (BookNotFoundException e){
			//remove type
			try {
				Type type=getType(type_id);
				this.types.remove(type);
			} catch (TypeNotFoundException e2){
				System.err.println("Warning: type not exist.");
			}
		}

		try{
			findSamePublisher(publisher_id);
		} catch (BookNotFoundException e){
			//remove Publisher
			try {
				Publisher publisher=getPublisher(publisher_id);
				this.types.remove(publisher);
			} catch (PublisherNotFoundException e2){
				System.err.println("Warning: publisher not exist.");
			}
		}
		//Save changes
		this.saveBookcase();
	}
	
	public BookString editBook(String code, String title, String author_name, String type_name, String publisher_name, String media_name, String position_name) throws BookNotFoundException, AuthorNotFoundException, FileNotFoundException, TypeNotFoundException, PositionNotFoundException, PublisherNotFoundException, MediaNotFoundException {
		
		removeBook(code);
		return addBook(title, author_name, type_name, publisher_name, media_name, position_name, code);
		
	}
	
	public Book getBook(String code) throws BookNotFoundException{
		for (Book book:mybookcase){
			if (code.equals(book.getCode())){
				return book;
			}
		}
		throw new BookNotFoundException();
	}
	
	public BookString getBookString(String code) throws BookNotFoundException, AuthorNotFoundException, TypeNotFoundException, MediaNotFoundException, PublisherNotFoundException, PositionNotFoundException{
		Book book=getBook(code);
		
		return new BookString(book, getAuthor(book.getAuthor_id()), getType(book.getType_id()), getMedia(book.getMedia_id()), getPublisher(book.getPublisher_id()), getPosition(book.getPosition_id()));
	}
	
	public ArrayList<Book> findSameAuthor(int author_id) throws BookNotFoundException{
		ArrayList<Book> booklist=new ArrayList<Book>();
		
		for (Book book:mybookcase){
			if (author_id==book.getAuthor_id()){
				booklist.add(book);
			}
		}
		
		if (booklist.size()>0){
			return booklist;
		} else {
			throw new BookNotFoundException();
		}
	}

	public ArrayList<Book> findSameType(int type_id) throws BookNotFoundException{
		ArrayList<Book> booklist=new ArrayList<Book>();
		
		for (Book book:mybookcase){
			if (type_id==book.getType_id()){
				booklist.add(book);
			}
		}
		
		if (booklist.size()>0){
			return booklist;
		} else {
			throw new BookNotFoundException();
		}
	}

	public ArrayList<Book> findSamePublisher(int publisher_id) throws BookNotFoundException{
		ArrayList<Book> booklist=new ArrayList<Book>();
		
		for (Book book:mybookcase){
			if (publisher_id==book.getPublisher_id()){
				booklist.add(book);
			}
		}
		
		if (booklist.size()>0){
			return booklist;
		} else {
			throw new BookNotFoundException();
		}
	}
	
	public Author getAuthor(int author_id) throws AuthorNotFoundException{
		for (Author author:authors){
			if (author_id==author.getId()){
				return author;
			}
		}
		throw new AuthorNotFoundException();
	}

	public Type getType(int type_id) throws TypeNotFoundException{
		for (Type type:types){
			if (type_id==type.getId()){
				return type;
			}
		}
		throw new TypeNotFoundException();
	}
	
	public Position getPosition (int position_id) throws PositionNotFoundException{
		for (Position position:positions){
			if (position_id==position.getId()){
				return position;
			}
		}
		if (position_id==-1) return new Position(0, "Default Position");
		throw new PositionNotFoundException();
		
	}
	
	public int getSize(){
		return mybookcase.size();
	}
	
	//Funzioni di lettura/scrittura su disco
	public void saveBookcase() throws FileNotFoundException{
		saveBookcase(filename);
	}
	public void saveBookcase(String filename) throws FileNotFoundException{
		FileOutputStream fos = new FileOutputStream(filename);
		PrintStream Output = new PrintStream(fos);
		
		Output.println("<xml>\n" +
				"<file>MyBookcase 1.0.0 file</file>\n");
		
		Output.println("\t<authors>");
		for (Author author:authors){
			Output.println("\t\t<author id=\""+Integer.toString(author.getId())+"\">"+author.getName()+"</author>");
		}
		Output.println("\t</authors>\n");
		
		Output.println("\t<types>");
		for (Type type:types){
			Output.println("\t\t<type id=\""+Integer.toString(type.getId())+"\">"+type.getType()+"</type>");
		}
		Output.println("\t</types>\n");
		
		Output.println("\t<publishers>");
		for (Publisher publisher:publishers){
			Output.println("\t\t<publisher id=\""+Integer.toString(publisher.getId())+"\">"+publisher.getPublisher()+"</publisher>");
		}
		Output.println("\t</publishers>\n");
		
		
		Output.println("\t<positions>");
		for (Position position:positions){
			Output.println("\t\t<position id=\""+Integer.toString(position.getId())+"\">"+position.getPosition()+"</position>");
		}
		Output.println("\t</positions>\n");
		
		Output.println("\t<books>");
		for (Book book:mybookcase){
			Output.println("\t\t<book code=\""+book.getCode()+"\" " +
					"author_id=\""+Integer.toString(book.getAuthor_id())+"\" " +
					"type_id=\""+Integer.toString(book.getType_id())+"\" " +
					"publisher_id=\""+Integer.toString(book.getPublisher_id())+"\" " +
					"media_id=\""+Integer.toString(book.getMedia_id())+"\" " +
					"position_id=\""+Integer.toString(book.getPosition_id())+"\"" +
					">" + book.getTitle()+"</book>");
		}
		Output.println("\t</books>\n");
		
		
		Output.println("</xml>");
	}
	
	public void loadBookcase() throws JDOMException, IOException{
		loadBookcase(filename);
	}
	public void loadBookcase(String filename) throws JDOMException, IOException{
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build(new File(filename));
		
		Element root = document.getRootElement();
		Iterator<?> iterator = root.getChildren().iterator();
		
		while(iterator.hasNext()){
		    Element item = (Element)iterator.next();
		    String name=item.getName();
		    if (name.equals("file")){
		    	System.out.println("Reading file info");
		    	System.out.println(item.getText());	
		    } else if (name.equals("authors")){
		    	Iterator<?> i2=item.getChildren().iterator();
		    	while (i2.hasNext()){
		    		Element author = (Element)i2.next();
		    		System.out.println("author: " + author.getAttributeValue("id")+" - "+author.getText());
		    		this.authors.add(new Author(Integer.parseInt(author.getAttributeValue("id")), author.getText()));
		    	}
		    } else if (name.equals("types")){
		    	Iterator<?> i2=item.getChildren().iterator();
		    	while (i2.hasNext()){
		    		Element type = (Element)i2.next();
		    		System.out.println("type: " + type.getAttributeValue("id")+" - "+type.getText());
		    		this.types.add(new Type(Integer.parseInt(type.getAttributeValue("id")), type.getText()));
		    	}
		    } else if (name.equals("publishers")){
		    	Iterator<?> i2=item.getChildren().iterator();
		    	while (i2.hasNext()){
		    		Element publisher = (Element)i2.next();
		    		System.out.println("publisher: " + publisher.getAttributeValue("id")+" - "+publisher.getText());
		    		this.publishers.add(new Publisher(Integer.parseInt(publisher.getAttributeValue("id")), publisher.getText()));
		    	}
		    } else if (name.equals("positions")){
		    	Iterator<?> i2=item.getChildren().iterator();
		    	while (i2.hasNext()){
		    		Element position = (Element)i2.next();
		    		System.out.println("position: " + position.getAttributeValue("id")+" - "+position.getText());
		    		this.positions.add(new Position(Integer.parseInt(position.getAttributeValue("id")), position.getText()));
		    	}
		    } else if (name.equals("books")){
		    	Iterator<?> i2=item.getChildren().iterator();
		    	while (i2.hasNext()){
		    		Element book = (Element)i2.next();
		    		String code= book.getAttributeValue("code");
		    		String author_id= ifNullZero(book.getAttributeValue("author_id"));
		    		String type_id= ifNullZero(book.getAttributeValue("type_id"));
		    		String media_id= ifNullZero(book.getAttributeValue("media_id"));
		    		String publisher_id= ifNullZero(book.getAttributeValue("publisher_id"));
		    		String position_id= ifNullZero(book.getAttributeValue("position_id"));
		    		
		    		
		    		System.out.println("book: " + code+" - "+book.getText());
		    		this.mybookcase.add(
		    				new Book(code, book.getText(), Integer.parseInt(author_id), Integer.parseInt(type_id), Integer.parseInt(media_id), Integer.parseInt(publisher_id), Integer.parseInt(position_id)));
		    	}
		    }
		}

	}
	
	// Funzioni accessorie
	
	private String ifNullZero(String str) {
		if (str==null){
			return "0";
		} else {
			return str;
		}
	}
	
	private String randomCode(){
		Random r = new Random();
		String token = Long.toString(Math.abs(r.nextLong()), 36);
		return token;
	}
	
	private int get_free_id(IdElement[] list){
		int id=1;
		Boolean found;
		do {
			found=false;
			for (IdElement object : list) {
				if (id==object.getId()){
					id=object.getId()+1;
					found=true;
				}
			}
		} while (found==true);
		return id;
	}
	
	public IdElement[] ListToArray(ArrayList<?> list){
		IdElement[] array=new IdElement[list.size()] ;
		int i=0;
		for (Object el:list){
			array[i]=(IdElement)el;
			i+=1;
		}
		return array;
	}
}
