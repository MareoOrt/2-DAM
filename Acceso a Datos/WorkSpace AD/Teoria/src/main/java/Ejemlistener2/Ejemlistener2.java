package Ejemlistener2;

import java.util.Date;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;

/**
 * Application Lifecycle Listener implementation class Ejemlistener2
 *
 */
public class Ejemlistener2 implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public Ejemlistener2() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	
    }
    
    public void sessionCreated(HttpSessionEvent se){
    	HttpSession sesion = se.getSession();
    	System.out.println("A las " + new Date(System.currentTimeMillis()).toString() + 
    			" se creo la sesion con ID: " + sesion.getId() + "\n");
    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	HttpSession sesion = se.getSession();
    	System.out.println("A las " + new Date(System.currentTimeMillis()).toString() + 
    			" se destruyo la sesion con ID: " + sesion.getId());
    }
	
}
