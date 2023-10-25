package Ejercicios3;

import java.io.IOException;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Ejercicio3_1
 */
public class Ejercicio3_1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Ejercicio3_1() {
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

		// Se inicia html
		response.setContentType("text/html");
		response.getWriter().append("<html><body>");

		// Se crea sesion
		// true -> si existe se recupera, sino la crea
		HttpSession sesion = request.getSession(true);
		// Recogemos la suma total de otras sesiones, y si no existe el atributo le da
		// el valor 0
		int sumTotal = sesion.getAttribute("sum_total") != null
				? Integer.parseInt(sesion.getAttribute("sum_total").toString())
				: 0;
		// Recogemos todos los parametros
		Enumeration<String> parametros = request.getParameterNames();

		// Recorremos los parametros y si coincide con ticket que imprima con la suma
		// total, sino que ponga el valor del boton
		while (parametros.hasMoreElements()) {
			// Cogemos el nombre de la version
			String aux = parametros.nextElement();
			// Imprimimos el total si es el boton ticket y si no mostramos el valor de la
			// letra
			response.getWriter().append(aux.equalsIgnoreCase("ticket") ? "TOTAL ACUMULDO" + sumTotal : aux);
			sumTotal += aux.equalsIgnoreCase("tiquet") ? 0 : Integer.parseInt(aux);
		}
		// Metemos el atributo para proximas sesiones
		sesion.setAttribute("sum_total", sumTotal);

		// Creamos el html
		response.getWriter().append("<form action='Ejercicio3_1' method='get'>")
				.append("<input type='submit' name='3' value='A'/>").append("<input type='submit' name='4' value='B'/>")
				.append("<input type='submit' name='5' value='C'/>").append("<input type='submit' name='1' value='D'/>")
				.append("<input type='submit' name='ticket' value='ticket'/>").append("</form></body></htmñl>");

		// Cerramos
		response.getWriter().close();
	}

}
