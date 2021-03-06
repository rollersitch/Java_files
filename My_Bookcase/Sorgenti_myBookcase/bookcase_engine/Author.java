package bookcase_engine;

public class Author implements IdElement{
	private int id;
	private String name;
	
	public Author (int id, String name){
		this.id=id;
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public String toString(){
		return name;
	}
}
