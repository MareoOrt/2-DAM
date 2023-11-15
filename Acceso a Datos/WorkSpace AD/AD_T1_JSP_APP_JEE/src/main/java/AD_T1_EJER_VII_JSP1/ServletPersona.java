package AD_T1_EJER_VII_JSP1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class EJER_VII_JSP1
 */
public class ServletPersona extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Persona> personas = new ArrayList();       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPersona() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dni = (request.getParameter("dni")!=null)?request.getParameter("dni"):"";
		String nombre = (request.getParameter("nombre")!=null)?request.getParameter("nombre"):"";
		String apellidos = (request.getParameter("apellidos")!=null)?request.getParameter("apellidos"):"";
		String direccion = (request.getParameter("direccion")!=null)?request.getParameter("direccion"):"";
		String telf = (request.getParameter("telf")!=null)?request.getParameter("telf"):"";
		String correo = (request.getParameter("correo")!=null)?request.getParameter("correo"):"";
		
		personas.add(new Persona(dni, nombre, apellidos, direccion, telf, correo));
		request.getRequestDispatcher("/MostrarPersona.jsp");
		
	}

}
