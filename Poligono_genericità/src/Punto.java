/*
 *      Punto.java
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

/** Classe che rappresenta un punto geometrico
 * @author Daniele Pipitone
 * @version 0.0 22/Apr/2010
 * @see Poligono
 */ 
public class Punto {
    /** Coordinata ascissa */
	private int x;
	/** Coordinata ordinata */
	private int y;

	/** Costruttore generico, pone il punto nell'origine
	 * degli assi cartesiani.
	 */

	public Punto() {
		x = 0;
		y = 0;
	}

	/** Costruttore che imposta l'ascissa
	 * @param i Ascissa del punto
	 */ 

	public Punto(int i) {
		this.x = i;
		y = 0;
	}


	/** Costruttore che posiziona il punto
	 * alle coordinate specificate.
	 * @param i Ascissa del punto
	 * @param j Ordinata del punto
	 */

	public Punto(int i, int j) {
		this.x = i;
		this.y = j;
	}

	/*METODI ACCESSORI */

	/** Ritorna l'ascissa */

	public int getX() {
		return x;
	}

	/** Ritorna l'ordinata */

	public int getY() {
		return y;
	}

	/** Ritorna una stringa con le coordinate in notazione
	 * matematica
	 * @return String Stringa che rappresenta il punto.
	 */

	/* Override sul toString di Object */

	public String toString() {
		return ("(" + getX() + "," + getY() + ")");
	}

	/** Calcola e restituisce la distanza di un punto
	 * passato come parametro dal punto corrente.
	 * @return dis La distanza tra i punti
	 * @param p Punto
	 */

	public double distanza(Punto p) {
		double dis = Math.sqrt(Math.pow((x-p.x),2) + Math.pow((y-p.y),2));
		return dis;
	}
}
