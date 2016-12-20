/*
 *      LanciaDado.java
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
/** Classe che costruisce un interfaccia grafica che permette il lancio di un
 * oggetto Dado e lo visualizza in un frame.
 */ 
public class LanciaDado extends JFrame {
	/** Etichetta che contiene il valore uscito */
	private JLabel valore;
	/** Dado da lanciare */
	private Dado dado = new Dado();

	/** Costruttore, crea la GUI di interazione */

	public LanciaDado() {
		// Impostiamo la finestra
		setSize(400,400);
		setTitle("Lancia il dado!!");
		// Accediamo al pannello dei contenuti
		Container sfondo = getContentPane();
		// Assegniamo alla stringa contenuta nell'etichetta il valore uscito dal lancio
		valore = new JLabel(String.valueOf(dado.getValore()),SwingConstants.CENTER);
		// Aggiungo l'etichetta al pannello
		sfondo.add(valore,"Center");
		// Creo un pulsante per il lancio
		JButton pulsante = new JButton("Lancia");
		// Registriamo l'ascoltatore
		pulsante.addActionListener(new GestorePulsante());
		// Aggiungiamo il pulsante *alla finestra*
		add(pulsante,"East");
		// Ultime impostazioni
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/** Ascoltatore del pulsante */

	class GestorePulsante implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dado.lancia();
			valore.setText(String.valueOf(dado.getValore()));
		}
	}
}
		
