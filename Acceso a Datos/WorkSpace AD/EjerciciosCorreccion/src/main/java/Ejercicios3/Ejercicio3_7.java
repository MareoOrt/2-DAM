package Ejercicios3;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ejercicio3_7
 */
public class Ejercicio3_7 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

	}

	/**
	 * Default constructor.
	 */
	public Ejercicio3_7() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      MostrarDatos
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HashMap<String, Integer> result = getServletContext().getAttribute("resultado") != null
				? (HashMap<String, Integer>) getServletContext().getAttribute("resultado")
				: new HashMap<String, Integer>();

		response.setContentType("text/html");
		response.getWriter().append("<html><body><table border='2'><tr><td>Numero de Personas<td>Sintoma</td></tr>");

		for (Map.Entry<String, Integer> entry : result.entrySet()) {
			response.getWriter().append("<tr><td>" + entry.getKey() + "</td><td>" + entry.getValue() + "</td></tr>");
		}
		response.getWriter().append("</table>")
		.append((CharSequence) ((request.getAttribute("msj")) != null ? request.getAttribute("msj"):""))
		.append("</body></html>");

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recoger si el usuario ya ha realizado la encuesta
		Cookie[] cs = request.getCookies();
		boolean flag = false;
		if (cs != null) {
			for (int i = 0; i < cs.length && flag; i++) {
				if (cs[i].getName().equals("si")) {
					flag = true;
				}
			}
		}

		if (!flag) {
			HashMap<String, Integer> result = getServletContext().getAttribute("resultado") != null
					? (HashMap<String, Integer>) getServletContext().getAttribute("resultado")
					: new HashMap<String, Integer>();
			// Recogemos los nombres de los campos del formulario
			Enumeration<String> parameters = request.getParameterNames();

			while (parameters.hasMoreElements()) {

				String param = parameters.nextElement();

				if (param != null && !param.equalsIgnoreCase("Enviar")) {
					if (result.get(param) != null) {
						result.put(param, result.get(param) + 1);
					} else {
						result.put(param, 1);
					}
				}

			}

			getServletContext().setAttribute("resultado", result);
			Cookie c = new Cookie("encuesta", "si");
			response.addCookie(c);

			doGet(request, response);
		}

	}
}
