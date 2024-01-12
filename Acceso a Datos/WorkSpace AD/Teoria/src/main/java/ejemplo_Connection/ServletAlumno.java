package ejemplo_Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.AlumnoDao;
import dao.AlumnoLN;
import entitties.Alumno;

/**
 * Servlet implementation class ServletGestor
 */
public class ServletAlumno extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAlumno() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String b = request.getParameter("");
		String page = "";

		switch (b) {
		case "Alta": {
			page = "alta.jsp";
			break;
		}
		case "Baja": {
			page = "baja.jsp";
			break;
		}
		case "Actualizar": {
			page = "alta.jsp";
			break;
		}
		case "Seleccionar": {
			page = "select.jsp";
			break;
		}
		default:{
			throw new IllegalArgumentException("Unexpected value: " + b);
			break;
		}

		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String b = request.getParameter("boton");
		String msj = "";

		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String ciclo = request.getParameter("ciclo");
		String curso = request.getParameter("curso");

		Alumno a = new Alumno(nombre, apellidos, ciclo, curso);

		boolean error = false;

		try {
			switch (b) {
			case "Alta": {
				msj = AlumnoLN.alta(a);
			}
			case "Baja": {
				msj = AlumnoLN.baja(a);
			}
			case "Actualizar": {
				msj = AlumnoLN.actualiza(a);
			}
			case "Seleccionar": {
				msj = AlumnoLN.seleccionar(a);
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + b);
			}
		} catch (Exception e) {
			error = true;
			System.err.println("ServletAlumno: Se ha producido el siguiente error. " + e.getMessage());
			e.printStackTrace();
		} finally {
			if("Volver".equals(request.getParameter("Volver"))) {
				
			}
		}

	}

}
