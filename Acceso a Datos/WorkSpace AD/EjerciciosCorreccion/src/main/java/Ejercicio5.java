

import java.io.IOException;
import javax.*;
import jakarta.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ejercicio5
 */
public class Ejercicio5 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Ejercicio5() {
        // TODO Auto-generated constructor stub
    }
    
//    public void init

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/plain");
		response.getWriter().append("<html><body style:'backgroundcolor>")
		//.append((request.getParameter("colorParam")!= null?request.getParameter((String)getServletContext().getInitParameter("colorContext"))))
		.append(">")
		.append("<form action='Ejercicio2b' method='post>")
		.append("<inpurt type='submit' name='Enviar'>")
		.append("</html></body>");
		response.getWriter().close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
