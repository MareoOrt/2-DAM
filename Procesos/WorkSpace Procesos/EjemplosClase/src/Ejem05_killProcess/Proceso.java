package Ejem05_killProcess;

// TODO: Auto-generated Javadoc
/**
 * The Class Proceso.
 */
public class Proceso extends Thread {

	private boolean ejecuta = true;

	public boolean isEjecuta() {
		return ejecuta;
	}

	public void setEjecuta(boolean ejecuta) {
		this.ejecuta = ejecuta;
	}

	/**
	 * Run.
	 */
	@Override
	public void run() {
		super.run();
		int i = 0;
		
		// Sin la bandera de interrupt
		// while (ejecuta) {
		
		// Con la bandera del interrupt
		while(!this.isInterrupted()) 
		{
			System.out.println("Estoy en ejecucion " + i);
			i++;
		}
		
		// Problema, si el proceso se duerme
		while(!this.isInterrupted()) 
		{	
			System.out.println("Estoy en ejecucion " + i);
			i++;
			
			// Si haces un interupt y esta dormido el proceso no se mata, saltara un error 
			// porque el proceso esta dormido
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				
				// Lo que podemos hacer es poner que al salte el error, le decimos que se 
				// interrumpa
				this.interrupt();
			}
		}
	}
}