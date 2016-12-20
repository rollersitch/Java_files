/*
 *      Libro.java
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


public class Libro {
	private String titolo;
	private String note;
	private String isbn;
	private Autore autore;
	

	public Libro() {}

	public Libro(String titolo,Autore autore) {
		this.titolo = titolo;
		this.autore = autore;
		
	}

	public void insertNote(String note) {
		this.note = note;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setAutore(Autore autore) {
		this.autore = autore;
	}

	public String getTitolo() {
		return titolo;
	}

	public String getNote() {
		return note;
	}

	public Autore getAutore() {
		return autore;
	}

	public String getIsbn() {
		return isbn;
	}

	public void stampaDati() {
		System.out.println("Il libro " + getTitolo());
		this.getAutore().descrivi();
		if (!(isbn == null)) {
			System.out.println("Il suo codice isbn è: " + getIsbn());
		}
		if (!(note == null)) {
			System.out.println("Note: \n" + getNote());
		}
	}
}
		
		
