package Ejemplo02;

// TODO: Auto-generated Javadoc
/**
 * The Class Proceso.
 */
public class Proceso implements Runnable {

	/** The proceso. */
	Thread proceso;

	/**
	 * Gets the proceso.
	 *
	 * @return the proceso
	 */
	public Thread getProceso() {
		return proceso;
	}



	/**
	 * Sets the proceso.
	 *
	 * @param proceso the new proceso
	 */
	public void setProceso(Thread proceso) {
		this.proceso = proceso;
	}

	/**
	 * Instantiates a new proceso.
	 */
	public Proceso() {
		// TODO Auto-generated constructor stub
		proceso = new Thread(this);
	}
	
	/**
	 * Instantiates a new proceso.
	 *
	 * @param grupo the grupo
	 */
	public Proceso(ThreadGroup grupo) {
		proceso = new Thread(grupo, this);
	}
	
	/**
	 * Instantiates a new proceso.
	 *
	 * @param nombre the nombre
	 * @param grupo1 the grupo 1
	 */
	public Proceso(String nombre, ThreadGroup grupo1) {
		this(grupo1);
		proceso.setName(nombre);
	}
	
	/**
	 * Run.
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// Si no le das nombre se le asigna Thread-1 por defecto
		
		for (int i = 0; i < 100; i++) {
			System.out.println("Soy el proceso: " + proceso.getName() + " contando el numero " + 
					1 + " y soy del grupo " + proceso.getThreadGroup().getName());
		}

	}
}
