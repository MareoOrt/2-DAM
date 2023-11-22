
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

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
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("estadistica.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre = (request.getParameter("nombre") != null) ? request.getParameter("nombre") : "";
		int opcion = (request.getParameter("opcion") != null)
				? Integer.parseInt(request.getParameter("opcion"))
				: 0;
		if (nombre.isEmpty() && opcion == 0) {
			request.setAttribute("frase", "(*) El nombre y la respuesta son obligatorios");
			request.getRequestDispatcher("estadistica.jsp").forward(request, response);
		} else {
			HashMap<String, Integer> mapa = (request.getAttribute("opciones") != null)
					? (HashMap<String, Integer>) request.getAttribute("opciones")
					: new HashMap<>();
			if (mapa.isEmpty()) {
				mapa.put("Me lo se todo, con la clase es suficiente", 0);
				mapa.put("No tengo ni idea, no lo intento", 0);
				mapa.put("Estudio en el ultimo momento", 0);
				mapa.put("Estudio todo lo posible", 0);
			} else {
				switch (opcion) {
				case 1:
					mapa.replace("Me lo se todo, con la clase es suficiente",
							mapa.get("Me lo se todo, con la clase es suficiente"),
							mapa.get("Me lo se todo, con la clase es suficiente") + 1);
					break;
				case 2:
					mapa.replace("No tengo ni idea, no lo intento", mapa.get("No tengo ni idea, no lo intento"),
							mapa.get("No tengo ni idea, no lo intento") + 1);
					break;
				case 3:
					mapa.replace("Estudio en el ultimo momento", mapa.get("Estudio en el ultimo momento"),
							mapa.get("Estudio en el ultimo momento") + 1);
					break;
				case 4:
					mapa.replace("Estudio todo lo posible", mapa.get("Estudio todo lo posible"),
							mapa.get("Estudio todo lo posible") + 1);
					break;
				default:
					System.err.println("Ocurrio un error");
					break;
				}
				request.setAttribute("nombre", request.getParameter("nombre"));
				getServletConfig().getServletContext().setAttribute("opciones", mapa);

				request.getRequestDispatcher("resultado.jsp").forward(request, response);
			}
		}
	}

}
