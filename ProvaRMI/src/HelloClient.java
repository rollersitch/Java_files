/*
 *      HelloClient.java
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
package provarmi.prova;


import java.rmi.*;

public class HelloClient {

	public static void main (String args[]) {
		System.out.println("Connection to server......");
		String serverName = args[0];
		RemoteHello hello = null;
		try {
			System.setSecurityManager(new RMISecurityManager());
			hello = (RemoteHello) Naming.lookup("rmi://" + serverName + "/hello");
		}
		catch (Exception e) {
			System.out.println("Cannot find server......");
			System.exit(1);
		}
		
		
	}
}
