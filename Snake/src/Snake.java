/**
 * @(#)Snake.java
 *
 *
 * @author La Porta Alfonso
 * @version 0.1 2010/12/28
 */


//Librerie
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;   //Importo tutta la libreria e quindi tutte le classi contenute

public class Snake extends JPanel implements ActionListener {

    //Costanti
    private final int LARGHEZZA         =   600;    //Ormai la maggiorparte degli schermi di qualsiasi persona sono piu grandi di questa risoluzione
    private final int ALTEZZA           =   430;
    private final int DIMENSIONE_PUNTO  =   10;
    private final int PUNTI_TOTALI      =   2580;   //E' dato da 600*430/10*10
    private final int POSIZIONI_RANDOM  =   50; //Valore puramente casuale
    private final int RITARDO           =   75; //Determina la velocita' di gioco (piu' basso = + veloce)

    //Array che contengono tutte le coordinate delle articolazioni del serpente
    private int x[] = new int[PUNTI_TOTALI];
    private int y[] = new int[PUNTI_TOTALI];

    //Variabili
    private int punti;          //Parti del corpo del serpente
    private int bersaglio_x;    //Coordinata x del bersaglio del serpente
    private int bersaglio_y;    //Coordinata y del bersaglio del serpente

    //Setto i boolean iniziali compreso anche quello di inizio gioco (in_gioco)
    private boolean sinistra = false;
    private boolean destra = true;  // il serpente parte verso destra
    private boolean su = false;
    private boolean giu = false;
    private boolean in_gioco = true;

    private Timer timer;
    //Dichiaro variabili di tipo immagine per "disegnare" il corpo_serpente , il bersaglio da colpire e
    //la testa del serpente che verra' colorata in modo diverso rispetto al corpo
    private Image corpo_serpente;
    private Image bersaglio;
    private Image testa;


    public Snake() {

        addKeyListener(new TAdapter()); //Ascoltatore dei tasti
        setBackground(Color.black);

        //Carico le immagini
        ImageIcon iic = new ImageIcon(this.getClass().getResource("parte_corpo.png"));  // iic = image icon corpo_serpente
        corpo_serpente= iic.getImage();

        ImageIcon iib = new ImageIcon(this.getClass().getResource("bersaglio.png")); //iib = image icon bersaglio
        bersaglio = iib.getImage();

        ImageIcon iit = new ImageIcon(this.getClass().getResource("testa.png")); //iit = image icon testa
        testa = iit.getImage();

        setFocusable(true); //Visibilita'

        //Si comincia a giocare
        avvio_gioco();
    }

    /**Metodo di avviamento del gioco, settando le coordinate inziali della testa del serpente
     *@see posiziona_bersaglio()*/
    public void avvio_gioco(){

            punti = 3;
            //Posizione iniziale della x
            for (short i = 0; i < punti; i++) {
                x[i] = 150 - i*10;
                y[i] = 150;
            }
            posiziona_bersaglio();

            //Attivazione del timer
            timer = new Timer(RITARDO, this);
            timer.start();
    }

       /**Metodo di controllo dell'intero gioco , per controllare se la variabile in_gioco e' ancora valida
         */
        public void actionPerformed(ActionEvent e) {

        if (in_gioco) {
            controlla_bersaglio();
            controlla_collisioni();
            muovi();
        }
        //System.out.println ("Sono nel repaint ");
        repaint();
    }

    /**Metodo di disegno ridefinito in modo specifico per il programma
     * Disegna il bersaglio da colpire e il serpente
     */
    public void paint(Graphics g) {
        super.paint(g);

        //Aumento lo spessore del bordo di gioco
        g.setColor(Color.red);
        for(short i=0; i<=5;i++)
            g.drawRect(100,100,LARGHEZZA+i,ALTEZZA+i);

        if (in_gioco){
            g.drawImage(bersaglio, bersaglio_x, bersaglio_y, this);

            for (int i = 0; i < punti; i++) {
                if (i == 0)
                    g.drawImage(testa, x[i], y[i], this);
                else g.drawImage(corpo_serpente, x[i], y[i], this);
            }
        }
        else game_over();

      }

        /**Metodo di fine gioco*/
        public void game_over() {
            JOptionPane.showMessageDialog(null,"GAME OVER","Titolo finestra mettici quello che vuoi",1);

    System.exit(0);
}

        /**Metodo d controllo della collisione fra serpente e bersaglio
         */
        public void controlla_bersaglio() {

        if ((x[0] == bersaglio_x) && (y[0] == bersaglio_y)) {
            punti++;
            posiziona_bersaglio();
        }
    }

    /**Metodo di movimento del serpente
     *Se si sposta la testa del serpente di conseguenza sis posta anche il restodel corpo
     */
    public void muovi() {

        for (int i = punti; i > 0; i--) {
            x[i] = x[(i - 1)];
            y[i] = y[(i - 1)];
        }

        if (sinistra) {
            x[0] -= DIMENSIONE_PUNTO;
        }

        if (destra) {
            x[0] += DIMENSIONE_PUNTO;
        }

        if (su) {
            y[0] -= DIMENSIONE_PUNTO;
        }

        if (giu) {
            y[0] += DIMENSIONE_PUNTO;
        }
    }
        /**Metodo che controlla se il serpente si scontra contro le pareti o contro se stesso
         */
         public void controlla_collisioni() {

          for (int i = punti; i > 0; i--) {

              if ((i > 4) && (x[0] == x[i]) && (y[0] == y[i])) {
                  in_gioco = false;
              }
          }

        if (y[0] >= ALTEZZA+85) {   //devo aggungere +85 perche' l'area di gioco comincia dal punto 100,100
            in_gioco = false;
        }

        if (y[0] <= 100) {
            in_gioco = false;
        }

        if (x[0] >= LARGHEZZA+85) {
            in_gioco = false;
        }

        if (x[0] <= 100) {
            in_gioco = false;
        }
    }

        /**Metodo per il posizionamento del bersaglio a schermo
         *Ripeti il ciclo se :
         *La x non e' maggiore di 100
         *La x e' maggiore della larghezza
         *La y non e' maggiore di 100
         *la y e' maggiore dell'altezza
        */
        public void posiziona_bersaglio() {
        do{
            int r = (int) (Math.random() * POSIZIONI_RANDOM);
            bersaglio_x = ((r * DIMENSIONE_PUNTO));
        }while(bersaglio_x<102 || bersaglio_x>LARGHEZZA);
        do{
            int r = (int) (Math.random() * POSIZIONI_RANDOM);
            bersaglio_y = ((r * DIMENSIONE_PUNTO));
        }while(bersaglio_y<102 || bersaglio_y>ALTEZZA);
        //System.out.println ("x : "+bersaglio_x+" y : "+bersaglio_y);
    }

    /**Metodo che acquisisce il tasto premuto dall'utente e si comporta di conseguenza
     */
       private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!destra)) {
                sinistra = true;
                su = false;
                giu = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!sinistra)) {
                destra = true;
                su = false;
                giu = false;
            }

            if ((key == KeyEvent.VK_UP) && (!giu)) {
                su = true;
                destra = false;
                sinistra = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!su)) {
                giu = true;
                destra = false;
                sinistra = false;
            }
        }
    }

}
