package Ejericicio3;

public class Proceso implements Runnable {

	Thread proceso;
	
	@Override
	public void run() {
		System.out.println(proceso.getName());
	}

	public Proceso(String nombre, ThreadGroup grupo) {
		proceso = new Thread(grupo, nombre);
	}

	public Thread getProceso() {
		return proceso;
	}

	public void setProceso(Thread thread) {
		this.proceso = thread;
	}



}
