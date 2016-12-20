/*
 *      IOOggetti.java
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

import java.io.*;


/* Questa classe salva degli ipotetici oggetti Punto in file binari sul filesystem */
public class IOOggetti {
	private static Punto p1;
	// Metodo di scrittua sul filesystem, statico (la classe ha un approccio procedurale)
	public static void scrittura() {
		try {
			
			// Creo un flusso di byte per scrivere un file
			FileOutputStream fileout = new FileOutputStream("oggetti.bin");
			// Lo inserisco in un flusso creato apposta per serializzare oggetti
			ObjectOutputStream fout = new ObjectOutputStream(fileout);

			// Scrivo 10 oggetti Punto
			for (int i=0; i<10; i++) {
				Punto p = new Punto();
				fout.writeObject(p);
			}

			// Chiudo lo stream del file
			fout.close();
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}// scrittura()

		// Metodo di lettura

	public static Object lettura() {
		try {
			// Creo il flusso di input per un file
			FileInputStream filein = new FileInputStream("oggetti.bin");
			// Creo un flusso di oggetti in entrata da file
			ObjectInputStream fin = new ObjectInputStream(filein);

			// parametro per evita la EOFException
			boolean fineFile = false;
			while(!fineFile) {
				try {
					// La lettura viene fatta come Object, bisogna fare un cast; gli oggetti vanno reistanziati
					Punto p = (Punto)fin.readObject();
					// Stampa dell'oggetto (metodo toString)
					System.out.println(p);
				}

				catch (EOFException e) {
					fineFile = true;
				}
			}
			p1 = (Punto)fin.readObject();
			fin.close();
			
		}

		// Dobbiamo gestire la ClassNotFoundException e la IOException

		catch (ClassNotFoundException e) {
			e.getMessage();
		}

		catch (IOException e) {
			e.getMessage();
		}
		return p1;
	}
}
	
