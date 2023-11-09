package ejercicio3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import javax.print.attribute.standard.DateTimeAtCompleted;

/**
 * Servlet implementation class Ejercicio3
 */
public class Ejercicio3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Object lock = new Object();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Ejercicio3() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Do get.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Sincronizacion para solucionar problemas de concurrencia
		synchronized (lock) {

			// Recoger datos
			HttpSession sesion = request.getSession();
			String us = ((sesion.getAttribute("us") == null) ? (String) sesion.getAttribute("us") : "");

			// Mostrar
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm");
			response.getWriter().append("<html><body><table>")
					.append("<tr><td>Usuario: " + us + "</td><td>Id: " + sesion.getId() + "</td></tr>")
					.append("<tr><td rowspan='2'>Fecha creacion sesión: " + sdf.format(sesion.getCreationTime())
							+ "</td></tr>")
					.append("<tr><td rowspan='2'>Fecha ultimo acceso: " + sesion.getLastAccessedTime() + "</td></tr>")
					.append("</table></body></html>");
			response.getWriter().close();

		}
	}
}
