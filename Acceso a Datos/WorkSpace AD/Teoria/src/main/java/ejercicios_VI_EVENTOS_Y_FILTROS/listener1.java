package ejercicios_VI_EVENTOS_Y_FILTROS;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class listener1
 *
 */
public class listener1 implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public listener1() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	// La variable "tareas" es de tipo HashMap y
    	// en "fichero" represente el nombre del archivo para almacenar el objeto
    	ObjectInputStream ois = new ObjectInputStream (
    	new FileInputStream(fichero);
    	tareas = (HashMap) ois.readObject();
    	ois.close();
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	// En la variable "tareas" tengo el HashMap con las tareas y
    	// "fichero" representa el nombre del archivo para almacenar el objeto
    	ObjectOutputStream oos = new ObjectOutputStream (
    	new FileOutputStream(fichero);
    	// Serialización
    	oos.writeObject(tareas);
    	oos.close();
    }
	
}
