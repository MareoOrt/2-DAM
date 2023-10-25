package Ejercicios3;

import java.io.IOException;
import java.util.HashMap;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ejercicio3_3b
 */
public class Ejercicio3_3b extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Ejercicio3_3b() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HashMap<String, String> lib = (HashMap<String, String>) (getServletConfig().getServletContext()
				.getAttribute("libros") == null ? getServletConfig().getServletContext().getAttribute("libros")
						: new HashMap<String, String>());
		// Recuperar todas las cookies del navegador cliente
		Cookie cookies[] = request.getCookies();
		Double suma = 0.00;

		response.setContentType("text/html");
		response.getWriter().append("<html><body>");

		if (cookies != null && cookies.length != 0) {

			response.getWriter().append("Compra Total").append("ztable border='1'><tr>")
					.append("<td>Título del libro</td>").append("<td>Unidades</td>").append("<td>precio</td></tr>");

			for (Cookie cookie : cookies) {

				response.getWriter().append("<tr><td>" + cookie.getName() + "</td>")
						.append("<tr><td>" + cookie.getValue() + "</td>")
						.append("<tr><td>" + lib.get(cookie.getName()) + "</td></tr>");

				suma += Double.parseDouble(cookie.getValue()) * Double.parseDouble(lib.get(cookie.getName()));

				// Borramos el rastro de cookies para este sitio
				cookie.setMaxAge(0);
				// Envio en la respuesta la cookie al navegador
				response.addCookie(cookie);
			}
			response.getWriter().append("</table><br>").append("<h2><b>Total: " + suma + "</b></h2>");

		} else {// En caso de que el vector de cookies sea nulo o este vacio
			response.getWriter().append("Ho hay comptra realizada");
		}
		
		response.getWriter().append("</html></body>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
