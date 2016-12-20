package excepions;

public class TypeNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public TypeNotFoundException() {
	   super("Couldn't find the type you search");
	}
    public String toString() {
        return getMessage();
    }
}