package Ejercicios1;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Ejercicio22b
 */
public class Ejercicio22b extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Ejercicio22b() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		ArrayList<Persona> listPers = getServletConfig().getServletContext().getAttribute("list_pers") != null
				? (ArrayList<Persona>) getServletConfig().getServletContext().getAttribute("list_pers")
				: new ArrayList<Persona>();

		response.setContentType("text/plain");
		response.getWriter().append("<html><body>")
		.append("<table border='1'>")
		.append("<tr>"
				+ "<td>Nombre</td>"
				+ "<td>Apellidos</td>"
				+ "<td>Edad</td>"
				+ "<td>Contacto</td>"
				+ "</tr>");
		for (Persona persona : listPers) {
			response.getWriter().append("<tr>"
					+ "<td>"+ persona.getNombre() +"</td>"
					+ "<td>"+ persona.getApellidos() +"</td>"
					+ "<td>"+ persona.getEdad() +"</td>"
					+ "<td>"+ persona.getContacto() +"</td>"
					+ "</tr>");
		}
		response.getWriter().append("</table></html></body>");
		
	}

}
