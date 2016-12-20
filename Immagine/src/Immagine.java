/*
 *      Immagine.java
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

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;

public class Immagine extends JFrame {
	Image immagine;

	public Immagine() {
		setSize(400,400);
		setTitle("Fiori");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		try {
			immagine = ImageIO.read(new File("fiori.jpeg"));
		}
		catch (IOException e) {
			System.out.println("File non trovato");
		}

		setVisible(true);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(immagine,0,0,this);
	}


	public static void main (String args[]) {
		Immagine immagine = new Immagine();		
		
	}
}
