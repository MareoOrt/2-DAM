package ejercicio2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class ServletTablas
 */
public class ServletTablas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ServletTablas() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recogemos el color y el numero
		int numero = Integer.parseInt(request.getParameter("numero"));
		String color = request.getParameter("color");

		// Configuramos para html
		response.setContentType("text/html");

		// Separamos el style del color, si el color ees negro ponemos la letra en
		// blanco y sino la ponemo en negro
		String estiloColor = "style='background-color:" + color + "; color:"
				+ (color.equals("black") ? "white" : "black") + "'";

		// Iniciamos html
		response.getWriter().append("<html><body>");
		response.getWriter().append("<hr>");
		response.getWriter().append("<h2>Tabla de multiplicar del " + numero + "</h2>");
		response.getWriter().append("<hr>");
		response.getWriter().append("<table>");
		// Imprimimos la tabla con un for
		for (int i = 1; i <= 10; i++) {
			response.getWriter().append(
					"<tr " + estiloColor + "><td>" + numero + " x " + i + "</td><td>" + (numero * i) + "</td></tr>");
		}
		response.getWriter().append("</table>");
		response.getWriter().append("<hr>");
		response.getWriter().append("<a href=\"/index.hmtl\">Volver</a>");
		response.getWriter().append("</body></html>");

	}

}
