package ejercicio2;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class Ejercicio2
 */
public class ServletDespachador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ServletDespachador() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Cogemos el numero y el color
		String numero = request.getParameter("numero");
		String color = request.getParameter("color");

		// Comprobamos si se elgigio un numero,
		if (numero != null && !numero.isEmpty()) { // Si noes nulo ni vacio llamamos al ServletTablas
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletTablas");
			dispatcher.forward(request, response);
		} else {// Y sino pasamos el mensaje y llamamos al ServletError
			getServletContext().setAttribute("mensaje", "No has elegido una tabla. Elige una por favor.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletError");
			dispatcher.forward(request, response);
		}
	}

}
