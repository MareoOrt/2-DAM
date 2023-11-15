package AD_T1_EJER_VII_JSP2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Servlet implementation class ServletVotacion
 */
public class ServletVotacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Votacion ruth = new Votacion("Ruth", 0);
	Votacion victor = new Votacion("Victor", 0);
	Votacion blanco = new Votacion("Blanco", 0);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletVotacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String votacion = (request.getParameter("delegado") != null) ? request.getParameter("delegado") : "Blanco";

		if(votacion.equals("Ruth")) {
			ruth.setnVotacion(ruth.getnVotacion()+1);
		}else if(votacion.equals("Victor")) {
			victor.setnVotacion(victor.getnVotacion()+1);
		}else {
			blanco.setnVotacion(blanco.getnVotacion()+1);
		}

		request.getRequestDispatcher("/Resultados.jsp");

	}

}
