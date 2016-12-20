/*
 *      ListaAttributi.java
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

/** Classe che crea dei metodi per associare degli attributi a un oggetto */
public class ListaAttributi implements ConAttributi {
	private int numAttributi;
	private Attributo[] attributi = new Attributo[9];

	public ListaAttributi() {
		numAttributi = 0;
	}

	public int getNumAttributi() {
		return numAttributi;
	}

	public void elencaAttributi() {
		for (int i=0; i<numAttributi;i++) {
			System.out.println(attributi[i]);
		}
	}

	public void aggiungiAttributo(String nome, String valore) {
		attributi[numAttributi] = new Attributo(nome,valore);
		numAttributi++;
	}
}
	
		
