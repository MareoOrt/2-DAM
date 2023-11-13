package ejercicio1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class Ej1Listener
 *
 */
public class Ej1Listener implements ServletContextListener, HttpSessionListener {

	/**
	 * Default constructor.
	 */
	public Ej1Listener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {

		File fichero = new File("tareas.txt");
		HashMap<String, String> tareas = (se.getSession().getAttribute("notificaciones") != null)
				? (HashMap<String, String>) se.getSession().getAttribute("notificaciones")
				: new HashMap<String, String>();

		// En la variable "tareas" tengo el HashMap con las tareas y
		// "fichero" representa el nombre del archivo para almacenar el objeto
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fichero));
			// Serialización
			oos.writeObject(tareas);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		File fichero = new File("tareas.txt");
		HashMap<String, String> tareas = new HashMap<String, String>();

		// TODO Auto-generated method stub
		// La variable "tareas" es de tipo HashMap y
		// en "fichero" represente el nombre del ar

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero));
			tareas = (HashMap) ois.readObject();
			ois.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sce.getServletContext().setAttribute("notificaciones", tareas);
	}

}
