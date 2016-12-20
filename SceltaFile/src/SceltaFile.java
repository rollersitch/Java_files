/*
 *      SceltaFile.java
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
import java.io.File;
/** Un menù per la selezione di file su disco */
public class SceltaFile extends JFrame {
	private JTextField testo;
	private JMenuItem apri;
	private JMenuItem esci;

	public SceltaFile() {
		setSize(500,500);
		setTitle("Scelta file");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		testo = new JTextField("Nome del file",20);
		Container sfondo = getContentPane();
		JPanel pannello = new JPanel();
		pannello.add(testo);
		sfondo.add(pannello,"Center");

		AscoltatoreMenu ascoltatore = new AscoltatoreMenu();

		JMenuBar barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);

		JMenu file = new JMenu("File");
		barraMenu.add(file);

		apri = new JMenuItem("Apri");
		file.add(apri);

		apri.addActionListener(ascoltatore);

		esci = new JMenuItem("Esci");
		file.add(esci);

		esci.addActionListener(ascoltatore);
		setVisible(true);
	}
	/* Per aprire i file usiamo la classe JFileChooser */

	public void apriFile() {
		JFileChooser scelta = new JFileChooser();
		// Se la scelta è apri seleziono il file inseriamo il nome del file nel tasto
		if (scelta.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
			File file = scelta.getSelectedFile();
			testo.setText(file.getName());
		}
	}

	private class AscoltatoreMenu implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object source = event.getSource();
			if (source == apri) {
				apriFile();
			}

			if (source == esci) {
				System.exit(0);
			}
		}
	}


	public static void main (String args[]) {
		SceltaFile finestra = new SceltaFile();	
		
	}
}
