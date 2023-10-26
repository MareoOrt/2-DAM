package Ejercicios3;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class Ejercicio3_4.
 */
public class Ejercicio3_4 extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Ejercicio3_4() {
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

		// Formulario con los dos botones, acceso nueva sesion
		response.setContentType("text/html");
		response.getWriter().append("<html><body>")
				.append("<form action=\"Ejercicio3_4\" method=\"post\">\r\n"
						+ "        <input type=\"submit\" value=\"Acceso\" name=\"boton\"/>\r\n"
						+ "        <input type=\"submit\" value=\"Nueva sesion\" name=\"boton\"/>\r\n" + "    </form>")
				.append("</body>\r\n" + "</html>");

	}

	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Tipo de contenidp
		response.setContentType("texdt/plain");
		// Se crea la sesion
		HttpSession sesion = request.getSession(true);
		// Se crea contador de veces que hemos entrado
		Integer contador = 1;

		// Si el boton es el de nueva sesion
		if (request.getParameter("boton").equalsIgnoreCase("Nueva sesion")) {

			// Se incvalida la sesion actual
			sesion.invalidate();
			// Se crea una nueva sesion
			sesion = request.getSession(true);
			response.getWriter().append("Es la primera vez que se accede a la web.");

			// Si no es nueva sesion es accede por lo que ...
		} else {

			// Si el atributo contador esta creado, no es null, se da el valor del atributp
			// sino, se le da valor 0
			contador = (Integer) (sesion.getAttribute("contador") != null ? (Integer) (sesion.getAttribute("contador"))
					: 1);

			// Si el contador no es 0 se le pone las veces que se entro
			if (contador != 0) {
				response.getWriter().append("Se ha accedido ya unas " + contador + "veces");
			} else {
				response.getWriter().append("Es la primera vez que se accede a la web.");
			}

		}

		// Metemos el atributo contador
		sesion.setAttribute("contador", contador + 1);

		response.getWriter().close();
	}

}
