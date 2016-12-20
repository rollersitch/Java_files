package data.icons;

import javax.swing.ImageIcon;

public class MyImage extends ImageIcon{
	private static final long serialVersionUID = 1L;

	public MyImage(String filename){
		super();
		if (this.getClass().getResource(filename)!=null){
			this.setImage(new ImageIcon(this.getClass().getResource(filename)).getImage());
		}
	}

}
