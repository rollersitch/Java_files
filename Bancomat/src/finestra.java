import java.awt.*;
import java.awt.event.*;
class finestra {
private static String titolo;
public static void main(String[] args) {
Frame f = new Frame(titolo);
f.setBounds(20,20,200,150);
f.addWindowListener(new ascoltatore());
f.setVisible(true);
}
}


class ascoltatore implements WindowListener {
public void windowClosing(WindowEvent e) {
e.getWindow().dispose();
}
public void windowClosed(WindowEvent e) {
System.exit(0);
}
public void windowOpened(WindowEvent e) {}
public void windowIconified(WindowEvent e) {}
public void windowDeiconified(WindowEvent e) {}
public void windowActivated(WindowEvent e) {}
public void windowDeactivated(WindowEvent e) {}
}
