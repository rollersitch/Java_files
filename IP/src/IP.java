/*
*      IP.java
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
import java.net.*;
/** Classe per l'acquisizione di dati di rete della stazione locale su cui è
* eseguita.
* @author Daniele Pipitone
* @version 25/Apr/2010 0.0
* @see InetAddress
*/
public class IP {
    /** Il main assume il nome e l'indirizzo ip della macchina su cui
    * è eseguito.
    */
    public static void main (String args[]) {
        try {
            /* Stampiamo il nome della macchina locale col metodo statico
            * getHostName della classe InetAddress, il quale restituisce
            * un oggetto di tipo InetAddress, cioè un indirizzo IP.
            * Dopodichè i metodi getHostName e getHostAddress restituiscono
            * delle stringhe che contengono nome e indirizzo.
            */
            System.out.println(InetAddress.getLocalHost().getHostName());
            // Se la macchina è dietro firewall viene preso l'indirizzo di loopback
            System.out.println(InetAddress.getLocalHost().getHostAddress());
        }
        catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
    } // main
} // class