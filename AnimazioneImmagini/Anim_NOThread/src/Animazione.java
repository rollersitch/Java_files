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
/**  Animazione di una sequenza di immagini (una sola volta) */
/* Questa classe possiede un problema, il ciclo di animazione è coincidente col thread principale di esecuzione,
 * cosicchè non è possibile inviare un evento durante l'applicazione (per esempio chiudere la finestra).
 * In questo caso poco male poichè l'animazione si ferma e chiude l'applicazione, ma se inserissimo
 * l'animazione in un loop non sarebbe possibile bloccarla, senza terminare brutalmente il programma.
 */ 
public class Animazione extends JFrame {
	/** Vettore che contiene i nomi dei file */
	private String[] fileImmagini = {"121951-UbuntuSunrise.jpg","dscn0558.jpg","fiamma-acqua.jpg","tacchino2.JPG","tramonto1.jpg"};
	/** Vettore delle immagini da gestire */
	private Image immagini[] = new Image[99];

	public Animazione() {
		setSize(400,400);
		setTitle("Animazioni!!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Accediamo al toolkit del Frame
		Toolkit toolkit = getToolkit();
		// Controlliamo che le immagini effettivamente esistano col nome registrato
		// e inseriamo i *file* immagine nell'opportuno array.
		for (int i=0; i < fileImmagini.length;i++) {
			immagini[i] = toolkit.getImage(fileImmagini[i]);
		}

		// Adesso usiamo la classe MediaTracker per caricare i file immagine
		// nella JVM.
		MediaTracker tracker = new MediaTracker(this);
		for (int i = 0; i < fileImmagini.length; i++) {
			tracker.addImage(immagini[i],i);
		}
		/* Aspettiamo che le immagini siano caricate per intero */
		try {
			tracker.waitForAll();
		}
		catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

		setVisible(true);
	}

	/* L'override di paint */

	public void paint(Graphics g) {
		for (int i=0; i<fileImmagini.length;i++) {
			g.drawImage(immagini[i],50,50,this);
			try {
				Thread.sleep(300);
			}
			catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
