package ejercicio1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class Ejercicio1b.
 */
public class Ejercicio1b extends HttpServlet implements Runnable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The val. */
	private static int val = 0;

	/**
	 * Instantiates a new ejercicio 1 b.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public Ejercicio1b() {
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
		// Recoger numero
		int val = Integer.parseInt(request.getParameter("param"));
	}

	/**
	 * Run.
	 */
	@Override
	public void run() {
		// Mostrar
		System.out.println("Soy el Servlet 2 y se que pusiste el numero " + val + " en al URL");
	}
}
