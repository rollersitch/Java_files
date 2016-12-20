/*
 *      CambiaColore.java
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
/** Semplice applicazione grafica che cambia il colore dello sfondo
 * attraverso la pressione di pulsanti
 */ 
// Usiamo i componenti swing, a mio avviso migliori degli awt
public class CambiaColore extends JFrame{
	/* Il pannello dei contenuti, e i 4 pulsanti, vanno definiti nella
	 * classe, poichè per poter dichiarare private questi componenti
	 * (che come tutti gli swing sono anche contenitori) dobbiamo inserire
	 * la classe ascoltatore (ne useremo solo una) come classe interna; essa deve
	 * però poter accedere agli attributi; non possiamo quindi definirli
	 * nel costruttore, poichè non sarebbero raggiungibili dall'ascoltatore
	 */
	/** Il pannello dei contenuti, intermedio tra JFrame e i componenti */
	 private Container sfondo;
	 /** I pulsanti */
	private JButton primo;
	private JButton secondo;
	private JButton terzo;
	private JButton quarto;

	/** Costruttore della finestra.
	 * Crea i pulsanti, li installa nel pannello
	 * dei contenuti, e registra la classe ascoltatore
	 * interna che andremo a definire
	 */

	 public CambiaColore() {
		 setSize(500,500);
		 setTitle("Cambia Colore coi pulsanti!!");
		 // Istanziamo i pulsanti
		 primo = new JButton("Rosso");
		 secondo = new JButton("Verde");
		 terzo = new JButton("Blu");
		 quarto = new JButton("Giallo");

		 // Inseriamo il gestore di ActionEvent (quelli di pertinenza
		 // dei JButton)
		 GestorePulsanti gestore = new GestorePulsanti();
		 primo.addActionListener(gestore);
		 secondo.addActionListener(gestore);
		 terzo.addActionListener(gestore);
		 quarto.addActionListener(gestore);

		 // Inseriamo nella finestra il pannello dei contenuti
		 // e in esso immettiamo i pulsanti nelle 4 zone limitrofe

		 sfondo = getContentPane();
		 sfondo.add(primo,"East");
		 sfondo.add(secondo,"West");
		 sfondo.add(terzo,"North");
		 sfondo.add(quarto,"South");

		 // Impostiamo le ultime cose per la finestra

		 setDefaultCloseOperation(EXIT_ON_CLOSE);
		 setVisible(true);
	 }

	 /* La zona "Center" del pannello rimarrà scoperta, così setteremo
	  * lo sfondo di "sfondo", cioè del pannello dei contenuti, il quale
	  * sarà invisibile nelle 4 zone per la presenza di componenti (nel nostro
	  * caso, pulsanti), ma rimarrà visibile nel mezzo
	  */

	  /** Ascoltatore degli eventi bottonepremuto interna
	   * alla classe pubblica
	   */
		/* Per gli ascoltatori di solito preferisco le adapter,
		* molto più comode, ma per actionlistener non esistono,
		* visto che contiene un solo metodo su cui fare override
		* e quindi perdono di significato
		*/ 
	   class GestorePulsanti implements ActionListener {   
			public void actionPerformed(ActionEvent e) {
				// Adesso verifichiamo quale pulsante sia la sorgente
				// dell'evento, e invochiamo il metodo corretto
				if (e.getSource() == primo) {
					sfondo.setBackground(Color.RED);
				}
				else if (e.getSource() == secondo) {
					sfondo.setBackground(Color.GREEN);
				}
				else if (e.getSource() == terzo) {
					sfondo.setBackground(Color.BLUE);
				}
				else if (e.getSource() == quarto) {
					sfondo.setBackground(Color.YELLOW);
				}
			}
		}

    // Per testarla creiamo nel main un oggetto di tipo CambiaColore
	public static void main (String args[]) {		
			CambiaColore finestra = new CambiaColore();
	}
}
