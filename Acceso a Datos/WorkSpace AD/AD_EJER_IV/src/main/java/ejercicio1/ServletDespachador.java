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
 * Servlet implementation class ServletDespachador
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

		// Iniciamos
		response.setContentType("text/html");
		response.getWriter().append("<html><body>");

		// Recogemos el nombre y la contraseña
		String nombre = request.getParameter("nombre");
		String contra = request.getParameter("contra");

		// Comporbamos si puso admin en la contraseña
		if (contra.equals("admin")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletBienvenida");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletError");
			dispatcher.forward(request, response);
		}

		// El link de vuelta
		response.getWriter().append("<a href='/index.html'>Volver</a>");

		// Cerramos y terminamos
		response.getWriter().append("</body></html>")
				.close();
	}

}
