/*
 *
 * 		Tombola.java
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
import java.util.Scanner;
import java.util.InputMismatchException;


/** Gestione di una cartella di tombola...
 *
 * @author Daniele Pipitone
 * @version Aprile 2010
 */

public class Tombola {
	// Istanze di classe costanti, o meglio di sola lettura
	// che serviranno nelle definizioni di metodi
	/** Numero di righe e colonne della cartella */
	static final int RIGHE=4, COLONNE=5;
	/** Numero minimo di numeri in fila	
	*  per vincere qualcosa */
	static final int MIN_VINCITA = 2;
	/** Definizione del totale di elementi della cartella */                                 
	static final int TOMBOLA = RIGHE*COLONNE;
	// Questo l'ho aggiunto in un secondo momento, è solo una chicca
	/** Nome dell'utente */
	static String nome = leggiNome();
	/** Tutta l'applicazione "vive" nel main() */
	/* Inseriamo tutto nel main */
	public static void main(String[] args) {
		/* Utilizziamo la classe Scanner
		 * passando al suo costruttore come
		 * parametro l'oggetto di classe System
		 * in, che rappresenta lo stdinput, cioè
		 * la tastiera
		 */ 
		Scanner input = new Scanner(System.in);

		

		int cartella[][] = leggiCartella();  // la cartella è implementata da una matrice, creata in base all'input
											 // dell'utente, mediante un apposito metodo
		boolean usciti[][] = new boolean[RIGHE][COLONNE];
		// L'array usciti è un marcatore, marca come true ogni casella corrispondente
		// a un numero uscito in cartella[][],
		// così da facilitare il confronto per la visualizzazione della vincita

		// Nel seguente array viene memorizzato il numero di elementi usciti
		// per ciascuna riga, per indicare l'ambo il terno ecc.
		int numUsciti[] = new int[RIGHE];

		/* Adesso creiamo l'interfaccia del programma, che chiederà all'utente
		 * l'input, delegando il controllo del numero (faremo in modo che
		 * alla pressione di zero l'applicazione termini) e il controllo
		 * della vincita a due opportuni metodi (e poi un terzo metodo per la
		 * visualizzazione della vincita).
		 */

		 int numero,r,vincita;
		
		 do {
			 System.out.print("Inserisci il numero uscito (0 per terminare): ");
			 numero = input.nextInt();
			 if (numero == 0)   break;

			 r=controllaNumero(numero,cartella,usciti,numUsciti);
			 if (r >= 0){
				 vincita = controllaVincita(numUsciti,r);
				 if(vincita > 0) visualizzaVincita(cartella,usciti,r,vincita);
				 }
			 }  while (numero != 0);
		 }
		 /* Fondamentalmente questo è il cuore del programma. Acquisisce il
		  * numero uscito e se è zero termina l'applicazione; altrimenti
		  * cerca il numero dentro la cartella col metodo controlla, e se lo trova
		  * (uno o più) controlla se esso dà una vincita oppure no (per esempio
		  * se l'ambo è già stato fatto, la cosa sarà registrata in numUsciti[]);
		  * Vedere i tre metodi per una comprensione dei parametri passati
		  */



		  /** All'inizio del programma dobbiamo creare questa cartella,
		   * e quindi implementiamo un metodo che la "legge" da tastiera
		   * e assegna i coefficienti ai vari posti della matrice-cartella
		   */
		  static int[][] leggiCartella() {
			  int cart[][] = new int[RIGHE][COLONNE];   // localmente la chiamiamo cart, e poi verrà inserita in cartella[]
			  int i,j;         	// Gli indici della matrice
			  Scanner input = new Scanner(System.in);
			  try {

			  for (i=0;i<RIGHE;i++)  {
				  System.out.print("Inserisci i " + COLONNE + " numeri della riga "+ (i+1) + " (puoi inserirli ad uno a uno e premere invio, oppure immetterli in riga separati da spazio) : ");
				  for (j=0;j<COLONNE;j++) {
					  cart[i][j] = input.nextInt();
			      }
			  }
			  
			  }
			  catch(InputMismatchException exc) {
				  System.out.println("Ma sei scemo?? Ho detto NUMERO!!");
			  }
			  return cart;
		  }
		  /* Abbiamo utilizzato un noto pattern per definire o accedere agli elementi
		   * di una matrice (nel nostro caso a registrarli prendendoli da tastiera).
		   * Abbiamo usato due cicli for di cui uno interno, per accedere a tutti gli elementi.
		   * In pratica i cicli partono da i=0 e impostano tutti i [0][j], poi gli [1][j], fino
		   * al [i][j].
		   * Poi ritorna una matrice di interi che è la nostra cartella che verrà assegnata a
		   * cartella[][] per essere usata nel resto del codice */




