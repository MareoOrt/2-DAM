package Ejercicios3;

import java.io.IOException;
import java.util.Random;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ejercicio3_5
 */
public class Ejercicio3_5 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// IMPORTANTE, Hacer el super porque sino nos da nulo
		super.init(config);

	}

	/**
	 * Default constructor.
	 */
	public Ejercicio3_5() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/plain");
		
		int numContext = getServletContext().getAttribute("num_context") != null
				? (int) (getServletContext().getAttribute("num_context"))
				: new Random().nextInt(0, 11);
		int num = new Random().nextInt(0, 11);
		
		response.getWriter().append("Aleatorio: " + num + ", Contexto: " + numContext + "\n")
				.append((num == numContext) ? "Numeros iguales" : "Numeros distintos");
		
		getServletContext().setAttribute("num_context", new Random().nextInt(0, 11));
	}

}
