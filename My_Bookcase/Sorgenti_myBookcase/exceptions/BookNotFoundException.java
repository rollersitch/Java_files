package excepions;

public class BookNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public BookNotFoundException() {
	   super("Couldn't find the book you search");
	}
    public String toString() {
        return getMessage();
    }
}
