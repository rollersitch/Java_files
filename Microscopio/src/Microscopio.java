/*
 *      Microscopio.java
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
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/** Applicazione grafica che visualizza la vita evolutiva di
 * un insieme di batteri. La usiamo per provare e dimostrare
 * l'uso di thread diversi
 */
/* La classe principale Microscopio estende la classe Canvas, del
 * package awt, che crea una porzione di schermo disegnabile, nella quale
 * andremo a disegnare le animazioni dei batteri che nascono e muoiono
 * (in  swing avremmo potuto usare un JPanel). Poichè l'ereditarietà multipla
 * di classi in java non è permessa, non possiamo fargli estendere la classe
 * Thread; tuttavia è possibile l'ereditarietà multipla delle interfacce (in pratica
 * si possono implementare più interfacce). Per questo essa estende Runnable, creata
 * appositamente, che ha un solo metodo run().
 */
public class Microscopio extends Canvas implements Runnable {
    /** Un vettore che contiene i batteri */
    private Vector<Batterio> batteri;
    /** Il numero di batteri corrente */
    private int numeroBatteri = 0;
    /** Produttore di nascite e morti */
    /* Questa è una istanza di Random, cioè un produttore
     * di eventi casuali; la useremo per produrre le nascite
     * e le morti dei batteri
     */
    private Random rng;

    /** Il costruttore della classe imposta i soliti
     * parametri grafici, istanzia il Random e il vettore
     * batteri, e inserisce l'applicazione(l'oggetto corrente)
     * in un thread, rendendolo avviabile col metodo start()
     */
    public Microscopio() {
        setSize(200,200);
        rng = new Random();
        batteri = new Vector<Batterio>();
        // Un ciclo che chiama un metodo, il quale crea i batteri
        for (int i=0; i<10 ; i++) {
            creaBatterio();
        }
        Thread t = new Thread(this);
        t.start();
        // setVisible(true);
    }

    /* Andiamo adesso a definire i metodi opportuni. Per prima cosa
     * creiamo creaBatterio, che costruisce un batterio (di classe Batterio
     * che andrà scritta a parte). Poi i metodi aggiungi,rimuovi,e get, i quali
     * dovranno essere sincronizzati, poichè devono agire su una risorsa
     * condivisa, il vettore "batteri"
     */

     public void creaBatterio() {
         /* Inseriamo in d le misure della figura che rappresenta
          * il batterio. Un oggetto Dimension è praticamente
          * un contenitore che contiene all'interno
          * width e height, quindi potremo accedervi con
          * d.width e d.height. Lo scopo è randomizzare il disegno
          * dei batteri in coordinate pseudocasuali, nei limiti
          * della dimensione del canvas Microscopio;
          * il resto del codice è chiaramente comprensibile andando
          * a vedere il costruttore della classe Batterio
          */
         Dimension d = getSize();
         // Creiamo il batterio alle coordinate casuali nei limiti del frame, ...........
         Batterio b = new Batterio(rng.nextInt(d.width),rng.nextInt(d.height),this, new Random(rng.nextLong()));
         // Lo aggiungiamo al vettore
         aggiungiBatterio(b);
     }
    /** Metodo sincronizzato che aggiunge un oggetto Batterio al vettore
     * e incrementa il contatore di batteri
     */
     public synchronized void aggiungiBatterio(Batterio b) {
         batteri.add(b);
         numeroBatteri++;
     }

     /** Metodo sincronizzato che elimina un oggetto Batterio dal vettore
      * e decrementa il contatore di batteri
      */
      public synchronized void rimuoviBatterio(Batterio batt) {
          batteri.remove(batt);
          numeroBatteri--;
      }
      /** Metodo che restituisce l'insieme di batteri
       * al momento presenti
       */
       /* Questo è il metodo che deve essere chiamato in
        * multithreading, visto che si deve continuamente
        * accedervi per ottenere il numero di batteri, il quale
        * può essere modificato dai metodi precedentemente
        * implementati. Esso restituisce un array di oggetti
        * generici, che nel nostro caso saranno oggetti Batterio
        * , (altrimenti avremmo potuto fare estendere una classe a Batterio,
        * rendendola sua superclasse, e farci restituire un array di "superoggetti",
        * cioè una cosa del tipo TipoSuperclasse[]). Per fare ciò utilizziamo
        * il metodo toArray, poichè batteri è stato istanziato come
        * Vector, poichè quest'ultimo, al contrario di Array è modificabile,
        * ma soprattutto è sincronizzato.
        */
        public synchronized Object[] getBatteri() {
            return batteri.toArray();
        }

        /* Adesso, come di consueto, ridefiniamo il metodo paint.
         * Da notare che oggi in Java è presente una classe più potente
         * , derivata da Graphics, cioè Graphics2D; tuttavia per
         * retrocompatibilità il metodo paint accetta sempre e solo un
         * parametro di tipo Graphics. Si ovvia alla cosa utilizzano un cast,
         * scrivendo questo codice
         * [CODE]
         * public void paint(Graphics g) {
             * Graphics2D g2 = (Graphics2D)g;
             * }
             * [/CODE]
             */
        public void paint(Graphics g) {
            // Array di oggetti Batterio
            Object[] bs = getBatteri();
            /* Questo ciclo semplicemente prende ogni Object di bs,
             * lo casta a tipo Batterio, e ne applica il metodo disegna
             * sull'oggetto Graphics(fondamentalmente, lo sfondo) descritto
             * nella classe Batterio. Notate che abbiamo scritto "bs.length" e non
             * "bs.length()", l'attributo length della classe Array stranamente
             * non è privato ed è accessibile
             */
            for (int i=0; i<bs.length ; i++) {
                ((Batterio) bs[1]).disegna(g);
            }
                // Impostiamo il colore rosso (come intingere il pennello nel rosso)
            g.setColor(Color.RED);
            // Scriviamo il numero di batteri attualmente presenti
            g.drawString("Numero batteri : "+ bs.length, 10, 10);
        }

        /* Implementiamo adesso il metodo run, poichè abbiamo
         * implementato Runnable, che mette in esecuzione il thread
         */
        public void run() {
            while(true) {                           // Praticamente, all'infinito
            /* Dobbiamo gestire l'eccezione poichè essa
             * è Throwable da run. Fermiamo il thread per 200 ms, e ridisegniamo
             * lo sfondo.
             */
                try {
                    Thread.sleep(200);
                    repaint();
                }
                catch(InterruptedException ie) {
                    return;
                }
            }
        }

        /* Infine, il main. Qui creiamo l'interfaccia grafica del
         * programma inserendo un oggetto Miscroscopio (che, ricordo,
         * è anche un Canvas, quindi un Component) in un frame nella zona
         * centrale. Nella zona sud inserisco un tasto infetta che chiama
         * "creaBatterio"; inserisco infine un ascoltatore sfruttando la
         * classe Adapter
         */


    public static void main (String args[]) {
        final Microscopio m = new Microscopio();
        Frame f = new Frame("Microscopio");
        f.addWindowListener(new WindowAdapter() {
                                    public void windowClosing(WindowEvent e) {
                                        System.exit(0);
                                    }
                                }
                                );
        f.add(m,"Center");

        Button b = new Button("Infetta");
        b.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    m.creaBatterio();
                                }
                            }
                            );
        f.add(b,"South");
        f.setSize(240,240);
        f.setVisible(true);
    }
}



