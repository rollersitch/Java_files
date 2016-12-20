package bookcase_engine;

public class Position implements IdElement{
	private int id;
	private String position;
		
	public Position (int id, String position){
		this.position=position;
		this.id=id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getId() {
		return id;
	}
	public String toString(){
		return position;
	}
}
