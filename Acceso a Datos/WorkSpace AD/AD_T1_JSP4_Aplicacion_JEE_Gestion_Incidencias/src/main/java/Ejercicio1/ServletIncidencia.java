package Ejercicio1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

/**
 * Servlet implementation class ServletIncidencia
 */
public class ServletIncidencia extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletIncidencia() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HashMap<Integer, Incidencia> incidencias = (getServletContext().getAttribute("mapaIncidencias") != null)
				? (HashMap<Integer, Incidencia>) getServletContext().getAttribute("mapaIncidencias")
				: new HashMap<Integer, Incidencia>();
		int codigo = 0;

		do {
			codigo = new Random().nextInt(1, 20);
		} while (!incidencias.containsKey(codigo));

		incidencias.put(codigo,
				new Incidencia(codigo, request.getParameter("tema"), request.getParameter("descripcion")));

		response.setContentType("text/html");
		response.getWriter().append("<html><body><form action='ConsultaIncidencia.jsp'>")
				.append("<h1>ALTA INCIDENCIA</H1>")
				.append("<p>Su incidencia ha sido dada de alta en nuestro sistema con el codigo: </p>")
				.append("<h2>" + codigo + "</h2>").append("<input type='submit' name='consultar' value='Consultar'>")
				.append("</form></body></html>");

		int[] codigosAt = new int[incidencias.size() + 1];
		String[][] incidenciasAt = new String[2][incidencias.size() + 1];

		int cont = 0;

		for (Incidencia i : incidencias.values()) {

			codigosAt[cont] = i.getCodigo();

			incidenciasAt[0][cont] = i.getTema();
			incidenciasAt[1][cont] = i.getDescripcion();

			cont++;
		}

		getServletContext().setAttribute("codigos", codigosAt);
		getServletContext().setAttribute("incidencias", incidenciasAt);

		getServletContext().setAttribute("mapaIncidencias", incidencias);

		response.getWriter().close();
	}

}
