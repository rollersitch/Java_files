/*
 *      AnimazioneConTimer.java
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

/** Questa classe crea un'animazione di un testo ma in modo più
 * semplice rispetto a Animazione.java, pochè usa la classe
 * Timer che inserisce un temporizzatore all'applicazione,
 * senza le complicazioni dovute all'inserimento di un oggetto
 * in un thread */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnimazioneConTimer extends JFrame {
	/** coordinata in cui stampare la stringa */
	private int x = 0;
	/** costante che verrà passata al costruttore di Timer */
	final private int RITARDO = 100;

	/** Costruttore che crea la base della finestra, crea un timer
	 * e ne ascolta gli eventi con un listener personalizzato */
	public AnimazioneConTimer() {
		setSize(500,500);
		setTitle("Animazione Con Timer");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		Timer timer = new Timer(RITARDO, new AscoltatoreTimer());
		timer.start();   // Avviamo il timer col metodo start()
		}
	/** Override del paint di Graphics (e anche di Component)
	 * che ridisegna lo sfondo ogni volta, e disegna la stringa
	 * voluta */
	public void paint(Graphics g) {
		super.paint(g);
		g.drawString("Animazione con Timer",x,100);
	}
	/** Classe ascoltatore del timer, che incrementa la coordinata x
	 * e ridisegna tutto chiamando repaint */
	class AscoltatoreTimer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			x = x + 1;
			repaint();
		}
	}
}
