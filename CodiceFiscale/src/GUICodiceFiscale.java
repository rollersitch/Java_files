/*
 *      GUICodiceFiscale.java
 *
 *      Copyright 2010 daniele <dany88vai@gmail.com>
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



/*                                             CodiceFiscale.java
 *                         Questo programma presenta una semplice GUI per calcolare il codice fiscale
 *                         italiano a partire dai dati anagrafici inseriti.
 *                                                                                                          */

/** Calcolo del codice fiscale. Classe di interfaccia grafica.
 * @author Daniele Pipitone
 * @version 15 Ott 2010 1.0
 */
package CodFis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
/*import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.*;*/





public class GUICodiceFiscale extends JFrame {


                                    /* COSTRUZIONE DELL'INTERFACCIA GRAFICA */



    // Dichiarazioni elementi di primo livello
    private Container contentPane;
    private JPanel headPane;
    private JPanel appPane;
    private  JPanel resultPane;
    private JLabel headLbl;
    private JLabel resultLbl;
    // Risultato finale
    private String finRes;

    // Dichiarazioni elementi di 2 livello, "listened"
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField dayField;
    private JTextField monthField;
    private JTextField yearField;
    private JTextField comuneField;
    private JRadioButton male;
    private JRadioButton female;
    private JButton calc;
    private JMenuItem info;
    private JMenuItem instr;
    private JMenuItem close;

