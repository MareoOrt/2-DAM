package ejercicio1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class ServletBienvenida
 */
public class ServletBienvenida extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ServletBienvenida() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recogemos el nombre
		String nombre = ((request.getParameter("nombre") == null) ? "sin nombre" : request.getParameter("nombre"));
		// Respuesta
		response.setContentType("text/html");
		response.getWriter().append("<html><body>")
				.append("<h2>Bienvenido al Minicurso Java sr@ " + nombre + "</h2>")
				.append("</body></html>")
				.close();
	}

}
