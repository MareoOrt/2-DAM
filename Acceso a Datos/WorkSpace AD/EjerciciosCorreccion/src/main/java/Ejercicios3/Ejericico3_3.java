package Ejercicios3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ejericico3_3
 */
public class Ejericico3_3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HashMap<String, String> lib = new HashMap<String, String>();

	/**
	 * Default constructor.
	 */
	public Ejericico3_3() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		// Cargamos la configuracion
		super.init(config);

		// Añadimos los libros a la lista
		lib.put("Java", "57.00");
		lib.put("C", "56.00");
		lib.put("C ++", "63.00");
		lib.put("VB", "52.00");
		lib.put("Python", "46.00");

		config.getServletContext().setAttribute("libros", lib);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		response.getWriter().append("<html><body>").append("<h1>Bienvenido a Web Libreria. Seleccione el libro</h1>")
				.append("<form action='Ejercicio3_3' method='post'>").append("<h3>Listado de libros:</h3>")
				.append("select name='list'>");

		for (Map.Entry entry : lib.entrySet()) {
			response.getWriter().append("<option>" + entry.getKey() + "</option>");
		}

		response.getWriter().append("</select>").append("<input type='text' name='ud' value='0'/>")
				.append("<input type='submit' value='Enviar'/>").append("</form></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.getWriter().append("<html><body>");

		if (!request.getParameter("ud").equals("0")) {

			// creamos una cookie con el libro seleccionado y las unidades a seleccionar
			Cookie c = new Cookie(request.getParameter("list"), request.getParameter("ud"));
			// Añadimos la cookie a la respuesta al navegador
			response.addCookie(c);

			response.getWriter().append(
					"<h1>Bienvenido a Web Libreria. Usted seleccionó " + request.getParameter("list") + "</h1>");
		} else {
			response.getWriter().append("<h1>No se han seleccionado ningun libro</h1>")
			.append("<p><a href='Ejercicio3_3'>Seguir comprando</a></p>")
					.append("<p><a href='Ejercicio3_3b'>Ver compra</a></p>");
		}
		response.getWriter().append("</html></body>");

	}

}
