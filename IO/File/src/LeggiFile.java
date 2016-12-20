/*
 *      File.java
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

/* Questa classe va eseguita in vari punti del filesystem per provarne tutte le possibilità */
import java.io.*;

public class LeggiFile {

	public static void main (String args[]) {
		// Directory corrente		
		File corrente = new File(".");

		String[] elenco = corrente.list();
		for(int i=0;i<elenco.length;i++) {
			System.out.println(elenco[i]);

			// File veri e propri nella directory (o anche sottodirectory)
			File f = new File(elenco[i]);

			if (f.isFile()) {
				System.out.println(" -file");
			}
			if (f.isDirectory()) {
				System.out.println(" -directory");
			}
		}
	}
}
