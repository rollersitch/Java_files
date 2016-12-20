/*
 *      Poligono.java
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

import java.util.*;

/** Classe che rappresenta un poligono.
 * @author Daniele Pipitone
 * @version 0.0 22/Apr/2010
 * @see Punto
 */

 /* In questa classe useremo la genericità della classe Vector,
  * inserendo come attributo del poligono un vettore di oggetti
  * Punto.
  */
  
public class Poligono {
	/** Vertici del poligono. */
	/* Usiamo un vector perchè così possiamo creare un poligono
	 * qualunque, con qualsiasi numero di vertici. Usando un array
	 * potremmo solo definire le varie classi rettangolo triangolo
	 * esagono ecc. dato che esso non è modificabile. In realtà esistono
	 * metodi di modifica che creano un nuovo array copiando il primo;
	 * questo però incide sulle prestazioni.
	 */
 	private Vector<Punto> vertici;

 	/** Costruisce un poligono generico */
 	public Poligono() {
		vertici = new Vector<Punto>();
	}
	/** Aggiunge un vertice al poligono
	 * @param p Punto
	 */
	/* Ecco che passiamo al vettore un oggetto Punto.
	 * Se avessimo usato il raw type aggiungendo un intero
	 * al vettore il compilatore avrebbe sollevato un
	 * warning di unchecked.
	 */  
	public void aggiungiVertice(Punto p) {
		vertici.add(p);
	}
	/** Restituisce il vertice della posizione indicata. */
	public Punto getVertice(int i) {
		if (i<vertici.size()) { return vertici.get(i); }
		return null;
	}
	
	/** Stampa i vertici del poligono.
	 * Di questo metodo esistono versioni diverse con diversi
	 * tool utilizzati.
	 * @see Poligono2.java
	 * @see Poligono3.java
	 * @see Poligono4.java
	*/
	public void stampaVertici() {
		for (int i = 0;i<vertici.size();i++) {
			Punto p = getVertice(i);
			System.out.println(p);
		}
	}

	/** Calcola e restituisce il perimetro del poligono.
	 * @return perimetro Perimetro del poligono
	 * @see Punto.aggiungiVertice() in Punto.java
	 */
	 

	public double getPerimetro() {
		double perimetro = 0;
		for (int i=0; i<vertici.size(); i++) {
			perimetro = perimetro + (getVertice(i)).distanza(getVertice(i+1));
		}
		perimetro = perimetro + (getVertice(vertici.size() - 1)).distanza(getVertice(0));
		return perimetro;
	}
}
