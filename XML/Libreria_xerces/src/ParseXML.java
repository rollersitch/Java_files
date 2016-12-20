/*
 *      ParseXML.java
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

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.apache.xerces.parsers.*;
/** Questa classe è una factory di un parser xml validante; essa va richiamata da linea di comando
 * con la sintassi  <p><strong>java ParseXML [-nomefile.xml] [-schema]</strong></p>. L'opzione "-schema"
 * può essere omessa; in tal caso il parser effettuerà solo un controllo di documento well-formed. Questa classe
 * è stata creata con la libreria xerces.jar di Apache. Per informazioni su xml visitare <a href="http://www.html.it">questo sito</a>.
 * 
 * @author Daniele Pipitone
 * @version 1.0 26/05/2010
 */ 
public class ParseXML {
	public static void main(String[] args) throws Exception {
		System.out.println("Parsing: " + args[0]);
		SAXParser parser = new SAXParser();


		if (args.length>1 && args[1].compareTo("-schema") == 0) {
			parser.setFeature("http://xml.org/sax/features/validation",true);
		}
		parser.setErrorHandler(new Handler());
		parser.parse(args[0]);

	}
}

class Handler extends DefaultHandler {
	boolean _error = false;


	public void endDocument() throws SAXException {
		if (!_error) {
			System.out.println("Parsing OK!");
		}
	}


	public void error(SAXParseException exc) {
		System.out.println("Error [line " + exc.getLineNumber() + "]: " + exc.getMessage() + "/n");
		_error = true;
	}


	public void warning(SAXParseException exc) {
		System.out.println("Warning [line " + exc.getLineNumber() + "]: " + exc.getMessage() + "/n");
	}
} 
		
