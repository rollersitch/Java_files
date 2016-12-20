package bookcase_engine;


public class MediaType implements IdElement{
	private int id;
	private String media;
	private String icon;
	public MediaType(int id, String media, String icon){
		this.id=id;
		this.media=media;
		this.icon=icon;
	}
	
	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getId() {
		return id;
	}
	
	public String toString(){
		return getMedia();
	}
}
