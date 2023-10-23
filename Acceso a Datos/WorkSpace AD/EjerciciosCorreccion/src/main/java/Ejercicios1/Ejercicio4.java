package Ejercicios1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ejercicio4
 */
public class Ejercicio4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Ejercicio4() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Enumeration<String> params;
		ArrayList<String> parametros = new ArrayList<String>();
		ArrayList<String> valores = new ArrayList<String>();
		params = request.getParameterNames();
		while(params.hasMoreElements()) {
			String param = params.nextElement();
			parametros.add(param);
			valores.add(request.getParameter(param));
		}
		
		response.setContentType("text/plain");
		
		response.getWriter()
		.append("<HTML><BODY>")
		.append("<table>")
		.append("<tr>");
		for(String p : parametros) {
			response.getWriter()
			.append("<td>"+ p +"</td>");
		}

		response.getWriter()
		.append("</tr><tr>");
				for(String v : valores) {
					response.getWriter()
					.append("<td>"+ v +"</td>");
				}
				response.getWriter().append("</tr>")
		
		response.getWriter()
		.append("</table>")
		.append("</BODY></HTML>");
	}

}
