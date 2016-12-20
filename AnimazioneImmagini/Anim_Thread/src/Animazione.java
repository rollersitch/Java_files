/*
 *      Animazione.java
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
/** Classe che esegue un'animazione e la inserisce in un thread secondario, così
 * da lasciare libero il thread principale di ascoltare altri eventi.
 */

 
public class Animazione extends JFrame implements Runnable {
		private String[] fileImmagini = {"121951-UbuntuSunrise.jpg","dscn0558.jpg","fiamma-acqua.jpg","tacchino2.JPG","tramonto1.jpg"};
		private Image immagini[] = new Image[99];
		private MediaTracker tracker;
		private int imm;
		private int numImm;
		private Thread animazione;

/** Costruisce la finestra, controlla che le immagini esistano, le carica nella JVM,
 * aspetta che siano tutte caricate, inserisce l'animazione in un thread e lo prepara
 * all'esecuzione.
 */ 		

		public Animazione() {
			setSize(400,400);
			setTitle("Animazioni!!");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			Toolkit toolkit = getToolkit();
			numImm = fileImmagini.length;
			for (int i = 0; i<numImm ; i++) {
				immagini[i] = toolkit.getImage(fileImmagini[i]);
			}
			tracker = new MediaTracker(this);
			for (int i = 0;  i<numImm; i++) {
				tracker.addImage(immagini[i],i);
			}
			try {
				tracker.waitForAll();
			}
			catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			setVisible(true);
			animazione = new Thread(this);
			animazione.start();
		}

		public void paint(Graphics g) {
			// Ridisegnamo lo sfondo
			super.paint(g);
			g.drawImage(immagini[imm],50,50,this);
		}

		public void run() {
			do {
				repaint();
				try {
					Thread.sleep(300);
				}
				catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
				imm = imm+1;
				imm = imm % numImm;
			} while (true);
		}
	}

