/*
 *      Telaio.java
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
/** Il telaio di una macchina, componente dell'oggetto
 * complesso finale.
 * @author Daniele Pipitone
 * @version 0.0 21/Apr/2010
 * @see CostruttoreAuto.java
 */ 


public class Telaio {
	/** Costruisce un telaio minimale */
	public Telaio() {}

	/** Ritorna il nome del telaio.
	 * @override Sovrascrive il toString di Object
	 */ 
	public String toString() {
		return "Telaio";
	}
}
