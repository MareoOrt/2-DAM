package EjerciciosSesionesyCoockies;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Ejericicio1
 */
public class Ejericicio1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int cantidadTotal=0;
	
	/**
	 * Default constructor.
	 */
	public Ejericicio1() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		
		
		response.setContentType("text/plain");
		response.getWriter().append("<HTML><BODY>")
							.append("<H2>Tienda LetreA</H2>")
							.append("<input type='buttton' content='A' name='botnA'/>")
							.append("<input type='buttton' content='B' name='botnB'/>")
							.append("<input type='buttton' content='C' name='botnC'/>")
							.append("<input type='buttton' content='D' name='botnD'/>")
							.append("<input type='buttton' content='Ticket' name='botnTick'/>")
							.append("</HTML></BODY>");
		response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
