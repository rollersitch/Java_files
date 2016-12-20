/*
 *      Convertitore.java
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

/** Semplice classe a interfaccia grafica che effettua la conversione da lire a
 * euro e viceversa. */

import java.awt.*;
import java.awt.event.*;
import java.text.*;
// La classe è ascoltatore di se stessa
public class Convertitore implements ActionListener {
	/** Fattore di conversione tra lira e euro */
	private final double fattoreDiConversione = 1936.27;
	/** Frame principale */
	private Frame f;
	/** Campo di testo in cui inserire l'importo */
	private TextField t;
	/** Le checkbox per la scelta del tipo di conversione */
	private Checkbox lire,euro;
	/** Pulsante che esegue la conversione */
	private Button b;

	/** Costruttore che crea il frame di base, inserendo in esso un pannello
	 * per meglio gestire il layout, e registra sul frame un ascoltatore utilizzando
	 * WindowAdapter */
	public Convertitore() {
		f = new Frame("Convertitore lire/euro");
		f.setSize(500,500);
		// Registro sul frame un ascoltatore di finestre per settare il comportamento
		// di default per la chiusura
		f.addWindowListener(new WindowAdapter() {          
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
													   
		// Campo di testo vuoto che contiene il valore da											   
		// convertire												  
		t = new TextField("");
		// Pulsante che esegue la conversione
		b = new Button("Converti");
		// Adesso registriamo l'oggetto corrente, cioè il convertitore
		// come ascoltatore degli eventi per il pulsante
		b.addActionListener(this);

		// Costruzione del selettore di valuta, con due
		// checkbox esclusivamente selezionabili
		CheckboxGroup cbg = new CheckboxGroup();
		lire = new Checkbox("lire",true,cbg);
		euro = new Checkbox("euro",false,cbg);
		// Raggruppo le due checkbox e il pulsante in un pannello
		Panel p = new Panel();
		p.add(lire);
		p.add(euro);
		p.add(b);
		// aggiungo il campo di testo nella zona north del layout della finestra
		// (di default viene diviso in 5 zone)
		f.add(t,"North");
		// immettiamo in basso al frame il pannello
		f.add(p,"South");
		// Rendiamo il frame visibile
		f.setVisible(true);
	}

	/** Metodo chiamato quando si preme il pulsante
	 * che effettua la conversione */
	public void actionPerformed(ActionEvent e) {
		String valore = t.getText(); // Legge il valore immesso nel campo
		double d;
		try {
				// Trasformiamo la scritta in un numero
				d = NumberFormat.getInstance().parse(valore).doubleValue();
				// Abbiamo utilizzato il metodo getinstance della classe numberformat
				// il quale ci restituisce un formattatore di numeri, a questo  abbiamo
				// chiesto di parsare la stringa valore per interpretarla come un numero,
				// e poi gli abbiamo specificato che deve essere espresso in formato double
			}
		catch(ParseException px) {
			d = 0.0;
			System.out.println("Errore, " + valore + " non è un numero");
			System.out.println(px.getMessage());
		}
		// Adesso gestiamo la selezione di uno dei due checkbox
		if (lire.getState()) {
			d = d/fattoreDiConversione;
			euro.setState(true);  // cambiamo cioè il bottone selezionato
		}
		else {
			d = d*fattoreDiConversione;
			lire.setState(true);
		}

		// Convertiamo d in una stringa per poterla stampare nell'area di testo
		t.setText(NumberFormat.getInstance().format(d));
	}

		/** Il main che crea un oggetto di tipo Convertitore */

	public static void main(String[] args) {
		Convertitore convertitore = new Convertitore();
	}
}
		
			
			
			

