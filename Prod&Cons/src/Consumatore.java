/*
 *      Consumatore.java
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

/** La classe simula il comportamento di un consumatore */
/* Anche consumatore estende thread, visto che ha bisogno di un
 * flusso di esecuzione indipendente. Vedi Produttore.java per
 * informazioni, le due classi sono totalmente simmetriche e duali
 */ 
public class Consumatore extends Thread {
	private Pallet p;

	public Consumatore(Pallet p, String nome) {
		this.p = p;
		setName(nome);
	}

	public void run() {
		int d;
		for (int i = 0; i < 10; i++) {
			d = p.get();
			System.out.println(getName() + " :ricevuto" + i);
		}
	}
}
			
