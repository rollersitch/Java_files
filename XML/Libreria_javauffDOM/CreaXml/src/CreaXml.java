/*
 *      CreaXml.java
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
import org.w3c.dom.*;
import org.w3c.dom.ls.*;
import javax.xml.parsers.*;
import java.util.*;

/* In questa classe sfruttiamo l'interfaccia DOM, che si appoggia
 * sulla SAX ma permette operazioni avanzate come la creazione di un file xml e la trasformazione di
 * esso attraverso un foglio di stile xsl (per esempio per portare tutto in html). Ovviamente è
 * sempre possibile creare un parser DOM, cambiano i nomi dei metodi ma il succo è lo stesso di SAX; tuttavia
 * DOM mantiene in memoria i vari eventi che incontra, quindi permette una navigazione completa del documento
 * e una gestione più particolarizzata; SAX invece esegue un parsing "on the fly".
 */ 
public class CreaXml {
	// Il documento principale
	private Document documento;

	public CreaXml() {
		/* Creo una factory, da essa un costruttore di documenti, e da esso un documento */
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = dbf.newDocumentBuilder();
			documento = builder.newDocument();
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		// Definisco l'elemento radice, unico in XML
		Element radice = documento.createElement("libri");
		documento.appendChild(radice);

		/* Adesso implementeremo un sistema di richieste all'utente con un oggetto Scanner, per impostare
		 * il testo contenuto nei tag, con il metodo lettura(). Il metodo creaLibro() imposta i tag da noi definiti,
		 * nell'esempio dei tag per una serie di dati su dei libri. Insomma il documento verrà creato coi tag impostati
		 * da creaLibro(),inserendo fra i tag le sezioni indicate dall'utente, stamperà nello stdout il documento (senza
		 * indentazioni) e lo scriverà in un file.
		 */

		do {
			Node libro = creaLibro(documento);
			radice.appendChild(libro);
		} while (lettura("Altro libro?(S/N)").equalsIgnoreCase("s"));

		DOMImplementation impl = documento.getImplementation();
		DOMImplementationLS implLS = (DOMImplementationLS)impl.getFeature("LS","3.0");
		LSSerializer ser = implLS.createLSSerializer();
		String output = ser.writeToString(documento);
		System.out.println(output);

		// Scrittura nel file

		try {
			PrintStream file = new PrintStream(new FileOutputStream(lettura("Nome del file xml")));
			file.print(output);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Node creaLibro(Document documento) {
		Element libro = documento.createElement("libro");
		Attr isbn = documento.createAttribute("isbn");
		isbn.setValue(lettura("ISBN"));
		libro.setAttributeNode(isbn);


		Element titolo = documento.createElement("titolo");
		titolo.appendChild(documento.createTextNode(lettura("Titolo: ")));
		libro.appendChild(titolo);

		Element autore = documento.createElement("autore");
		autore.appendChild(documento.createTextNode(lettura("Autore:")));
		libro.appendChild(autore);


		Element edizione = documento.createElement("edizione");
		Element editore = documento.createElement("editore");
		editore.appendChild(documento.createTextNode(lettura("Editore:")));
		edizione.appendChild(editore);

		Element anno = documento.createElement("anno");
		anno.appendChild(documento.createTextNode(lettura("Anno di edizione:")));
		edizione.appendChild(anno);

		libro.appendChild(edizione);


		Element prezzo = documento.createElement("prezzo");
		prezzo.appendChild(documento.createTextNode(lettura("Prezzo:")));
		libro.appendChild(prezzo);


		return libro;
	}


	private String lettura(String domanda) {
		Scanner tastiera = new Scanner(System.in);
		System.out.println(domanda);
		return tastiera.nextLine();
	}
}
