package excepions;

public class PositionNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	public PositionNotFoundException(){
		super("Position you searched couldn't be found");
	}
	
	public String toString(){
		return getMessage();
	}
}
