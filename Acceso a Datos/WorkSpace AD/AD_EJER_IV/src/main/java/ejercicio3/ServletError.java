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
 * Servlet implementation class ServletErr
 */
public class ServletError extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ServletError() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Respuesta
		response.setContentType("text/html");
		response.getWriter().append("<html><body>")
				.append("<hr>")
				.append("<h2>Mensaje del servidor</h2>")
				.append("<hr>")
				.append("<p style=\"color: red;\">No has introducido ningun texto</p>")
				.append("<a href=\"/index.hmtl\">Volver</a>")
				.append("</body></html>");
		response.getWriter().close();
	}

}
