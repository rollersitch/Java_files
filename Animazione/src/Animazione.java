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
import java.awt.event.*;
/** Questa classe crea una animazione di una stringa, inserendola in un thread
 * separato, in modo da consentire la gestione degli eventi, quali
 * la defaultcloseoperation o la pressione dei pulsanti */

// La classe estende JFrame per creare una finestra e implementa Runnable
// per poter creare un thread apposito; non è possibile estendere Thread
// poichè l'ereditarietà multipla non è consentita
public class Animazione extends JFrame implements Runnable {
	/** oggetto di tipo Thread che contiene l'animazione */
	private Thread animazione;
	/** variabile di posizione di partenza per disegnare la stringa */
	private int x = 0;

	/** Costruttore di base, che oltre a settare con i metodi di JFrame
	 * crea due pulsanti per avviare o fermare l'animazione, inserendo tutti
	 * i componenti in un contenitore, in posizione nord e sud */
	public Animazione() {
		setSize(500,500);
		setTitle("Animazione");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Creazione dei due bottoni
		JButton avvia = new JButton("Avvia");
		avvia.addActionListener(new Animazione.Avvia());
		JButton ferma = new JButton("Ferma");
		ferma.addActionListener(new Animazione.Ferma());
		/* Registrazione dell'ascoltatore nei due JButton;
		 * Notare che il listener è direttamente un oggetto
		 * di tipo Avvia o tipo Ferma, definite come classi
		 * all'interno di Animazione, le quali chiamano
		 * i metodi avvia() e ferma() che verranno poi
		 * implementati */ 
		
		
		// Creazione del container e inserimento dei button in esso
		Container sfondo = getContentPane();
		sfondo.add(avvia,"North");
		sfondo.add(ferma,"South");
		setVisible(true);
		}

		/** Metodo paint() della classe Graphics ridefinito in modo
		 * che esso chiami il paint della superclasse per cancellare e
		 * reimpostare ogni volta lo sfondo, altrimenti le lettere
		 * precedentemente disegnate rimarranno a schermo; viene usato
		 * il drawString per scrivere (ogni volta) la parola ciao */
		public void paint(Graphics g) {
			super.paint(g);
			g.drawString("Ciao",x,100);
		}

		/** Override del metodo run ereditato dall'interfaccia
		 * Runnable, per creare un thread separato per l'animazione */
		public void run() {
			while(animazione!=null) {			// Cioè finchè esiste un oggetto a cui
				x = x +1;						// animazione fa riferimento
				// Se x raggiunge la fine della finestra
				// viene reimpostata a 0 e riparte

				if(x >= getWidth()) {
					x = 0;
				}
				repaint(); // Chiamata a repaint che richiama paint

				/* Adesso usiamo Thread per impostare un ritardo di 300 ms, fra un paint
				 * e l'altro; come ricorderete il metodo sleep lancia un'eccezione
				 * che va gestita */
				try {
					Thread.sleep(300);
				}
				catch(InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
		}

			/** Metodo che avvia l'animazione, inserendola in un
			 * nuovo thread, e avvia lo stesso col metodo
			 * start, che chiama il run */
			private void avvia() {
				if(animazione == null) {						// Se animazione è riferimento a niente
					animazione = new Thread(this);				// le viene assegnato un oggetto thread
					animazione.start();							// passando come parametro al costruttore
			    }												// l'oggetto corrente	
			}
														

			/* Adesso creiamo le classi ascoltatori definite all'inizio,
			 * che semplicemente chiamano i metodi appena definiti. Non
			 * creo un metodo ferma(), dato che per far questo basta impostare
			 * a null il riferimento di animazione, cioè bloccare il thread,
			 * dato che esso è condizionato da un while */

			/** Classe ascoltatore di azioni che avvia l'animazione */
			class Avvia implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					avvia();
				}
			}

			/** Classe ascoltatore di azioni che ferma l'animazione
			 * distruggendo l'oggetto animazione di tipo thread */
			class Ferma implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					animazione = null;
				}
			}
		}
		
