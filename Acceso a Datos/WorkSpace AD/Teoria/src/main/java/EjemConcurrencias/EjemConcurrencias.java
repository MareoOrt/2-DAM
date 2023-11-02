package EjemConcurrencias;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class EjemConcurrencias
 */
public class EjemConcurrencias extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static int val = 0;   
	private final Object lock = new Object();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EjemConcurrencias() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		synchronized (lock) {		
		val = Integer.parseInt(request.getParameter("param"));
		// Mostrar el porceso que se va a ejecutar
		System.out.println("Thread: " + Thread.currentThread().getName() +" usa el valor: "+ val);
		// Simulamos que el servlet esta en la operativa
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Mostrar el proceso que se esta ejecutando
		System.out.println("Thread: " + Thread.currentThread().getName() +" usa el valor: "+ val);
		}		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
