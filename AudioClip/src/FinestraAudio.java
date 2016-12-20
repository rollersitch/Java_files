/*
 *      FinestraAudio.java
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
import java.applet.*;
import java.net.*;
import java.io.*;

/** Questo frame esegue un file audio */
public class FinestraAudio  extends JFrame {
	private AudioClip audio;

	public FinestraAudio() {
		try {
			// FIX: Occupa troppa memoria, probabile stack overflow
			audio = Applet.newAudioClip(new URL(new File(".").toURI().toURL(),"audio.wav"));
		}
		catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}

		setSize(200,200);
		setTitle("Audio");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JButton avvia = new JButton("Avvia");
		avvia.addActionListener(new Avvia());
		JButton ferma = new JButton("Ferma");
		ferma.addActionListener(new Ferma());

		Container sfondo = getContentPane();
		sfondo.add(avvia,"West");
		sfondo.add(ferma,"East");
		setVisible(true);
	}

	class Avvia implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			audio.play();
		}
	}

	class Ferma implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			audio.stop();
		}
	}
}
		

	