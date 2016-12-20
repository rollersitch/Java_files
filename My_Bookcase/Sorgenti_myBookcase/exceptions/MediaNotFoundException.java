package excepions;

public class MediaNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	public MediaNotFoundException(){
		super("Media you searched couldn't be found");
	}
	
	public String toString(){
		return getMessage();
	}
}
