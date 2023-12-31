
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Teoria9
 */
public class Teoria9 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Teoria9() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;
		int n = 0;
		Cookie[] cs = request.getCookies();
		if (cs != null) {
			for (int i = 0; i < cs.length && !flag; i++) {
				if (cs[i].getName().equals("cont")) {
					n = Integer.parseInt(cs[i].getValue()) + 1;
					flag = true;
				}
			}
		}

		Cookie c = new Cookie("cont", String.valueOf(n));
		response.addCookie(c);
		response.getWriter().append("Numero de visitas= " + c);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
