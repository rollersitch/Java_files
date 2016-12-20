package gui_list;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import bookcase_engine.BookString;
import data.icons.MyImage;

public class BookRow implements ListCellRenderer, ComponentListener{
	
	JPanel panel= new JPanel();
	JLabel licon=new JLabel();
	JLabel title=new JLabel();
	JLabel author=new JLabel();
	JLabel type=new JLabel();
	JLabel publisher=new JLabel();
	JLabel position=new JLabel();
	
	public BookRow(){
		
	}

	public Component getListCellRendererComponent(JList list,
            Object obj,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
		
		list.addComponentListener(this);
		
		licon.setPreferredSize(new Dimension(30, 30));
		
		Resize(new Dimension(800,30));
		
		//panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.add(licon);
		panel.add(title);
		panel.add(author);
		panel.add(type);
		panel.add(publisher);
		panel.add(position);
		
		panel.addComponentListener(this);
		
		if (isSelected) {
            panel.setBackground(list.getSelectionBackground());
            panel.setForeground(list.getSelectionForeground());
        } else {
            panel.setBackground(list.getBackground());
            panel.setForeground(list.getForeground());
        }
		BookString book=(BookString) obj;
		if (book!=null){
			licon.setIcon(new MyImage(book.getMediaicon()));
			title.setText(book.getTitle());
			author.setText(book.getAuthor());
			type.setText(book.getType());
			publisher.setText(book.getPublisher());
			position.setText(book.getPosition());
		}
		Resize (list.getSize());
        return panel;
	}
	
	public void Resize(Dimension dm){
		int w =dm.width;
		int per=(w-35)/100;
		panel.setPreferredSize(new Dimension(w,35));
		title.setPreferredSize(new Dimension(30*per, 30));
		author.setPreferredSize(new Dimension(15*per, 30));
		type.setPreferredSize(new Dimension(15*per, 30));
		publisher.setPreferredSize(new Dimension(15*per, 30));
		position.setPreferredSize(new Dimension(25*per, 30));
	}

	public void componentHidden(ComponentEvent arg0) { }
	public void componentMoved(ComponentEvent arg0) { }
	public void componentResized(ComponentEvent e) {
		Resize(e.getComponent().getSize());
	}
	public void componentShown(ComponentEvent arg0) { }
}
