/*
 *      Rettangolo.java
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

/** Classe che modella un rettangolo di qualsiasi tipo.
 * @author Daniele Pipitone
 * @version 0.0 21/Apr/2010
 */

 public class Rettangolo {
	 /** Base del rettangolo */
	 private int base;
	 /** Altezza del rettangolo */
	 private int altezza;

	/** Costruttore che inizializza a 0 gli attributi */

	public Rettangolo() {
		base = 0;
		altezza = 0;
	}

	public Rettangolo(int a,int b) {
		base = b;
		altezza = a;
	}


	public int getBase() {
		return base;
	}


	public int getAltezza() {
		return altezza;
	}

	public void setBase(int b) {
		base = b;
	}

	public void setAltezza(int a) {
		altezza = a;
	}


	public boolean equals(Rettangolo r) {
		return (base == r.base && altezza == r.altezza);
	}
}
