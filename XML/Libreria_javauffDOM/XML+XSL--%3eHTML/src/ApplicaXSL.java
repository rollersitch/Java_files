/*
 *      ApplicaXSL.java
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
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.*;
/* Trasformazione di un documento xml in una pagina html mediante un foglio di stile xsl */

public class ApplicaXSL {

	public static void main (String args[]) {		
		TransformerFactory tf = TransformerFactory.newInstance();
		try {
			Transformer t = tf.newTransformer(new StreamSource("file.xsl"));
			t.transform(new StreamSource("file.xml"),new StreamResult(new FileOutputStream("file.html")));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
