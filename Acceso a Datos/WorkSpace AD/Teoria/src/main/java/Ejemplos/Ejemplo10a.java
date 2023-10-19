package Ejemplos;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ejemplo10a
 */
public class Ejemplo10a extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext sc;

	/**
	 * Default constructor.
	 */
	public Ejemplo10a() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter().append("<html><body>").append("<form action='Ejemplo10a' method='post'>")
				.append("<input type='submit' name='metodo' value='include'/>")
				.append("<input type='submit' name='metodo' value='forward'/>").append("</form></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter().append("<html>").append("<body style='background-color:#AAFF9F'>")
				.append("<h3>Ejemplo server despachador</h3>")
				.append("<p>Este server uas un despachador que nos redirige a otro server</p>");

		// Para despachar a otros contextos pero son con rutas absolutas
		// ResquestDispatcher dispatcher= sc.getResquestDispatcher("/Ejemplo10a");

		// Para despachar en el contexto de la app, y para rutas relativas
		RequestDispatcher dispatcher = request.getRequestDispatcher("Ejemplo10b");

		if (dispatcher != null) {
			request.setAttribute("attribDispatch", "LLEGA ATRIBUTO");
			for (int i = 0; i < 19; i++) {
				request.setAttribute("" + i, "LLEGA ATRIBUTO");
			}
			if (request.getParameter("metodo").equals("include")) {
				dispatcher.include(request, response);
			} else {
				dispatcher.forward(request, response);
			}

			response.getWriter().append("<p>Final del servlet despachador</p>");
		} else {
			response.getWriter().append("<p>No se encontro el despachador</p>");
		}
		response.getWriter().append("</body></html>");
		response.getWriter().close();

	}

	@Override
	public void init() throws ServletException {
		sc = getServletConfig().getServletContext();
	}

}
