import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.*;

public class AnalisiSAX extends DefaultHandler {
    public static int i = 1;

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






        /* if(attributi.getLength() > 0) {
            System.out.println("\tAttributi");
            for(int i=0; i < attributi.getLength(); i++) {
                System.out.println(attributi.getQName(i) + " = " + attributi.getValue(i) + " ");
            }
            System.out.println();
        }*/
    }

    public void endElement(String namespaceURI,String localName,String elemento) throws SAXException {

    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (length>0) {
            String testo = new String(ch,start,length);
            if(!testo.trim().equals("")) {
                if(i==1) {
                    System.out.println("\tCodice: " + testo.trim());
                    i++;
                    return;
                }
                if(i==2) {
                    System.out.print("\tComune:" + testo.trim());
                    i=1;
                }
            }
        }
    }
}
