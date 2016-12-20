/*
 *      PrimoServlet.java
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
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/** Servlet di prova **/
public class PrimoServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Servlet</title></head>");
		out.println("<body>");
		out.println("<h1>Servlet</h1>");
		out.println("<p>Output del Servlet</p>");
		String nome = request.getParameter("nome");
		out.println("<p>Ciao " + nome + "</p>");
		out.println("</body></html>");
		out.close();
	}
}
