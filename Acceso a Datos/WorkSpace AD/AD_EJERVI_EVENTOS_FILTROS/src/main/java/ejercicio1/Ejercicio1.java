package ejercicio1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Servlet implementation class Ejercicio1
 */
public class Ejercicio1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Ejercicio1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HashMap<String, String> tareas = (getServletContext().getAttribute("notificaciones") != null)
				? (HashMap<String, String>) getServletContext().getAttribute("notificaciones")
				: new HashMap<String, String>();

		response.setContentType("text/html");
		response.getWriter().append("<html><body>").append("<h2>Ingrese tarea nueva</h2>")
				.append("<form action='Ejercicio1.java' method='post'>").append("<input type='text' name='desc'>")
				.append("<input type='submit' name='enviar' value='Enviar'>").append("<br>")
				.append("<h3>Tareas anteriores</h3></form><ol>");

		for (String tarea : tareas.values()) {
			response.getWriter().append("<li>" + tarea + "</li>");
		}
		response.getWriter().append("</ol></body></html>");
		response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HashMap<String, String> tareas = (getServletContext().getAttribute("notificaciones") != null)
				? (HashMap<String, String>) getServletContext().getAttribute("notificaciones")
				: new HashMap<String, String>();

		tareas.put("T" + (tareas.size() + 1), request.getParameter("desc"));
	}

}
