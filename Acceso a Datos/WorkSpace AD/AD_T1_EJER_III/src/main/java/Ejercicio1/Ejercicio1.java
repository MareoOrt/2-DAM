package Ejercicio1;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ejercicio1
 */
public class Ejercicio1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Ejercicio1() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int n = 0;
		
		Cookie ck = new Cookie("importe", n +"");
		
		response.addCookie(ck);
		response.setContentType("text/html");
		response.getWriter().append("<html><body>")
		.append("<h2>Tienda LetreA</h2>")
		.append("<p>" + n + "</p>")
		.append("<form method='post'>")
		.append("<input type='button' name='letra' value='A'/>")
		.append("<input type='button' name='letra' value='B'/>")
		.append("<input type='button' name='letra' value='C'/>")
		.append("<input type='button' name='letra' value='D'/>")
		.append("<br>")
		.append("<br>")
		.append("<input type='submit' content='Ticket'/>")
		.append("</form>")
		.append("</html></body>");
	}

}
