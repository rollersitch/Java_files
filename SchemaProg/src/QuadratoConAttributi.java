/*
 *      QuadratoConAttributi.java
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


public class QuadratoConAttributi extends Rettangolo implements ConAttributi {
	private ListaAttributi attributi = new ListaAttributi();

	public QuadratoConAttributi() {
		super();
	}

	public QuadratoConAttributi(int lato) {
		super(lato,lato);
	}

	public int getLato() {
		return getBase();
	}

	public void setLato(int lato) {
		setBase(lato);
		setAltezza(lato);
	}

	public void elencaAttributi() {
		System.out.println("lato = " + getLato());
		attributi.elencaAttributi();
	}

	public void aggiungiAttributo(String nome,String valore) {
		attributi.aggiungiAttributo(nome,valore);
	}
}
	
