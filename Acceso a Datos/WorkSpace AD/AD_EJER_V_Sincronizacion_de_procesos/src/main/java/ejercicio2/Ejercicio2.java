package ejercicio2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Servlet implementation class Ejercicio2
 */
public class Ejercicio2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Object lock = new Object();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Ejercicio2() {
		super();
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

		// Sincronizacion para solucionar problemas de concurrencia
		synchronized (lock) {
			// Array y relleno de este
			List<Integer> cadenaNum = new ArrayList();
			for (int i = 0; i < 100000; i++) {
				cadenaNum.add(new Random().nextInt(0, 100));
			}

			// Suma
			int valor = 0;
			for (int i = 0; i < cadenaNum.size(); i++) {
				valor += cadenaNum.get(i);
			}

			// Resta
			for (int i = 0; i < cadenaNum.size(); i++) {
				valor -= cadenaNum.get(i);
			}

			// Mostrar iframes
			response.setContentType("html/txt");
			response.getWriter().append("<html>body>");
			for (int i = 0; i < 25; i++) {
				response.getWriter().append(
						"<iframe src=\"/EjercicioV/Ejercicio2\" width=\"19%\" >El resultado es " + valor + "</iframe>");
			}
			response.getWriter().append("</body></html>");
			response.getWriter().close();
		}

	}

}
