package pojo;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet Filter implementation class filtrosExamen
 */
@WebFilter("/ServletCotizacion")
public class filtrosExamen extends HttpFilter implements Filter {

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public filtrosExamen() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String fechaActual = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		request.getServletContext().setAttribute("fechaActual", fechaActual);
		if (request.getParameter("boton") != null && request.getParameter("Enviar").equalsIgnoreCase("enviar")) {
			Integer contador = (request.getServletContext().getAttribute("acceso_" + fechaActual) != null)
					? (Integer) (request.getServletContext().getAttribute("acceso_" + fechaActual))
					: 0;
			request.setAttribute("acceso_"+fechaActual, contador +1);
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
