package bookcase_engine;

public class Publisher implements IdElement{
	private int id;
	private String publisher;
	public Publisher(int id, String publisher){
		this.id=id;
		this.publisher=publisher;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getId() {
		return id;
	}
	public String toString(){
		return getPublisher();
	}
}
