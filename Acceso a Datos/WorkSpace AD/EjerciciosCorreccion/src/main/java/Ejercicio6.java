
import java.io.IOException;
import java.util.ArrayList;

import Ejercicios1.Persona;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ejercicio6
 */
public class Ejercicio6 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Ejercicio6() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		Persona persona = new Persona();

		persona.setNombre(request.getParameter("nombre") != null ? request.getParameter("nombre") : "");
		persona.setApellidos(request.getParameter("apellidos") != null ? request.getParameter("apellidos") : "");
		persona.setEdad(request.getParameter("edad") != null ? Integer.parseInt(request.getParameter("nombre")) : 0);
		persona.setContacto(request.getParameter("contacto") != null ? request.getParameter("nombre") : "");

		ArrayList<Persona> listPers = getServletConfig().getServletContext().getAttribute("list_pers") != null
				? (ArrayList<Persona>) getServletConfig().getServletContext().getAttribute("list_pers")
				: new ArrayList<Persona>();

		listPers.add(persona);

		getServletConfig().getServletContext().setAttribute("list_persona", listPers);
		
		
		response.setContentType("text/plain");
		response.getWriter().append("<html><body>")
		.append("<form action='Ejercicio22b' method='post'")
		.append("<input type='submit' value='Listado de personas'>")
		.append("</form></html></body>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
