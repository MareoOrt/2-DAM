package ejercicio3;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class ServletDespachado
 */
public class ServletDespachado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ServletDespachado() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recogemos el mensaje
		String msj = ((request.getParameter("texto") == null) ? "" : request.getParameter("texto"));

		// Comporbamos que si se escribio algo
		if (msj.isEmpty()) {// Si no lo enviamos al servlet de error
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletError");
			dispatcher.forward(request, response);
		} else {
			// Imprimimos la cabecera
			RequestDispatcher dispatcherCab = request.getRequestDispatcher("/cabecera.html");
			dispatcherCab.include(request, response);

			// Respuesta
			response.setContentType("text/html");
			response.getWriter().append("<html><body>")
					.append("<h2>Has tecleado" + msj.toUpperCase() + "</h2>")
					.append("<p>Has delegado el procesamiento de la peticion al servlet" + getServletName() + "<p>")
					.append("</body></html>");
			response.getWriter().close();

			// Pie
			RequestDispatcher dispatcherPie = request.getRequestDispatcher("/cabecera.html");
			dispatcherPie.include(request, response);
		}
	}

}
