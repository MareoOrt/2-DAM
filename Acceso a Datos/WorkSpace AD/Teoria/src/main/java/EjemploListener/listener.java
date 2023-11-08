package EjemploListener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class listener
 *
 */
public class listener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public listener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	
    	System.out.println("Aplicacion arrancando el constexto" + sce.getServletContext().getContextPath()
    			+ "con el siquiente parametro de inicialización " + sce.getServletContext().getInitParameter("bbdd"));
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	System.out.println("Aplicacion del contexto " + sce.getServletContext().getContextPath() + "deteniendose ... ");
    }
	
}
