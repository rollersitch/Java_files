package excepions;

public class PublisherNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	public PublisherNotFoundException(){
		super("Publisher you searched couldn't be found");
	}
	
	public String toString(){
		return getMessage();
	}
}
