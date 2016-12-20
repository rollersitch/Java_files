/*
 *      ControllaRisorsa.java
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
/** Questa classe crea un oggetto che permette di controllare e gestire l'accesso
 * a una certa risorsa quando sia necessario che esso venga richiesto da più
 * oggetti contemporaneamente, o dallo stesso oggetto più volte in modo ricorsivo.
 * L'uso di metodi sincronizzati da solo non può gestire tutto ciò, e crea il rischio
 * di deadlock
 */ 

public class ControllaRisorsa {
	/** Il thread che chiama il controllore, istanza della classe, il quale possiede momentaneamente
	 * l'accesso alla risorsa
	 */
	private Thread owner = null;
	/** Variabile contatore di accessi di un singolo thread (annidati,cioè contemporanei) */
	/* Ovviamente l'accesso separato in diversi flussi di esecuzione da parte dello stesso oggetto
	 * non crea problemi e non necessita di un contatore
	 */
	 private int counter = 0;

	 /* Adesso implementeremo due metodi, che permettono all'oggetto
	  * richiedente di assumere il controllo della risorsa, nel caso essa sia libera
	  * o occupata da se stessi; poi un metodo che rilascia il controllo
	  * per liberare la risorsa
	  */

	  /** Metodo che delega il controllo della possibilità di
	   * accesso alla risorsa al metodo privato tentaAssunzione,
	   * il quale restituisce true se la risorsa è libera o occupata
	   * dal thread corrente. Se è occupata da un altro thread, l'oggetto
	   * aspetta.
	   */ 
	    public synchronized void assumiControllo() {
		    while (!tentaAssunzione()) {
			  try {
				  wait();
			  }
			  catch(InterruptedException ie) {
			  }
		    }
	    }  

        /** Metodo che gestisce il rilascio della risorsa */

        public synchronized void rilasciaControllo() {
			/* La prima condizione è necessaria per evitare che un thread
			 * che non sia quello corrente possa richiedere il rilascio di
			 * una risorsa di cui non è proprietario, mandando in errore
			 * il controllore
			 */ 
			if (owner == Thread.currentThread()) {
				counter--;
				// Se il counter va a zero, la risorsa va liberata
				if (counter == 0) {
					owner = null;
					// Svegliamo eventuali oggetti che aspettano a causa del metodo wait()
					notify();
				}
			}
		} 










	    




		/** Il metodo "lucchetto", dice al thread che lo chiama se la
		 * risorsa richiesta è accessibile
		 */

		private synchronized boolean tentaAssunzione() {
						
			Thread me = Thread.currentThread();
			if (owner == null) {  // Se nessuno ha il controllo
				// Diventa l'owner
				owner = me;
				// Il contatore registra l'accesso
				counter = 1;
				// Restituiamo l'ok
				return true;
			}
			// Seconda possibilità, l'owner è il thread stesso
			else if (owner == me) {
				// Registriamo il successivo accesso
				counter++;
				// Diamo l'ok
				return true;
			}
			// In tutti gli altri casi, cioè esiste già un thread che la occupa e non è quello corrente
			else { return false; }
		}
}
	
