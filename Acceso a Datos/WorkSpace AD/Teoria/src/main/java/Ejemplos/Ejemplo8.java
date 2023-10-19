package Ejemplos;

import java.io.IOException;
import java.io.ObjectInputFilter.Config;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
 * Servlet implementation class Ejemplo8
 */
public class Ejemplo8 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Ejemplo8() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/plain");
		int c=0;
		
//		Creamos la sesion si no existe
		HttpSession sesion = request.getSession(true);
		if(sesion.getAttribute("cont")==null) {
			response.getWriter().append("HOLA DAM \n");
//			Reescritura de URL si el navegador no acepta cookies
//			response.sendRedirect(response.encodeRedirectURL("Ejemplo8"));
		}else {
			response.getWriter().append("HOLA DE NUEVO DAM \n");
			c = (int) sesion.getAttribute("cont");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
