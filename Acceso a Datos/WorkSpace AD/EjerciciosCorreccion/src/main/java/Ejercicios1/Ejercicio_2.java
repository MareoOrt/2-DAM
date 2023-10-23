package Ejercicios1;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ejercicio_22
 */
public class Ejercicio_2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArrayList<Persona> personas = new ArrayList<Persona>();
	private Persona persona;
	
    /**
     * Default constructor. 
     */
    public Ejercicio_2() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		persona.setNombre(request.getParameter("nombre")!= null?request.getParameter("nombre"):"");
		persona.setApellidos(request.getParameter("nombre")!= null?request.getParameter("nombre"):"");
		persona.setEdad(Integer.parseInt(request.getParameter("nombre"))!= 0?(Integer.parseInt(request.getParameter("nombre")):0));
		persona.setContacto(request.getParameter("contacto")!= null?request.getParameter("contacto"):"");
		personas.add(persona);
		response.setContentType("text/html");
		response.getWriter()
		.append("<HTML><BODY>")
		.append("<table border='1'>")
		.append("<tr>")
		.append("<td>Nombres</td>")
		.append("<td>Apellidos</td>")
		.append("<td>Direccion</td>")
		.append("<td>Tipo de tarjeta</td>")
		for(Persona p : personas) {
		.append("</tr>")
		.append("<tr>")
		.append("<td>" + p.getNombre() + "</td>")
		.append("<td>" + p.getApellidos() + "</td>")
		.append("<td>" + p.getEdad() + "</td>")
		.append("<td>" + p.getContacto() + "</td>")
		.append("</tr>")
		}
		.append("</BODY></HTML>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
