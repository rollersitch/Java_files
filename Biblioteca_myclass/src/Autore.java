/*
 *      Autore.java
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

/** Modello per l'autore di un testo.
 * @author Daniele Pipitone
 * @version 0.0 23/Apr/2010
 * @see Biblioteca
 * @see Libro
 * @see Scaffale
 */
public class Autore extends Human {
	private String nazionalita;

	/** Costruttore di default */
	public Autore() {}

	/** Costruttore rapido */
	public Autore(String nome, String cognome,String nazionalita) {
		super(nome,cognome);
		this.nazionalita = nazionalita;
	}

	/* METODI ACCESSORI */

	/* Il getNome di Human */
	/* Il getCognome di Human */
	/** Metodo che restituisce la nazionalità dell'autore
	 * @return nazionalita Nazionalità dell'autore
	 */ 
	public String getNazionalita() {
	return nazionalita;
	}

	/** Metodo che accetta un Autore per trovarne la
	 * nazionalità.
	 * @return naz Nazionalità dell'autore.
	 * @param author Autore
	 */

	public String getNazionalita(Autore author) {
		String naz = author.getNazionalita();
		return naz;
	}

	/* METODI MODIFICATORI */

	/* setNome di Human */
	/*setCognome di Human */

	public void setNazionalita(String naz) {
		this.nazionalita = naz;
	}

	public void descrivi() {
		System.out.println("Nome: " + this.getNome());
		System.out.println("Cognome: " + this.getCognome());
		System.out.println("Nazionalità:" + this.getNazionalita());
	}


}
