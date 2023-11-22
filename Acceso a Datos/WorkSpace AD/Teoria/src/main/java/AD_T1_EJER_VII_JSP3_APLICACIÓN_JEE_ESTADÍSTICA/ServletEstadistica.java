package AD_T1_EJER_VII_JSP3_APLICACIÓN_JEE_ESTADÍSTICA;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet implementation class ServletEstadistica
 */
public class ServletEstadistica extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletEstadistica() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("estadisticas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre = (request.getParameter("nombre") != null) ? request.getParameter("nombre") : "";
		int opcion = (request.getParameter("estudio") != null) ? Integer.parseInt(request.getParameter("estudio")) : 0;

		HashMap<String, Integer> opciones = (getServletConfig().getServletContext().getAttribute("opciones") != null)
				? (HashMap<String, Integer>) getServletConfig().getServletContext().getAttribute("opciones")
				: new HashMap<String, Integer>();

		if (opciones.isEmpty()) {
			opciones.put("Me lo se todo, no necesito estudiar con la clase es suficiente", 0);
			opciones.put("No tengo ni idea, no lo entiendo", 0);
			opciones.put("Estudio en el último momento", 0);
			opciones.put("Estudio todo lo posible", 0);
		}

		if (nombre.isEmpty() && opcion == 0) {
			request.setAttribute("frase", "(*) El nombre y la respuesta son obligatorios");
			request.getRequestDispatcher("estadisticas.jsp").forward(request, response);
		} else {
			int contador = (request.getAttribute("contador") != null)
					? Integer.parseInt((String) request.getAttribute("contador")) + 1
					: 1;
			request.setAttribute("contador", contador);

			switch (opcion) {
			case 1: {
				opciones.replace("Me lo se todo, no necesito estudiar con la clase es suficiente",
						opciones.get("Me lo se todo, no necesito estudiar con la clase es suficiente") + 1);
				break;
			}
			case 2: {
				opciones.replace("No tengo ni idea, no lo entiendo",
						opciones.get("No tengo ni idea, no lo entiendo") + 1);
				break;
			}
			case 3: {
				opciones.replace("Estudio en el último momento", opciones.get("Estudio en el último momento") + 1);
				break;
			}
			case 4: {
				opciones.replace("Estudio todo lo posible", opciones.get("Estudio todo lo posible") + 1);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + opcion);
			}
			getServletConfig().getServletContext().setAttribute("opciones", opciones);
			request.getRequestDispatcher("resultado.jsp").forward(request, response);
		}
	}

}
