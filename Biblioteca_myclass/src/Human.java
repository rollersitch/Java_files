/*
 *      Human.java
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

/** Questa classe modella un essere umano medio */
public class Human {
	private static String nome;                                    /** Le caratteristiche anagrafiche base di un umano */
	private static String cognome;
	private static int età;
	private static double altezza;
	private static double peso;
	private static String sesso;
	/** Costruttore base , ridefinito su quello di default; inizializza a zero i tipi int e double e a null gli oggetti stringa */
	public Human() {
	}


	/** Costruttore parziale, utile per la classe Autore */

	public Human(String nome,String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}
	/** Costruttore che accetta parametri, per una inizializzazione rapida */
	public Human(double peso,int età,double altezza,String sesso,String nome, String cognome) {
		this.peso = peso;
		this.età = età;
		this.altezza = altezza;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
	}
	/** Metodi per impostare gli attributi dell'oggetto, che saranno poi dichiarati private */
	public void setNome(String n) {
		this.nome = n;
	}
	public void setCognome(String cogn) {
		this.cognome = cogn;
	}
	public void setAltezza(double alt) {
		this.altezza = alt;
	}
	public void setPeso(double weight) {
		this.peso = weight;
	}
	public void setEtà(int age) {
		this.età = age;
	}
	public void setSesso(String sex) {
		this.sesso = sex;
	}
	/** Metodi di accesso agli attributi */
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public static double getAltezza() {
		return altezza;
	}
	public double getPeso() {
		return peso;
	}
	public int getEtà() {
		return età;
	}
	public String getSesso() {
		return sesso;
	}
	/** Metodo di descrizione dell'oggetto con stampa dei suoi dati in una stringa a video */
	public void descrivi() {
		System.out.println(this.getNome() + " " + this.getCognome() +" è alta " + this.getAltezza() + " metri e pesa " + this.getPeso() + " kg , inoltre ha " + this.getEtà() + " anni ed è un/a " + this.getSesso());
	}
}
	
