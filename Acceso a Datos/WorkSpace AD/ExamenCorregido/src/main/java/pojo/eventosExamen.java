package pojo;

import java.util.HashMap;

import entities.Cotizacion;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class eventosExamen
 *
 */
public class eventosExamen implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public eventosExamen() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	HashMap<String, Cotizacion> cotizaciones = new HashMap<String, Cotizacion>();
        cotizaciones.put("DAM", new Cotizacion("DAM", 48.5));
        cotizaciones.put("SMR", new Cotizacion("SMR", 47.5));
        cotizaciones.put("ADFI", new Cotizacion("ADFI", 30.0));
        cotizaciones.put("ADMIN", new Cotizacion("ADMIN", 45.0));
        
        sce.getServletContext().setAttribute("listCotiza", cotizaciones);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }
	
}
