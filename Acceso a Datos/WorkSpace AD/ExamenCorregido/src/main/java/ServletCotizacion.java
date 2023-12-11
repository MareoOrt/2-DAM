
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import entities.Cotizacion;

/**
 * Servlet implementation class ServletCotizacion
 */
public class ServletCotizacion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCotizacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletContext sc = request.getServletContext();
		HttpSession sesion = request.getSession(true);

		HashMap<String, Cotizacion> cotizaciones = (sc.getAttribute("listCotiza") != null)
				? (HashMap<String, Cotizacion>) sc.getAttribute("listCotiza")
				: new HashMap<String, Cotizacion>();
		String page = "";

		switch (request.getParameter("boton")) {
		case "Acceso":
			if (request.getParameter("nombre").isBlank() || request.getParameter("password").isBlank()) {
				request.setAttribute("blancos", "(*) El nombre y el codigo son obligatorios.");
				page = "Acceso.jsp";
			} else if (!request.getParameter("password").equals("123456")) {
				request.setAttribute("credenciales", "(*) Las credenciales introducidas son erroneas.");
				page = "Acceso.jsp";
			} else {
				sesion.setAttribute("user", request.getParameter("nombre"));
				page = "Cotizacion.jsp";
			}
			break;
		case "Cerrar":
			sesion.invalidate();
			request.setAttribute("invalida", "(*) La sesion ha expirado.");
			break;
		case "Enviar":
			Enumeration<String> params = request.getParameterNames();
			while (params.hasMoreElements()) {
				String type = params.nextElement();
				String valor = (request.getParameter(type) != null && request.getParameter(type).isBlank())
						? request.getParameter(type).replace(",", ".")
						: "0.0";
				Cotizacion c;
				if (!type.equals("boton")) {
					c = new Cotizacion(type, Double.valueOf(valor), cotizaciones.get(type).getAnterior());
					c.setEvalua();
					cotizaciones.put(type, c);
				}
			}
			page = "ConsultaCotizaicon.jsp";
			break;
		case "Volver":
			page = "Acceso.jsp";
			break;
		default:
			break;
		}
		request.getRequestDispatcher(page).forward(request, response);

	}

}
