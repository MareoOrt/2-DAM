package EjemploFiltro;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
/**
 * Servlet Filter implementation class EjemploFiltro
 */

// @WebFilter("/*") Cualquier peticion
// @WebFilter("/Ejemplo14") Una web en concreto

@WebFilter(urlPatterns= {"/Ejemplos14/Ejemplo14b", "/Ejemplos14/Ejemplo14"})
public class EjemploFiltro extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public EjemploFiltro() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		StringBuilder buffer = new StringBuilder();
		Map<String, String[]> params=request.getParameterMap();
		for (Entry<String, String[]> entry: params.entrySet()) {
			String key = entry.getKey();
			buffer.append(key);
			buffer.append("=");
			String[] val = entry.getValue();
			for(String string : val) {
				buffer.append(string);
				buffer.append("|");
			}
		}
		System.out.println("Recibiendo peticion desde la ip: " + request.getRemoteAddr());
		if(buffer.toString().equals("")) {
			System.out.println("\n La peticion tiene los siguientes parametros: " + buffer);
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
