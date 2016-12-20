/*
 *      Produttore.java
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

/** La classe che rappresenta un produttore */
/* Essa estende thread poichè dovrà gestire dei flussi
 * di esecuzione diversi
 */ 
public class Produttore extends Thread {
	// Creiamo l'oggetto di tipo Pallet
	private Pallet p;

	/** Questo è il costruttore, che inserisce un Pallet come
	 * zona di scambio con un consumatore e lo collega all'oggetto
	 * produttore. Poi usa il setName ereditato da thread per meglio
	 * gestire il processo
	 */

	 public Produttore(Pallet p, String nome) {
		 this.p = p;
		 setName(nome);
	 }

	 /* Adesso overridiamo il run, che inserirà in questo esempio
	  * le cifre da 0 a 9 nel pallet chiamando il suo metodo put, e
	  * lo notificherà grazie al getName di thread
	  */

	public void run() {
		for (int i = 0; i < 10 ; i++) {
			p.put(i);
			System.out.println(getName() + ": inviato(" + i + ")");
			/* Questo sleep simula il tempo necessario alla produzione
			 * di un oggetto successivo, usando un ritardo casuale (compreso
			 * tra 0 e 1) con il metodo random della classe Math, moltiplicandolo
			 * per 100. Siccome random restituisce un double e sleep accetta interi
			 * dobbiamo castare il risultato
			 */ 
			try {
				sleep((int)(Math.random() * 100));
			}
			catch (InterruptedException e) { }
		}
	}
}  
