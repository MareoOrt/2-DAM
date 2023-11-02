package Ejericicio5.Ejercicio_Matando_Procesos_Runnable;

public class Proceso extends Thread {

	private Contador contador;
	private Thread proceso;

	public Proceso(Contador contador, String nombre, ThreadGroup grupo) {
		this.contador = contador;
		this.proceso = new Thread(grupo, nombre);
	}

	public Contador getContador() {
		return contador;
	}

	public void setContador(Contador contador) {
		this.contador = contador;
	}

	public Thread getProceso() {
		return proceso;
	}

	public void setProceso(Thread proceso) {
		this.proceso = proceso;
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted() && contador.getContador() < 5000) {
			if (contador.getContador() == 4999) {
				int numHermanos = proceso.getThreadGroup().activeCount();
				Thread[] procesosHermanos = new Thread[numHermanos];
				Thread.enumerate(procesosHermanos);

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				for (Thread p : procesosHermanos) {
					if (p != Thread.currentThread() && p.isAlive()) {
						p.interrupt();
						System.out.println("Mato a " + p.getName() + ".");
					}
				}
				System.out.println("Proceso: " + Thread.currentThread().getName() + " finalizo, que soy el último");
			}
			contador.setContador(contador.getContador() +1);
		}

		if (Thread.currentThread().isInterrupted())
			System.out.println("Proceso: " + Thread.currentThread().getName() + " me han matado");
	}

}
