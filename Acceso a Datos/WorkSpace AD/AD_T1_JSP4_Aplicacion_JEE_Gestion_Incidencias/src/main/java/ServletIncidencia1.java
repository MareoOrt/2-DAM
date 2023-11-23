
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String page = "";
		ArrayList<Incidencia> incidencias = (getServletContext().getAttribute("listInc") != null)
				? (ArrayList<Incidencia>) getServletContext().getAttribute("listInc")
				: new ArrayList<Incidencia>();

		switch (request.getParameter("boton")) {
		case "Confirmar":
			if(!request.getParameter("tema").isEmpty() && !request.getParameter("descripcion").isEmpty()) {
				Incidencia incidencia = new Incidencia(request.getParameter("tema"), request.getParameter("descripcion"));
				incidencias.add(incidencia);
				getServletConfig().getServletContext().setAttribute("listInc", incidencias);
				request.setAttribute("codigo", incidencia.getCodigo());
			}else {
				request.setAttribute("error", true);
			}
			page="AltaIncidencia.jsp";
			break;
		case "Consultar":
			page="ConsultaIncidencia.jsp";
			break;
		default:
			break;
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

}
