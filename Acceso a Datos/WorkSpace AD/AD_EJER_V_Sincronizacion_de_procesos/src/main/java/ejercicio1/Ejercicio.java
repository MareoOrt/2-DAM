package ejercicio1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Ejercicio
 */
public class Ejercicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Object lock = new Object();

	/**
	 * Default constructor.
	 */
	public Ejercicio() {
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

			// Recoger parametro
			int n = Integer.parseInt(request.getParameter("param"));

			// Mostrar
			System.out.println("Soy el Servlet 1 y se que pusiste el numero " + n + " en al URL");

			// Dormir 10 s
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Guardar el atributo
			request.setAttribute("num", n);

			// Ejecutar servlet 2
			Ejercicio1b ej = new Ejercicio1b();
			ej.doGet(request, response);
		}

	}
}
