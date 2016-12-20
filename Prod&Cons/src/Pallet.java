/*
 *      Pallet.java
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

/** Questa classe rappresenta un pattern di tipo Pallet, cioè una zona
 * dove un oggetto produttore può inserire un oggetto e un oggetto
 * consumatore può prelevarlo, senza potersi però accavallare le due
 * operazioni
 */ 
public class Pallet {
	/** Il dato da immagazzinare */
	/* Il dato inserito qui per semplicità è un intero, ma la cosa
	 * è facilmente modificabile con un Object
	 */
	// private Object dato;
	private int dato;
	/** Il lucchetto, che indica se il dato sia presente;
	 * ovviamente all'inizio non ci sono dati
	 */ 
	private boolean datoPresente = false;

	/** Metodo get, che chiamato dal consumatore, permette di
	 * acquisire il dato inserito
	 */
	// public synchronized Object get() {
	public synchronized int get() {
		/* Finchè il dato non c'è, il metodo aspetta */
		while (!datoPresente) {
			try {
				wait();
			}
			catch (InterruptedException e) { }
		}
		/* Una volta che si è visto che è presente, torniamo a
		 * false il lucchetto, svegliamo il produttore (che si era messo
		 * in attesa) e ritorniamo il dato
		 */
		datoPresente = false;
		notify();
		return dato;
	}


	/** Metodo usato dal produttore, che inserisce il dato nel pallet */
	// public synchronized void put(Object dato) {
	public synchronized void put(int dato) {
		/* Fin quando c'è un qualsiasi dato nel pallet, il prod
		 * aspetta;
		 */ 
		while (datoPresente) {
			try {
				wait();
			}
			catch(InterruptedException e) {}
		}
		/* Appena il pallet è libero, il parametro dato viene inserito
		 * in "dato", il flag viene messo a true e si notifica al
		 * consumatore che aspettava che ce ne fosse uno
		 */

		this.dato = dato;
		datoPresente = true;
		notify();
	}

} 
		
			


				 
