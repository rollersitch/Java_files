/*
 *      Animale.java
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

/** Modello per animale generico.
 * @author Daniele Pipitone
 * @version 0.0 21/Apr/2010
 */ 
public abstract class Animale {
	/** Regno zoologico */
	private String regno;
	/** Classe zoologica*/
	private String classe;
	/** Specie zoologica */
	private String specie;
	/** Costruttore di default, assegna alla variabile d'istanza
	 * regno la stringa "animale"
	 */
	public Animale() {
		regno = "Animale";
	}

	/** Costruttore che assegna la classe oltre al regno.
	 * @param classe la classe zoologica dell'oggetto
	 */
	public Animale(String classe) {
		 this();
		 this.classe = classe;
	}
	/** Costruttore che assegna la classe, il regno, e
	 * la specie.
	 * @param classe,specie Dati zoologici dell'essere
	 */
	public Animale(String classe,String specie) {
		this(classe);
		this.specie = specie;
	}

	/** Stampa i dati dell'oggetto animale istanziato.
	*/

	public void stampaDati() {
		System.out.print("regno: " + regno);
		System.out.print("," + " classe: " + classe);
		System.out.println(", specie " + specie);
	}

	/** Metodo astratto per il movimento */

	public abstract String siMuove();

	/** Metodo astratto per il verso
	 * @return verso il verso dell'animale
	 */ 
	
	public abstract String verso();
}  
	  
