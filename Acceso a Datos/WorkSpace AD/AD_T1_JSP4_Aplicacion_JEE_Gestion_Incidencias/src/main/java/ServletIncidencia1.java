

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import Ejercicio1.Incidencia;

/**
 * Servlet implementation class ServletIncidencia1
 */
public class ServletIncidencia1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletIncidencia1() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	super.init(config);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<Integer, Incidencia> incidencias = (getServletConfig().getServletContext().getAttribute("mapaIncidencias") != null)
				? (HashMap<Integer, Incidencia>) getServletConfig().getServletContext().getAttribute("mapaIncidencias")
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

		getServletConfig().getServletContext().setAttribute("codigos", codigosAt);
		getServletConfig().getServletContext().setAttribute("incidencias", incidenciasAt);

		getServletConfig().getServletContext().setAttribute("mapaIncidencias", incidencias);

		response.getWriter().close();
	}

}
