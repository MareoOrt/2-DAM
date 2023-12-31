package Ejercicios1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ejercicio1_2
 */
public class Ejercicio1_2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Ejercicio1b() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/plain");
		
		switch (((request.getParameter("Saludo")) |= null) ? request.getParameter("idioma") : "" ) {
		case "español":
			response.getWriter().append("Hola Mundo");
			break;
		case "ingles":
			response.getWriter().append("Hellow world");
			break;
		case "italiano":
			response.getWriter().append("Ciao tutti");
			break;
		case "frances":
			response.getWriter().append("Salute monde");
			break;
		default:
			response.getWriter().append("No se escogio el idioma");
			break;
		}
		
		response.getWriter().close();
	}
}
