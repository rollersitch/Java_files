/*
 *      Mammifero.java
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

/** Classe per la rappresentazione dei mammiferi, sottoclasse di
 * Animale.
 * @author Daniele Pipitone
 * @version 0.0 12/Apr/2010
 * @see java.util.Scanner
 */
  
public abstract class Mammifero extends Animale {
	/** Costruttore che istanzia Mammifero usando il costruttore
	 * della superclasse.
	 */

	public Mammifero() {
		super("mammifero");
	}

	public Mammifero(String s) {
		super("mammifero",s);
	}

	/** Ridefinizione del metodo di Animale.
	 * 
	 * @return "corre" movimento specifico del mammifero.
	 */
	@Override
	public String siMuove() {
		return "corre";
	}
} 
