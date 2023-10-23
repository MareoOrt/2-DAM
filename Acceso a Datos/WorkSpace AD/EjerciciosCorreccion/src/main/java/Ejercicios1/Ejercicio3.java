package Ejercicios1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ejercicio3
 */
public class Ejercicio3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Ejercicio3() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(jakarta.servlet.http.HttpServletRequest request,
			jakarta.servlet.http.HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/plain");
		response.getWriter().append("<HTML><BODY><h2>Mostrar Datos</h2>").append("<table border='1'>").append("<tr>")
				.append("<td>Nombres</td>").append("<td>Apellidos</td>").append("<td>Direccion</td>")
				.append("<td>Tipo de tarjeta</td>").append("</tr>").append("<tr>")
				.append("<td>" + request.getParameter("nombre") + "</td>")
				.append("<td>" + request.getParameter("apellidos") + "</td>")
				.append("<td>" + request.getParameter("direccion") + "</td>")
				.append("<td>" + request.getParameter("numero") + "</td>").append("</tr>").append("</BODY></HTML>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
