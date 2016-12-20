/*
 *      Dado.java
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

import java.util.Random;
/** La classe modella un dado semplice a 6 facce.
 * @author Daniele Pipitone
 * @version 0.0 24/Apr/2010
 */
  
public class Dado {
	/** Generatore di numeri casuali */
	private Random generatore;
	/** Possibile valore del dado */
	private int valore;

	/** Costruisce un dado istanziando un generatore
	 * e simulando un lancio dello stesso
	 */

	public Dado() {
		  generatore = new Random();
		  lancia();
	}

	/** Costruisce un dado istanziando un generatore
	 * fissandone il seme, cosicchè oggetti dello stesso
	 * seme generino sempre lo stesso valore
	 * @see java.util.Random
	 * @param seme Seme impostato
	 */

	public Dado(long seme) {
		generatore = new Random(seme);
		lancia();
	}

	/** Metodo che simula un lancio */

	public void lancia() {
		valore = generatore.nextInt(6) + 1;
	}

	/** Ritorna il valore ottenuto dal lancio.
	 * @return valore Il valore uscito.
	 */
	public int getValore() {
		return valore;
	}
} 
