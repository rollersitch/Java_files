package listeners;

public interface BookWindowListener {
	public void NewBookEvent(String title, String author, String type, String publisher, String media, String position);
	public void EditBookEvent(String code, String title, String author, String type, String publisher, String media, String position);
}