		   /** Questo metodo controlla se un numero è presente nella cartella. Se lo
		    * trova, marca a true la posizione nella matrice "specchio" di controllo
		    * (composta da booleani) nell'array usciti, incrementa il contatore corrispondente
		    * in numUsciti, restituisce l'indice della riga (poichè le vincite della tombola si
		    * basano su numeri uguali sulla stessa riga) in cui si trovava il numero.
		    * Se non lo trova, restituisce -1.  */
		    static int controllaNumero(int numero,int cartella[][],boolean usciti[][],int numUsciti[]) {
				// Cerca il numero
				boolean trovato;
				int i,j, riga=-1,col=-1;
				trovato = false;
				for (i=0; !trovato && i<RIGHE;i++) 
					for (j=0; !trovato && j<COLONNE;j++) {
						if (!usciti[i][j] && cartella[i][j] == numero) {    // Questa parte è fondamentale, controlla
							trovato = true;                                 // che il numero, oltre ad esserci, non sia
							riga = i;                                       // già uscito e marcato in usciti
							col = j;
						}
					}
				if(!trovato)            // Se l'if non è verificato trovato rimane a false, quindi viene restituito -1
					return -1;

				usciti[riga][col] = true;            // Imposta a true la casella corrispondente in usciti[][]
				numUsciti[riga]++;					 // Incrementa il contatore di riga in numUsciti[]
				return riga;
				}
				
			
			

			/** Metodo che controlla se si è realizzata una nuova
			 * vincita. In caso affermativo restituisce un numero che
			 * indica la vincita: 2 per l'ambo, 3 per il terno,4 per la quaterna, ecc.
			 * dà 0 se non c'è vincita.
			 */
			 static int controllaVincita(int numUsciti[], int riga) {
				 // Calcola il minimo valore su una riga
				 int min = minimo(numUsciti);   // cioè la riga con meno numeri già usciti

				 if(min == COLONNE)				// Se la riga con meno numeri usciti ne ha quante sono le colonne, allora
					return TOMBOLA;				// tutti i numeri sono già usciti ed è tombola

				// Calcola il massimo valore su una riga, ma deve
				// escludere la riga dell'ultimo numero uscito
				numUsciti[riga]--; // Lo modifichiamo temporaneamente
				int oldMax = massimo(numUsciti);
				numUsciti[riga]++; // Lo riportiamo come prima

				if (numUsciti[riga] > oldMax && numUsciti[riga] >= MIN_VINCITA)
					return numUsciti[riga];
				else
				return 0;
			}

			/** Visualizza una vincita
			 */
			 static void visualizzaVincita(int cartella[][], boolean usciti[][], int riga, int vincita) {
				 if (vincita == TOMBOLA) {
					 System.out.println("E bravo l'asino, hai fatto tombola scegliendo tu i numeri. Sei contento?? :-)");
					 System.out.println("Adesso, " + nome +" , vai a fare qualcosa di più intelligente, ciaoooo!!");
					 System.exit(0);
				}
				else {
					switch(vincita) {
						case 2: System.out.print("Ok, questo è un ambo!!"); break;
						case 3: System.out.print("Maroo, semo o teinno!!"); break;
						case 4: System.out.print("Quaterna Quaterna!!"); break;
						case 5: System.out.print("A cinquina un sa pigghiao ancora nuddro, ta po pigghiare"); break;
						default: System.out.print("Vincita di " + vincita +" numeri"); break;
					}
					System.out.println(" sulla riga " + (riga+1));
					System.out.print("I numeri sono: ");
					int i;
					for (i=0;i<COLONNE;i++) {
						if (usciti[riga][i]) {
							System.out.print(" " + cartella[riga][i]);
						}
						System.out.println();
					}
				}
			}

			/** Calcola il massimo di un array */

			static int massimo(int a[]) {
				int i,m;
				m=a[0];
				for(i=1;i<a.length;i++) {
					if (a[i] > m) {
						m = a[i];
					}
				
				}
				return m;
			}

			/** Calcola il minimo di un array */

			static int minimo(int a[]) {
				int i,m;
				m = a[0];
				for(i=1;i<a.length;i++) {
					if(a[i] < m) {
						m = a[i];
					}
				
				}
				return m;
			}

			/** Legge il nome dell'utente da tastiera
			 * e lo ritorna */

			static String leggiNome() {
				 Scanner tastiera = new Scanner(System.in);
				 String nome = "";
				 System.out.print("Ciao bello, dimmi il tuo nome: ");
		         nome = tastiera.nextLine();
		         return nome;
			 }
}

				
			 
