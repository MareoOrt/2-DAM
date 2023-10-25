package Ejercicios3;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Ejericico3_2
 */
public class Ejericico3_2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Ejericico3_2() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Iniciammos con texto plano
		response.setContentType("text/plain");
		// Creamos la sesion si no existe
		HttpSession sesion = request.getSession(true);
		// Si la lista como atributo de la sesion no esta creada se introduce una nueva
		// en la variable
		ArrayList<String> listUser = (ArrayList<String>) (sesion.getAttribute("list") != null
				? sesion.getAttribute("list")
				: new ArrayList<String>());
		// Se recoge el parametro usuario
		String user = request.getParameter("usuario");

		// Si el usuario a escrito el usuario
		if (user != null && user.isEmpty()) {
			// Ponemos el saludo
			response.getWriter().append("Hola" + user + "\n");
			// Lo añadimos a la lista
			listUser.add(user);
			// Metemos la linea
			sesion.setAttribute("list", listUser);
		}

		// Respuesta
		response.getWriter().append("Bienvenido \n").append("Contigo hoy me han visitado: \n ");
		// Bucle for para poner todos los nombres
		for (String u : listUser) {
			response.getWriter().append(u + "\n");
		}

	}

}
