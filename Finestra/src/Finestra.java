/*
 *      Finestra.java
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

/* Questa classe crea una finestra che permette l'impostazione del testo
 * su una textarea, attraverso vari componenti */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Finestra extends JFrame {
	private JTextField testo;
	private JCheckBox corsivo;
	private JCheckBox grassetto;         /* Dichiarazione delle variabili di istanza */
	private JRadioButton piccolo;
	private JRadioButton medio;
	private  JRadioButton grande;
	private JComboBox selezione;

	public Finestra() {                   /* Costruttore pubblico di default */
	// Impostazione iniziale della finestra in dimensione, titolo, e comportamento alla chiusura (metodi ereditati da
	// JFrame)
	setSize(200,200);
	setTitle("Stili di testo");
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	// Creazione di un campo di testo non editabile
	testo = new JTextField("Testo di prova");
	testo.setEditable(false);
	// Registrazione di un ascoltatore personalizzato per "testo"
	GestoreScelta gestore = new GestoreScelta();
	// Casella combinata per scelta tipo di carattere, editabile, in cui inserisco tre voci e registro "gestore"
	selezione = new JComboBox();
	selezione.addItem("Serif");
	selezione.addItem("SansSerif");
	selezione.addItem("Monospaced");
	selezione.setEditable(true);
	selezione.addActionListener(gestore);
	// Pulsanti per la selezione di dimensione, di tipo JRadioButton, con ascoltatore "gestore", raggruppati in
	// un ButtonGroup
	piccolo = new JRadioButton("Piccolo");
	piccolo.setSelected(true);      // Piccolo è quello selezionato di default
	piccolo.addActionListener(gestore);
	medio = new JRadioButton("Medio");
	medio.addActionListener(gestore);
	grande = new JRadioButton("Grande");
	grande.addActionListener(gestore);
	/* Inserisco i tre pulsanti in un ButtonGroup così che siano esclusivamente selezionabili */
	ButtonGroup gruppoDim = new ButtonGroup();
	gruppoDim.add(piccolo);
	gruppoDim.add(medio);
	gruppoDim.add(grande);
	// Caselle di controllo per lo stile, con "gestore"
	corsivo = new JCheckBox("Corsivo");
	corsivo.addActionListener(gestore);
	grassetto = new JCheckBox("grassetto");
	grassetto.addActionListener(gestore);
	/* Preparazione della finestra principale; Creazione di 4 pannelli, di cui uno ne contiene altri tre, che
	 * conterranno gli elementi creati. Per ognuno useremo dei metodi di JPanel per impostazioni di layout e
	 * di bordo. Tutti questi componenti verranno inseriti nel Container sfondo, e verrà dichiarata visibile la
	 * finestra principale */
	// Pannello 1
	JPanel pannello1 = new JPanel();
	pannello1.add(selezione);  // Qui inseriamo la casella combinata
	// Pannello 2
	JPanel pannello2 = new JPanel();
	pannello2.add(piccolo);               // Qui inseriamo 
	pannello2.add(medio);                 // i pulsanti di selezione
	pannello2.add(grande);                // di gruppoDim
	/* Nel pannello 2 settiamo un bordo che è di tipo Titled, cioè con titolo,
	 * e gli passiamo come argomento un Etched, cioè marcato, con titolo Dimensione */
	pannello2.setBorder(new TitledBorder(new EtchedBorder(),"Dimensione"));
	// Pannello 3
	JPanel pannello3 = new JPanel();											// Qui aggiungo i checkbox
	pannello3.add(corsivo);														// e imposto un bordo come
	pannello3.add(grassetto);													// quello precedente, ma con
	pannello3.setBorder(new TitledBorder(new EtchedBorder(),"Stile"));			// titolo Stile
	// Pannello 4, principale contenitore degli altri tre
	JPanel pannelloStili = new JPanel();
	pannelloStili.setLayout(new GridLayout(3,1));          // Setto il layout di tipo griglia, 3 righe e 1 colonna
	/* Aggiungo i tre pannelli al pannello principale */
	pannelloStili.add(pannello1);
	pannelloStili.add(pannello2);
	pannelloStili.add(pannello3);
	/* Adesso creo un Container, che dovrà contenere la area di testo in zona centrale e il pannelloStili con i suoi
	 * sottocomponenti in zona sud */
	Container sfondo = getContentPane();
	sfondo.add(testo,"Center");
	sfondo.add(pannelloStili,"South");
	// Chiamo un metodo per modificare effettivamente lo stile
	impostaTesto();
	setVisible(true);
	}

	/* Fino ad adesso abbiamo solo creato l'interfaccia dell'applicazione, ora implementiamo il metodo
	 * che effettivamente la fa funzionare */
/* Metodo cuore dell'applicazione */
	void impostaTesto() {
		/* Inizializzo tipo,stile e dimensione, che serviranno per
		 * il costruttore di un oggetto Font che chiede come parametri appunto
		 * stile tipo e dimensione */

		// Il tipo lo dichiaro con il metodo getSelectedItem, che mi restituisce l'oggetto
		// selezionato, che quindi va convertito esplicitamente in stringa
		String tipo = (String)selezione.getSelectedItem();
		int stile = 0;
		if (corsivo.isSelected()) {
			stile = stile + Font.ITALIC;                              // Imposto il carattere accedendo alle costanti
		}                                                             // della classe Font
		if (grassetto.isSelected()) {
			stile = stile + Font.BOLD;
		}
		int dimensione = 0;                                         // Imposto la dimensione a seconda 
		if(piccolo.isSelected()) {                                  // della scelta nei JRadioButton(s)
			dimensione = 12;
		}
		if(medio.isSelected()) {
			dimensione = 16;
		}
		if(grande.isSelected())
		 {
			dimensione = 24;
		}

		// Imposto il testo nella textarea a seconda delle selezioni dei pannelli, settando il tipo di
		// carattere col metodo setFont, che prende come parametro un oggetto di tipo Font, di cui sfrutto un
		// costruttore che accetta stringa tipo, intero stile e intero dimensione

		testo.setFont(new Font(tipo,stile,dimensione));
		testo.repaint(); // Ridisegna la finestra (solo la textarea)
	}


	/* Manca ancora una cosa fondamentale, il gestore, cioè l'ascoltatore di azioni che
	 * abbiamo registrato su tutti i componenti */
	class GestoreScelta implements ActionListener {
		public void actionPerformed(ActionEvent e) {     // Utilizzo l'unico metodo dell'interfaccia
			impostaTesto();                              // ActionListener per richiamare impostaTesto
		}												 // ogni volta che si verifica un evento
	}
}
