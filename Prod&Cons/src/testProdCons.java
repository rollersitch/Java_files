/*
 *      testProdCons.java
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

/** Classe di prova del processo di creazione di un dato e inserimento
 * nel pallet da parte del produttore, e prelievo del dato da parte
 * del consumatore
 */ 
public class testProdCons {
	public static void main(String[] args) {
		Pallet pallet = new Pallet();
			Produttore p = new Produttore(pallet,"Produttore");
			Consumatore c = new Consumatore(pallet,"Consumatore");

			p.start();
			c.start();
	}
}
		
