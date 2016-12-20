package excepions;

public class AuthorNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	public AuthorNotFoundException(){
		super ("The author you selected wasn't found");
	}
	
	public String toString() {
        return getMessage();
    }

}
