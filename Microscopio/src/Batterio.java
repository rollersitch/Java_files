/*
 *      Batterio.java
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
import java.util.*;
/** Classe che rappresenta un batterio, di supporto all'applicazione
 * Microscopio, e ne gestisce il ciclo di vita di ognuno in un flusso
 * di esecuzione differente
 */
public class Batterio implements Runnable {
    /** Generatore di numeri casuali */
    private Random rng;
    /** Coordinate alle quali il batterio va disegnato */
    private int x , y;
    /** Dimensione dei cerchietti che rappr. i batteri */
    private int dim;
    /** Età del batterio */
    private int eta;
    /** Canvas in cui verranno disegnati i batteri (vedi Microscopio.java) */
    private Microscopio m;

    /** Costruttore del batterio, che imposta l'oggetto in cui va disegnato, le
     * sue coordinate, la sua età, la sua dimensione, e lo inserisce in un thread
     * apposito
     */
    public Batterio(int xIniziale,int yIniziale,Microscopio m,Random rng) {
         /* Imposta le variabili Random e Microscopio */
         this.rng = rng;
         this.m = m;
         // Inizializziamo età a 0 e dimensione a (per esempio) 10
         eta = 0;
         dim = 10;
         // Disegniamolo all'inizio nelle coordinate specificate
         x = xIniziale;
         y = yIniziale;
         // Inseriamolo in un thread
         Thread t = new Thread(this);
         t.start();
    }

     /** Metodo che disegna ogni batterio */
    /* Questo metodo disegna sul canvas ogni batterio, deve essere perciò
     * sincronizzato
     */
    public synchronized void disegna(Graphics g) {
         /* In questo modo, usando il costruttore di Color che accetta come parametri
          * tre coefficienti di "presenza" di rosso,verde e blu (notazione RGB), rendiamo
          * il colore del batterio funzione della sua età
          */
         int c = Math.min(eta,240);
         g.setColor(new Color(c,c,c));
         // Disegniamo un ovale pieno del colore precedente (un cerchio, poichè i due semiassi sono uguali)
         g.fillOval(x,y,dim,dim);
    }

    /** Metodo che gestisce tutti i movimenti dei singoli batteri */
    public synchronized void muovi() {
        // Aggiorna la posizione
        x += rng.nextInt(11) - 5;
        y += rng.nextInt(11) - 5;
        // Confina le particelle nella finestra Microscopio
        Dimension d = m.getSize();

        if (x+dim > d.width) x = d.width-dim;
        else if (x<0) x=0;

        if (y+dim > d.height) y = d.height-dim;
        else if (y<0) y=0;

        // Ciclo di crescita, la probabilità è impostata al 10%
        if (rng.nextInt(10) == 0) dim++;
        // Se la dimensione è maggiore di quella stabilita, il batterio si sdoppia in due più piccoli
        if (dim >= 15) {
            dim = 10;
            Batterio b = new Batterio(x,y,m,new Random(rng.nextLong()));
            m.aggiungiBatterio(b);
        }
    }

    /* Adesso definiamo il run di Runnable, che esegue il metodo muovi
     * un numero casuale di volte, finchè l'età non raggiunge un
     * limite stabilito, dopodichè il batterio muore
     */
    public void run() {
        while (eta<255) {
            try {
                 int i = rng.nextInt(10);
                 Thread.sleep(i*100);
                 muovi();
                 eta += i;
            }
            catch (InterruptedException ex) {
                 return;
            }
        }
    m.rimuoviBatterio(this);
    }
}