    public GUICodiceFiscale() {
        /* Impostazioni generali della finestra */
        setSize(600,400);
        setTitle("CODICE FISCALE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        // Definizione dei componenti

        JLabel headLbl = new JLabel("Inserisci i tuoi dati nei campi predisposti e premi il tasto Calcola!",SwingConstants.LEFT);
        final JLabel resultLbl = new JLabel();

        JPanel headPane = new JPanel();
        headPane.setBorder(new TitledBorder(new EtchedBorder(),"Introduzione"));
        JPanel appPane = new JPanel();
        appPane.setLayout(new GridLayout(5,1));
        appPane.setBorder(new TitledBorder(new EtchedBorder(),""));
        JPanel resultPane = new JPanel();
        resultPane.setBorder(new TitledBorder(new LineBorder(Color.red,5),"Risultato"));

        headPane.add(headLbl,"Center");
        resultPane.add(resultLbl,"Center");

                                // Costruzione dell'appPane

        /* Pannello nome e cognome */

        JPanel pannAnag = new JPanel();
        pannAnag.setBorder(new TitledBorder(new EtchedBorder(),"Anagrafici"));
        pannAnag.setLayout(new GridLayout(1,4));

        JLabel lbl1 = new JLabel("Nome",SwingConstants.CENTER);
        nameField = new JTextField();
        JLabel lbl2 = new JLabel("Cognome",SwingConstants.CENTER);
        surnameField = new JTextField();

        pannAnag.add(lbl1);
        pannAnag.add(nameField);
        pannAnag.add(lbl2);
        pannAnag.add(surnameField);


        /* Pannello data di nascita */

        JPanel pannBirth = new JPanel();
        pannBirth.setBorder(new TitledBorder(new EtchedBorder(),"Data di nascita"));
        pannBirth.setLayout(new GridLayout(1,6));

        JLabel lbl3 = new JLabel("Giorno");
        dayField = new JTextField();
        JLabel lbl4 = new JLabel("Mese",SwingConstants.CENTER);
        monthField = new JTextField();
        JLabel lbl5 = new JLabel("Anno",SwingConstants.CENTER);
        yearField = new JTextField();

        pannBirth.add(lbl3);
        pannBirth.add(dayField);
        pannBirth.add(lbl4);
        pannBirth.add(monthField);
        pannBirth.add(lbl5);
        pannBirth.add(yearField);


        /* Pannello Comune */
        JPanel pannComune = new JPanel();
        pannComune.setBorder(new TitledBorder(new EtchedBorder(),"Comune di nascita"));
        pannComune.setLayout(new GridLayout(1,2));

        JLabel lbl6 = new JLabel("Comune",SwingConstants.RIGHT);
        comuneField = new JTextField();

        pannComune.add(lbl6);
        pannComune.add(comuneField);


        /* Pannello Sesso */
        JPanel pannGender = new JPanel();
        pannGender.setBorder(new TitledBorder(new EtchedBorder(),"Genere"));
        pannGender.setLayout(new GridLayout(1,3));

        JLabel lbl7 = new JLabel("Sesso",SwingConstants.LEFT);
        male = new JRadioButton("Maschile");
        female = new JRadioButton("Femminile");
        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);

        pannGender.add(lbl7);
        pannGender.add(male);
        pannGender.add(female);


        /* Pannello Pulsante Calcola */

        JPanel pannButt = new JPanel();
        pannButt.setBorder(new EtchedBorder());
        calc = new JButton("Calcola!");
        ActionListener btnList = new ActionListener() {
                                            public void actionPerformed(ActionEvent event) {
                                                if (event.getSource() instanceof JButton) {
                                                    if ((event.getActionCommand()).equals("Calcola!")) {
                                                        // System.out.println("gestore chiamato");
                                                        String finRes = calcCod();
                                                        // System.out.println(finRes);
                                                        // System.out.println(resultLbl);
                                                        resultLbl.setText(finRes);

                                                    }
                                                }
                                            }
        };
        calc.addActionListener(btnList);
                                                            ;

        pannButt.add(calc);






        /* Aggiungiamo i componenti dell'appPane */
        appPane.add(pannAnag);
        appPane.add(pannBirth);
        appPane.add(pannComune);
        appPane.add(pannGender);
        appPane.add(pannButt);



        /* Preparazione del Menu */
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");                             /* TO-DO:  Aggiungere Voci al menu  */
        JMenu infMenu = new JMenu("?");

        JMenuItem close = new JMenuItem("Chiudi");
        fileMenu.add(close);

        JMenuItem info = new JMenuItem("Informazioni");
        JMenuItem instr = new JMenuItem("Istruzioni");
        infMenu.add(info);
        infMenu.add(instr);

        menuBar.add(fileMenu);
        menuBar.add(infMenu);
        // Ascoltatore del menu
        MenuListener menuListener = new MenuListener();
        close.addActionListener(menuListener);
        info.addActionListener(menuListener);
        instr.addActionListener(menuListener);

        /* Aggiungiamo i tre pannelli principali alla finestra */

        contentPane.add(headPane,"North");
        contentPane.add(appPane,"Center");
        contentPane.add(resultPane,"South");

        setVisible(true);
        setJMenuBar(menuBar);
        setIconImage(new ImageIcon("CodFis.jpeg").getImage());
    }

    /*                                  Ascoltatore per le Voci di Menu                              */

