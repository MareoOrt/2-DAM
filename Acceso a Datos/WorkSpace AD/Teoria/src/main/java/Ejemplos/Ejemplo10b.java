package Ejemplos;

import java.io.IOException;
import java.io.ObjectInputFilter.Config;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Ejemplo10b
 */
public class Ejemplo10b extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Ejemplo10b() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter().append("<html><body>")
		.append("<p>Inicio del despachador</p>")
		.append((String)request.getAttribute("attribDispatch"));
		for (int i = 0; i < 10; i++) {
			response.getWriter().append("<br>")
			.append((String)request.getAttribute(""+i));
		}
		response.getWriter().append("<p>" + request.getParameter("metodo") + "</p>")
		.append("<p>Fin del despachador</p>")
		.append("</html></body>");
	}

}
