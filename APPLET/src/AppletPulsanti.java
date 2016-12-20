	import java.applet.*;
	import java.awt.*;
	import javax.swing.*;
	
	public class AppletPulsanti extends Applet {
		public void init() {
			add(new Button("uno"));
			add(new JButton("due"));
			add(new JButton("tre"));
			add(new JButton("quattro"));
			add(new JButton("cinque"));
			add(new JButton("sei"));
		}
	}
