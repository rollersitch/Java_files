package bookcase_engine;

public class BookString {
	String code="";
	String title="";
	String author="";
	String type="";
	String media="";
	String mediaicon="";
	String publisher="";
	String position="";
	
	public BookString(Book book, Author author, Type type, MediaType media, Publisher publisher, Position position){
		this.code=book.getCode();
		this.title=book.getTitle();
		this.author=author.getName();
		this.type=type.getType();
		this.publisher=publisher.getPublisher();
		this.media =media.getMedia();
		this.mediaicon =media.getIcon();
		this.position=position.getPosition();
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getType() {
		return type;
	}

	public String getPosition() {
		return position;
	}
	
	public String getMediaicon() {
		return mediaicon;
	}

	public String getMedia() {
		return media;
	}

	public String getPublisher() {
		return publisher;
	}
	
	public Boolean equals(BookString book){
		return book.getCode().equals(this.code);
	}
}
