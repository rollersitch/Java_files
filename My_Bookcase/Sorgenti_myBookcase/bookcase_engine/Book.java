package bookcase_engine;

public class Book {
	private String code="";
	private String title="";

	private int author_id=0;
	private int type_id=0;
	private int media_id=0;
	private int publisher_id=0;
	private int position_id=0;
	
	public Book (String code, String title, int author_id, int type_id, int media_id, int publisher_id, int position_id){
		this.code=code;
		this.title=title;
		this.author_id=author_id;
		this.type_id=type_id;
		this.media_id=media_id;
		this.publisher_id=publisher_id;
		this.position_id=position_id;
	}

	public int getMedia_id() {
		return media_id;
	}

	public void setMedia_id(int mediaId) {
		media_id = mediaId;
	}

	public int getPublisher_id() {
		return publisher_id;
	}

	public void setPublisher_id(int publisherId) {
		publisher_id = publisherId;
	}

	public Boolean equals(Book book_cmp){
		return this.getCode().equals(book_cmp.getCode());		
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int authorId) {
		author_id = authorId;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int typeId) {
		type_id = typeId;
	}

	public int getPosition_id() {
		return position_id;
	}

	public void setPosition_id(int positionId) {
		position_id = positionId;
	}
	
	public String toString(){
		return title;
	}
}