    class MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();
            if(command.equals("Chiudi")) { System.exit(0); }
            if(command.equals("Istruzioni")) {
                JOptionPane.showMessageDialog(null,"Nomi e cognomi doppi vanno spaziati.\nLa data di nascita va" +
                                            " inserita con valori numerici (eccetto il mese, che accetta sia parole che formato numero) specificando lo zero iniziale se dovuto.\n" +
                                            "Per cognomi doppi si intende *DUE* cognomi, non cognomi come *De Vita* " +
                                            "i quali invece vanno scritti senza spazi.\n" +
                                            "Il nome del comune può mantenere gli spazi se presenti.","Istruzioni",
                                            JOptionPane.INFORMATION_MESSAGE);
            }
            if(command.equals("Informazioni")) {
                JOptionPane.showMessageDialog(null,"CodiceFiscale versione 1.0\n" +
                                              "Piccolo esercizio di programmazione :-)\n" + "\n" +
                                              "Autore: Daniele Pipitone   -RollerBitch-","Informazioni",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }


    /* PASSIAMO I PARAMETRI INPUT DELLA GUI AL "BUSINESS"   */

    public String calcCod() {
        // TO-DO Controllo input    boolean inputOk = false;
        if (  nameField.getText().equals("") || surnameField.getText().equals("") || dayField.getText().equals("") ||
             monthField.getText().equals("") || yearField.getText().equals("") || comuneField.getText().equals("")  )
        {
                JOptionPane.showMessageDialog(null, "Manca uno o più parametri fondamentali!!","ERRORE",JOptionPane.WARNING_MESSAGE);
        }


        if (!(male.isSelected()) && !(female.isSelected()) )
        {
            JOptionPane.showMessageDialog(null,"Manca l'indicazione del sesso","ERRORE",JOptionPane.WARNING_MESSAGE);
        }








        String finRes = createCod(nameField.getText(),surnameField.getText(),dayField.getText(),
                                                  monthField.getText(),yearField.getText(),comuneField.getText(),
                                                  male.isSelected(),female.isSelected());


        return finRes;
    }


    /*
     *  METODO PRINCIPALE:  Generazione del codice fiscale
     */
    public String createCod(String name,String surname,String day,String month,String year,String comune,boolean male,boolean female) {
        String codNome = "";
        String codCognome = "";
        String codGiorno= "";
        String codAnno = "";
        String codMese = "";
        String codComune = "";
        String codControllo = "";


        String CodFisFinale = "";

        // trattazione del nome
        name = name.toLowerCase();
        name = name.trim();
        StringTokenizer tokenizer = new StringTokenizer(name);
        int numTok = tokenizer.countTokens();
        Vector<Character> consArray = new Vector<Character>();
        Vector<Character> vocalArray = new Vector<Character>();
        for(int j=0; j < numTok; j++) {
            String token = tokenizer.nextToken();
            char[] intArray = token.toCharArray();
            for(int i=0; i < intArray.length;i++) {
                if(!(isVocal(intArray[i]))) {
                    consArray.add(intArray[i]);

                }
                else {
                    vocalArray.add(intArray[i]);
                }
            }
        }
        //System.out.println(consArray);
        //System.out.println(consArray.length);

        //System.out.println(vocalArray);


        if (consArray.size() == 1) {
            char[] correct = {consArray.get(0),vocalArray.get(0),vocalArray.get(1)};
            codNome = new String(correct);
        }
        if (consArray.size() == 2) {
            char[] correct = {consArray.get(0),consArray.get(1),vocalArray.get(0)};
            codNome = new String(correct);
        }
        if (consArray.size() == 3) {
            char[] correct = {consArray.get(0),consArray.get(1),consArray.get(2)};
            codNome = new String(correct);
        }
        if (consArray.size() > 3) {
            char[] correct = {consArray.get(0),consArray.get(2),consArray.get(3)};
            codNome = new String(correct);
        }



        // "" cognome


        surname = surname.toLowerCase();
        surname = surname.trim();
        StringTokenizer tokenizer2 = new StringTokenizer(surname);
        int numTok2 = tokenizer2.countTokens();
        Vector<Character> consArray2 = new Vector<Character>();
        Vector<Character> vocalArray2 = new Vector<Character>();
        for(int j=0; j < numTok2; j++) {
            String token = tokenizer2.nextToken();
            char[] intArray2 = token.toCharArray();
            for(int i=0; i < intArray2.length;i++) {
                if(!(isVocal(intArray2[i]))) {
                    consArray2.add(intArray2[i]);

                }
                else {
                    vocalArray2.add(intArray2[i]);
                }
            }
        }


        if (consArray2.size() == 1) {
            char[] correct = {consArray2.get(0),vocalArray2.get(0),vocalArray2.get(1)};
            codCognome = new String(correct);
        }
        if (consArray2.size() == 2) {
            char[] correct = {consArray2.get(0),consArray2.get(1),vocalArray2.get(0)};
            codCognome = new String(correct);
        }
        if (consArray2.size() == 3) {
            char[] correct = {consArray2.get(0),consArray2.get(1),consArray2.get(2)};
            codCognome = new String(correct);
        }
        if (consArray2.size() > 3) {
            char[] correct = {consArray2.get(0),consArray2.get(1),consArray2.get(2)};
            codCognome = new String(correct);
        }

        // "" giorno
        if(male){
            codGiorno = day.trim();
        }
        else {
            int giornoFemm = Integer.parseInt(day.trim()) + 40;
            codGiorno = giornoFemm + "";
        }
        // "" mese
        String[] tabella = {
                                "gennaio","A",
                                "febbraio","B",
                                "marzo","C",
                                "aprile","D",
                                "maggio","E",
                                "giugno","H",
                                "luglio","L",
                                "agosto","M",
                                "settembre","P",
                                "ottobre","R",
                                "novembre","S",
                                "dicembre","T"
                            };
        month = month.toLowerCase();
        month = month.trim();
        // Permettiamo l'uso dei numeri per indicare il mese
        if (month.equals("01")) month = "gennaio";  if (month.equals("02")) month = "febbraio";   if (month.equals("03")) month = "marzo";
        if (month.equals("04")) month = "aprile";  if (month.equals("05")) month = "maggio";   if (month.equals("06")) month = "giugno";
        if (month.equals("07")) month = "luglio";  if (month.equals("08")) month = "agosto";   if (month.equals("09")) month = "settembre";
        if (month.equals("10")) month = "ottobre";  if (month.equals("11")) month = "novembre";   if (month.equals("12")) month = "dicembre";
        for(int i=0; i < tabella.length; i += 2) {
            if (month.equalsIgnoreCase(tabella[i])) {
                codMese = tabella[i+1];
            }
        }

        // "" anno
        year = year.trim();
        codAnno = year.substring(year.length() - 2,year.length());


        // "" comune
       /* class AnalisiSAX extends DefaultHandler {

            public AnalisiSAX(String file) {
                try {
                    SAXParserFactory spf = SAXParserFactory.newInstance();
                    SAXParser saxParser = spf.newSAXParser();
                    saxParser.parse(new File(file),this);
                }
                catch(SAXParseException e) {
                    System.out.println("Errore di parsing : " + e.getMessage());
                    System.exit(1);
                }
                catch(FileNotFoundException exc) {
                    System.out.println("File " + file + " non trovato.");
                    System.exit(1);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }


            public void startElement(String namespaceURI,String localName,String elemento,Attributes attributi)
            throws SAXException {

                    System.out.println("Elemento:" + elemento);






            /* if(attributi.getLength() > 0) {
                System.out.println("\tAttributi");
                for(int i=0; i < attributi.getLength(); i++) {
                    System.out.println(attributi.getQName(i) + " = " + attributi.getValue(i) + " ");
                }
                System.out.println();
            }
            }

            public void endElement(String namespaceURI,String localName,String elemento) throws SAXException {
                System.out.println("*************************************************************");
            }

            public void characters(char[] ch, int start, int length) throws SAXException {
                if (length>0) {
                    String testo = new String(ch,start,length);
                    if(!testo.trim().equals("")) {
                        String temp = testo;
                            if(testo.trim().toUpperCase() == comune) {
                                System.out.println("\tTesto: " + testo.trim());
                                codComune = temp;
                            }
                    }
                }
            }
        }*/

        /*
         * Codice Temporaneo
         *
         */

        if ((comune.trim()).equalsIgnoreCase("iseo"))       codComune = "E333";
        if ((comune.trim()).equalsIgnoreCase("marsala"))       codComune = "E974";
        if ((comune.trim()).equalsIgnoreCase("trapani"))       codComune = "L331";
        if ((comune.trim()).equalsIgnoreCase("erice"))       codComune = "D423";
        if ((comune.trim()).equalsIgnoreCase("palermo"))       codComune = "G273";










                                /* TO-DO
                                *                               QUESTO è IL PEZZO CHE TRATTA I CODICI COMUNE
                                *
                                *
                                */


        // codice controllo
         CodFisFinale = (codCognome + codNome + codAnno + codMese + codGiorno + codComune).toUpperCase();


        String[] pari1 = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        String[] pari2 = {"0","1","2","3","4","5","6","7","8","9","0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25"};

        // è identico a pari1, solo per chiarezza
        String[] dispari1 =  {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        String[] dispari2 = {"1","0","5","7","9","13","15","17","19","21","1","0","5","7","9","13","15","17","19","21","2","4","18","20","11","3","6","8","12","14","16","10","22","25","24","23"};

        Vector<Integer> valoriPari = new Vector<Integer>();
        Vector<Integer> valoriDispari = new Vector<Integer>();

        for (int i=0; i<CodFisFinale.length(); i++) {
            /* if (i == 0) {
                System.out.println("if dello zero chiamato");
                System.out.println(CodFisFinale.charAt(0) + " lettera al primo posto");
                for(int j=0; j<dispari2.length; j++) {
                    if ((CodFisFinale.charAt(i) + "").equalsIgnoreCase(dispari1[j])) {
                        System.out.println(dispari2[j] + " valore sostituito alla lettera di primo posto");
                        valoriDispari.add(Integer.valueOf(dispari2[j]));
                        // System.out.println(valoriDispari.get(j) + "valore temporaneo per il primo");
                    }
                }
            }*/
            if (i%2 != 0) {
                System.out.println("if dei pari chiamato");
                System.out.println(CodFisFinale.charAt(i) + " lettera al " + i + " posto");
                for (int j=0; j<pari2.length; j++) {
                    if ((CodFisFinale.charAt(i) + "").equalsIgnoreCase(pari1[j])) {
                        System.out.println(pari2[j] + " valore sostituito al " + i + " posto");
                        valoriPari.add(Integer.valueOf(pari2[j]));
                         //System.out.println(valoriPari.get(j));
                    }
                }
            }
            if (i%2 == 0) {
                //System.out.println("if dei dispari chiamato");
                //System.out.println(CodFisFinale.charAt(i) + " lettera al " + i + " posto");
                for(int j=0; j<dispari2.length; j++) {
                    if ((CodFisFinale.charAt(i) + "").equalsIgnoreCase(dispari1[j])) {
                        //System.out.println(dispari2[j] + " valore sostituito al " + i + " posto");
                        valoriDispari.add(Integer.valueOf(dispari2[j]));
                        // System.out.println(valoriDispari.get(j) + "valore temporaneo dispari");
                    }
                }

            }

        }


        int sommaValoriPari = 0;
        for(int i=0; i<valoriPari.size();i++) {
            sommaValoriPari += valoriPari.get(i);
        }
        System.out.println(sommaValoriPari + "somma valori pari");

        int sommaValoriDispari = 0;
        for (int i=0; i<valoriDispari.size(); i++) {
            sommaValoriDispari += valoriDispari.get(i);
        }
        System.out.println(sommaValoriDispari + "somma valori dispari");


        int NumCodControllo = (sommaValoriPari + sommaValoriDispari) % 26;
        String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        int index = 0;
        while (index <= 25) {
            if(index == NumCodControllo) {
                codControllo = alphabet[index];
            }
            index++;
        }





        /* Ogni tratt. aggiunge un termine alla CodFisFinale */
        CodFisFinale = (codCognome + codNome + codAnno + codMese + codGiorno + codComune + codControllo).toUpperCase();
        return CodFisFinale;
    }

    public boolean isVocal(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            return true;
        }
        else return false;
    }


    public static void main(String[] args) {
        GUICodiceFiscale app = new GUICodiceFiscale();
    }





}



