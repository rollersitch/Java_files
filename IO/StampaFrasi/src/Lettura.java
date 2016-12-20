/*
 *      Lettura.java
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
/* Esempio di lettura di input dallo stdinput System.in(classe PrintWriter);
 * quest'ultimo viene concatenato ad un BufferedReader per leggere delle stringhe
 * complete, mediante un meccanismo di buffer.
 */ 
public class Lettura {

	public static void main (String args[]) {		
		// Creo un oggetto per convertire un flusso di byte in flusso di caratteri.
		// Imposto come sorgente la tastiera
		InputStreamReader input = new InputStreamReader(System.in);

		// Passo l'input ad uno reader "buffered"
		BufferedReader tastiera = new BufferedReader(input);

		String s = "";
		System.out.println("Inserisci delle frasi:(\"fine\" per terminare)");
		// I metodi read() ereditati fermano il thread in attesa di input

		try {
			while (!(s = tastiera.readLine()).equals("fine")) {
				System.out.println(s);
			}
		}

		catch (IOException exc) {
			System.out.println(exc);
		}
	}
}cd
