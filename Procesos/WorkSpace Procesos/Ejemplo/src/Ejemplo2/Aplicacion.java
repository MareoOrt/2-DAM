package Ejemplo2;

public class Aplicacion {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		ThreadGroup grupo1 = new ThreadGroup("grupo1");
		
		Proceso proceso1 = new Proceso("Proceso 1", grupo1);
		proceso1.getProceso().setPriority(Thread.MIN_PRIORITY);
		proceso1.getProceso().start();
		
		Proceso proceso2 = new Proceso("Proceso 2", grupo1);
		proceso1.getProceso().setPriority(Thread.MAX_PRIORITY);
		proceso2.getProceso().start();
	}
}
