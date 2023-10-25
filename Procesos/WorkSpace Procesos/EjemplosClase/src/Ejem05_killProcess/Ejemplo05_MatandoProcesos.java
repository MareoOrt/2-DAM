package Ejem05_killProcess;

public class Ejemplo05_MatandoProcesos {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Proceso proceso = new Proceso();
		proceso.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Opcion que no vale para el examen, sin la bandera del interrupt
		// proceso.setEjecuta(false);
		
		// Interrupt
		// El interrupt cambai el estado de una bandera de true a false
		proceso.interrupt();
		
		// El interrupt se puede hacer con grupos de procesos (ThreadGroup)
	}
}
