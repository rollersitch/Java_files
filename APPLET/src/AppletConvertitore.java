/*
 *      AppletConvertitore.java
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
import java.awt.event.*;
import java.text.*;
/** Applet corrispondente all'applicazione convertitore;
 * converte l'importo immesso da lire a euro e viceversa
 */ 
public class AppletConvertitore extends Applet {
	/** Fattore di conversione tra lira e euro */
	private final double fattoreDiConversione = 1936.27;
	/** Area di testo per l'input e l'output */
	private TextField t;
	/** Caselle di scelta per il tipo di conversione */
	private Checkbox lire,euro;
	/** Il pulsante Converti */
	private Button b;

	  /* Come ricorderemo, le applet non hanno costruttori,
	   * poichè vengono costruite dalla JRE dei browser, i quali ne impostano
	   * alcuni parametri base (alcuni definiti nel codice html). Per questo
	   * non abbiamo dovuto creare un JFrame generale per contenere
	   * il resto dei componenti. Non hanno
	   * nemmeno un metodo main, ma chiamano all'avvio
	   * il metodo init; è questo che andiamo ora a implementare
	   */ 
		
	public void init() {
		// impostiamo il layout
		setLayout(new BorderLayout());
		// impostiamo lo sfondo
		setBackground(Color.WHITE);
		// Campo di testo per il valore da convertire
		t = new TextField("");
		// pulsante converti
		b = new Button("COnverti");
		// Registrazione dell'ascoltatore
		b.addActionListener(new AppletConvertitore.Gestore());

		/* Costruzione del selettore di valuta, di cui
		 * i due pulsanti devono essere esclusivamente
		 * selezionabili
		 */
		 CheckboxGroup cbg = new CheckboxGroup();
		 lire = new Checkbox("Lire",true,cbg);
		 euro = new Checkbox("Euro",false,cbg);

		 // Inseriamo un pannello
		 Panel p = new Panel();
		 // e aggiungiamo il pulsante e le checkbox
		 p.add(lire);
		 p.add(euro);
		 p.add(b);
		 //  Immettiamo tutto
		add(p,"South");
		add(t,"North");
	}

	class Gestore implements ActionListener {
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
	}
}
		
	

