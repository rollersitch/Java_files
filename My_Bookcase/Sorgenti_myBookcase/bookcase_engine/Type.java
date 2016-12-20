package bookcase_engine;

public class Type  implements IdElement{
	private int id;
	private String type;

	public Type (int id, String type){
		this.id=id;
		this.type=type;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}
	
	public String toString(){
		return type;
	}
}
