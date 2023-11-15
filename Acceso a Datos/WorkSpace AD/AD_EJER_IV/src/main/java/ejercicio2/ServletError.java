package ejercicio2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class ServletError
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

		// Recogemos el mensaje
		String mensaje = (String) getServletContext().getAttribute("mensaje");

		// Respuesta
		response.setContentType("text/html");
		response.getWriter().append("<html><body>");
		response.getWriter().append("<hr>");
		response.getWriter().append("<h2>Mensaje del servidor</h2>");
		response.getWriter().append("<hr style=\"color: red;\">");
		response.getWriter().append("<p style=\"color: red;\">" + mensaje + "</p>");
		response.getWriter().append("<a href=\"/index.hmtl\">Volver</a>");
		response.getWriter().append("</body></html>");
	}

}
