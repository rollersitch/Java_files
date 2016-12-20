/*
 *      AppletDisegno.java
 *      
 *      Copyright 2010 Daniele Pipitone <dany-vai@hotmail.it>
 *      
 *      This program is free software; you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation; either version 2 of the License, or
 *      (at your option) any later version.
 *      
 *      This program is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *      
 *      You should have received a copy of the GNU General Public License
 *      along with this program; if not, write to the Free Software
 *      Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 *      MA 02110-1301, USA.
 */
import java.applet.*;
import java.awt.*;
import java.awt.geom.*;

/** Applet che crea una finestra e disegna all'interno una serie di
 * figure geometriche della classe Graphics2D, testandone alcuni
 * metodi di rendering  */ 
public class AppletDisegno extends Applet {
	/* Ridefiniamo il metodo paint, il quale, poichè
	 * Graphics2D è una sottoclasse di Graphics, chiama il
	 * paint di graphics, e accetta come parametro un oggetto
	 * di tipo Graphics. Per ciò sarà necessario
	 * un cast esplicito */
	/** Override del paint di graphics */
	public void paint(Graphics g) {
		setBackground(Color.yellow);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.blue);
		g2.setStroke(new BasicStroke(5));
		Line2D.Double segmento = new Line2D.Double(10,10,400,400);
		g2.draw(segmento);
		Rectangle rett = new Rectangle(100,50,200,300);
		float[] dash = {10.0f};
		g2.setStroke(new BasicStroke(1.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f,dash,0.0f));
		g2.rotate(Math.toRadians(45));
		g2.translate(100,-200);
		g2.draw(rett);
		g2.translate(-100,200);
		g2.rotate(Math.toRadians(-45));
		Ellipse2D.Double cerchio = new Ellipse2D.Double(5,10,200,200);
		g2.setColor(Color.white);
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);
		g2.setComposite(ac);
		g2.fill(cerchio);
		ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0f);
		g2.setComposite(ac);
		GradientPaint rossobianco = new GradientPaint(50,150,Color.WHITE,450,100,Color.RED);
		g2.setPaint(rossobianco);
		Polygon triangolo = new Polygon(new int[] {50,150,450}, new int[] {150,300,100},3);
		g2.fill(triangolo);
	}
}

