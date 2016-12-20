/*
 *      AnalisiSAX2.java
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
import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.*;

/* Analisi di un documento xml con un parser validante attraverso il modello event-driven di SAX */

public class AnalisiSAX2 extends DefaultHandler {
	public AnalisiSAX2(String file) {
		try {
			// Istanziamo la factory
			SAXParserFactory spf = SAXParserFactory.newInstance();
			// Rendiamo i parser prodotti validanti
			spf.setValidating(true);

			// Creiamo un parser
			SAXParser saxParser = spf.newSAXParser();

			// Effettuiamo il parsing del file passato al costruttore; impostiamo la classe stessa come listener
			saxParser.parse(new File(file),this);

		}

		catch (SAXParseException e) {
			System.out.println(e.getMessage());
		}

		catch (FileNotFoundException e) {
			System.out.println(e);
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	/* Override dei metodi di callback chiamati da SAX */

	public void startElement(String namespaceURI,String localName,String elemento,Attributes attributi)
	throws SAXException {
		System.out.println("Elemento: - " + elemento);
		if (attributi.getLength() > 0) {
			System.out.print("\tAttributi: ");
			for (int i=0; i < attributi.getLength(); i++) {
				System.out.print(attributi.getQName(i) + " = " + attributi.getValue(i) + " ");
			}
			System.out.println();
		}
	}




	public void endElement(String namespaceURI,String localname,String elemento)
	throws SAXException {
		System.out.println("Fine elemento: " + elemento);
	}



	public void characters(char[] ch,int start,int length) throws SAXException {
		if (length>0) {
			String testo = new String(ch,start,length);
			if (! testo.trim().equals("")) {
				System.out.println("\tTesto: " + testo.trim());
			}
		}
	}



	/* Metodi di callback per gli errori e i warning */

	public void fatalError(SAXParseException e) throws SAXException {
		System.out.println(e);
	}

	public void error(SAXParseException e) throws SAXException {
		System.out.println(e);
	}

	public void warning(SAXParseException e) throws SAXException {
		System.out.println(e);
	}
}
